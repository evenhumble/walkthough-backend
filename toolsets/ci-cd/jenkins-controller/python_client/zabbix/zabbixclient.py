# coding: utf-8
import argparse
import json
import logging

import sys
import requests

"""
only support python3
"""


class ZabbixAPIException(Exception):
    pass


logger = logging.getLogger(__name__)


class MonitorDescription:
    zabbix_servers = {
        "JMX": "java.zabbix.abcd.com",
        "SERVER": "server.zabbix.abcd.com"
    }

    default_interface_port = {
        "JMX": 10011,
        "SERVER": 10050
    }

    default_template_names = {
        "JMX": ["Template JMX Generic"],
        "SERVER": ["Template OS Linux"]
    }

    default_host_groups = {
        "JMX": ['JMX'],
        "SERVER": ['Linux']
    }

    def __init__(self, ip, application_name, interface_port=None, monitor_type='JMX'
                 , host_group_names=None, monitor_template_names=None
                 , http_test_resource='/api/it/ping', http_test_port=8080):
        self.ip = ip
        if application_name:
            self.application_name = application_name
        else:
            raise ZabbixAPIException("请输入应用名称.......")
        self.monitor_type = monitor_type if monitor_type else 'JMX'
        if self.monitor_type == 'JMX':
            self.interface_type = 4
        else:
            self.interface_type = 1
        self.interface_port = interface_port if interface_port else self.default_interface_port[self.monitor_type]
        self.host_group_names = host_group_names if host_group_names else self.default_host_groups[self.monitor_type]
        if monitor_template_names:
            self.monitor_template_names = self.default_template_names[self.monitor_type]
            self.monitor_template_names.extend(monitor_template_names)
        else:
            self.monitor_template_names = self.default_template_names[self.monitor_type]
        self.http_test_resource = http_test_resource
        self.http_test_port = http_test_port
        self.build_zabbix_server()
        self.host_group_ids = []
        self.template_ids = []

    def build_zabbix_server(self):
        try:
            self.api_url = 'http://{0}/api_jsonrpc.php' \
                .format(self.zabbix_servers[self.monitor_type])
        except Exception as e:
            raise ZabbixAPIException("检查你输入的监控类型是否正确,目前支持:JMX, SERVER两种类型")

    def __str__(self, *args, **kwargs):
        return super().__str__(*args, **kwargs)


class ZabbixClient:
    """
    Generic Zabbix API Client
    - login
    - get auth
    """
    global_header = {
        'Content-Type': 'application/json-rpc',
        'User-Agent': 'python-zabbix-client',
        'Cache-Control': 'no-cache'
    }

    def __init__(self, monitor_description, user_name=None, password=None, timeout=None):
        self.user_name = user_name if user_name else '1234'
        self.password = password if password else '456'
        self.session = requests.session()
        self.session.headers.update(self.global_header)
        self.auth = ''
        self.id = 0
        self.timeout = timeout if timeout else 20
        self.zabbix_api_url = monitor_description.api_url
        self.monitor_description = monitor_description

    def login(self):
        """
        login with given user_name and password, if None, use default user
        :param user_name:
        :param password:
        :return: result,auth key
        """
        self.auth = self.user.login(user=self.user_name, password=self.password)

    def api_version(self):
        return self.apiinfo.version()

    def get_exiting_or_create_hostgroup(self):
        """
        get exiting host group name to avoid host group not existing error
        :param host_group_names:
        :return:
        """
        print(self.monitor_description.host_group_names)
        host_groups = self.hostgroup.get(
            filter={
                'name': self.monitor_description.host_group_names
            })
        logger.info(str(host_groups) + "is found")

        existing_group_names = set([item['name'] for item in host_groups])
        different_groups = set(self.monitor_description.host_group_names).difference(existing_group_names)

        host_group_ids = [{'groupid': item['groupid']} for item in host_groups]

        for item in different_groups:
            new_group = self.hostgroup.create(
                name=item
            )
            host_group_ids.append({'groupid': new_group['groupids'][0]})
        # for item in different_groups:
        #     new_groups = self.hostgroup.create(
        #         name=item
        #     )
        #     host_group_ids.extend({"groupids": new_groups['groupid']})
        self.monitor_description.host_group_ids = host_group_ids
        return host_group_ids

    def get_existing_templates(self):
        """
                get exiting monitor templates
                :param monitor_template:
                :return:
                """
        templates = self.template.get(filter={
            "host": self.monitor_description.monitor_template_names
        })

        for item in templates:
            self.monitor_description.template_ids.append({"templateid": item['templateid']})
        return templates

    def create_host(self):
        """
        create new host
        """
        created_host = self.host.create(
            host=self.monitor_description.ip,
            interfaces=[{
                "type": self.monitor_description.interface_type,
                "main": 1,
                "useip": 1,
                "ip": self.monitor_description.ip,
                "dns": "",
                "port": self.monitor_description.interface_port
            }],
            groups=self.monitor_description.host_group_ids
        )
        new_host_id = created_host['hostids'][0]
        return new_host_id

    def create_trigger(self, host_name, scenario_name, step_name):

        trigger_template = '{host_name}:web.test.rspcode[{scenario_name},{step_name}]'.format(host_name=host_name,
                                                                                              scenario_name=scenario_name,
                                                                                              step_name=step_name)
        trigger_template = "{" + trigger_template
        trigger_statement = ''
        for i in range(1, 4):
            expression = trigger_template + '.last(#{index})'.format(index=i) + '}#200'
            if i == 1:
                trigger_statement += expression
            else:
                trigger_statement += ' & ' + expression

        trigger_response = self.trigger.create({
            "description": scenario_name,
            "expression": trigger_statement,
            "priority": 5
        })

        return trigger_response

    def link_template_to_host(self, host_id):
        """
        :param host_id:
        :return:
        """
        if host_id:
            self.host.update(
                templates=self.monitor_description.template_ids,
                hostid=host_id
            )
            return host_id

    def create_monitor(self):
        print(self.user_name, "登陆")
        self.login()
        print("开始确认是否有host group %s" % self.monitor_description.host_group_names)
        self.get_exiting_or_create_hostgroup()
        print("开始确认是有已经存在模版 %s" % self.monitor_description.monitor_template_names)
        self.get_existing_templates()
        print("开始创建主机 %s" % self.monitor_description.ip)
        host_id = self.create_host()
        print("开始连接主机: %s和模版:%s " % (self.monitor_description.ip, self.monitor_description.monitor_template_names))
        self.link_template_to_host(host_id)
        if self.monitor_description.monitor_type == 'SERVER':
            print("开始为主机 %s 创建Web Test scenario" % self.monitor_description.ip)
            self.create_httptest(host_id)
        print("%s 创建成功" % self.monitor_description.ip)
        return host_id

    def create_httptest(self, host_id):
        """
        create http test like ping /api/it/ping
        :param host_id:
        :param name:
        :param ip:
        :param port:
        :param http_test_resource:
        :return:
        """

        if host_id:
            httptest_url = "http://{ip}:{port}{resource_url}".format(ip=self.monitor_description.ip
                                                                     , port=self.monitor_description.http_test_port
                                                                     ,
                                                                     resource_url=self.monitor_description.http_test_resource)

            name = "{ip}_{port}_{application_name}" \
                .format(ip=self.monitor_description.ip, port=self.monitor_description.http_test_port
                        , application_name=self.monitor_description.application_name)
            httptest_request = {
                'retries': '1',
                'status': '0',
                'agent': 'Mozilla/5.0 (Windows NT 6.1; WOW64) '
                         'AppleWebKit/535.8 (KHTML, like Gecko) Chrome/17.0.940.0 Safari/535.8',
                'steps': [
                    {
                        'no': '1',
                        'status_codes': '200',
                        'posts': '',
                        'variables': '',
                        'timeout': '15',
                        'url': httptest_url,
                        'required': '',
                        'name': name
                    }
                ],
                'authentication': '0',
                'macros': '',
                'hostid': host_id,
                'variables': '',
                'delay': '30',
                'http_password': '',
                'name': name
            }

            http_test = self.httptest.create(httptest_request)
            # create trigger
            self.create_trigger(self.monitor_description.ip, name, name)
            return http_test

    def do_request(self, method, params=None):
        request_json = {
            'jsonrpc': '2.0',
            'method': method,
            'params': params or {},
            'id': self.id,
        }

        if method != 'apiinfo.version' and self.auth:
            request_json['auth'] = self.auth

        logger.info("sending: %s", json.dumps(request_json, indent=4, separators=(',', ':')))
        response = self.session.post(
            self.monitor_description.api_url,
            data=json.dumps(request_json),
            timeout=self.timeout
        )
        logger.info("Response Code : %s", str(response.status_code))

        response.raise_for_status()

        if not len(response.text):
            raise ZabbixAPIException("没有返回值")
        try:
            response_json = json.loads(response.text)
        except ValueError:
            raise ZabbixAPIException("不能解析JSON %s" % response.text)

        logger.info("sending: %s", json.dumps(request_json, indent=4, separators=(',', ':')))

        self.id += 1

        if 'error' in response_json:
            if 'data' not in response_json['error']:
                response_json['error']['data'] = 'No Data'
            msg = "Error {code}: {message},{data}".format(
                code=response_json['error']['code'],
                message=response_json['error']['message'],
                data=response_json['error']['data']
            )
            raise ZabbixAPIException(msg, response_json['error']['code'])
        return response_json

    def __getattr__(self, item):
        """
        auto create Zabbix API Client
        :param item:
        :return:
        """
        return ZabbixAPIObjectClass(item, self)


class ZabbixAPIObjectClass(object):
    """
    Zabbix API Object for API client
    """

    def __init__(self, name, parent):
        self.name = name
        self.parent = parent

    def __getattr__(self, item):
        """
        dynamic create a method (get,create,update,delete, or others)
        :param item:
        :return:
        """

        def fn(*args, **kwargs):
            if args and kwargs:
                raise TypeError('只能输入一种参数,value或者 key=value形式')

            return self.parent.do_request('{0}.{1}'.format(self.name, item),
                                          args or kwargs)['result']

        return fn


if __name__ == '__main__':

    # try:
    #     reload(sys)
    #     sys.setdefaultencoding('utf-8')
    # except Exception:
    #     pass

    parser = argparse.ArgumentParser(description="创建Zabbix监控")
    # parser = OptionParser()
    parser.add_argument("-t", '--monitor_template_names',
                        help="输入模版名称,默认为JMX:Template JMX Generic,SERVER:Template OS Linux", default=[], type=str,
                        nargs='+')
    parser.add_argument("-i", '--ip', help="输入HOST的IP地址")
    parser.add_argument("-m", '--monitor_type', help="监控类型:JMX or SERVER", default='JMX')
    parser.add_argument("-f", '--interface_port', help="监控代理端口:agent interface port,JMX 默认是10011,Server默认端口是10050")
    parser.add_argument("-g", '--host_group_names', help="host group names", default=[], type=str, nargs='+')
    parser.add_argument("-p", '--http_test_resource', help="服务检查URL,默认为/api/it/ping", default='/api/it/ping')
    parser.add_argument("-P", '--http_test_port', help="服务检查端口,默认为8080端口", default=8080)
    parser.add_argument("-u", '--username', help="zabbix 用户名")
    parser.add_argument("-w", '--password', help="zabbix 用户密码")
    parser.add_argument("-a", '--application_name', help="应用名称")

    options = parser.parse_args()
    if options.ip is None:
        options.ip = input("请输入host的IP地址:")
        if len(options.ip) == 0:
            options.ip = input("请输入host的IP地址,此为必填项")

        options.application_name = input("请输入应用名称:")
        if len(options.application_name) == 0:
            options.application_name = input("请输入应用名称,此为必填项")

        options.monitor_type = input("请输入添加监控类型默认为JMX:")
        if len(options.monitor_type) == 0:
            options.monitor_type = 'JMX'

        options.host_group_names = input("请输入主机需要添加的组名,默认JMX 为JMX 组,"
                                         "SERVER 为Linux组,如果不是添加这两个组的话,请使用格式: group1 group2 :")
        if len(options.host_group_names) == 0:
            options.host_group_names = []
        options.monitor_template_names = input("请输入需要添加的模版名称,默认为JMX和LINUX,"
                                               "如果有额外的,请使用格式:template1,template2")
        if len(options.monitor_template_names) == 0:
            options.monitor_template_names = []
        options.http_test_resource = input("请输入web test的路径:默认为/api/it/ping")
        if len(options.http_test_resource) == 0:
            options.http_test_resource = "/api/it/ping"
        options.http_test_port = input("请输入WEB TEST的端口号,默认是8080")
        if len(options.http_test_port) == 0:
            options.http_test_port = 8080

        options.username = input("请输入使用的用户名,可以使用默认用户名创建")
        options.password = input("请输入使用的密码,可以使用默认用户名的密码")

    monitor_description = MonitorDescription(ip=options.ip, application_name=options.application_name
                                             , interface_port=options.interface_port
                                             , monitor_type=options.monitor_type
                                             , host_group_names=options.host_group_names
                                             , monitor_template_names=options.monitor_template_names
                                             , http_test_resource=options.http_test_resource
                                             , http_test_port=options.http_test_port)

    # print(monitor_description.host_group_names)
    # print(monitor_description.monitor_template_names)
    client = ZabbixClient(user_name=options.username, password=options.password
                          , monitor_description=monitor_description)
    client.create_monitor()
