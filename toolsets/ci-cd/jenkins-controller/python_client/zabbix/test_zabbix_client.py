# -*- coding: utf-8 -*-


from unittest import TestCase

from zabbix.zabbixclient import MonitorDescription, ZabbixClient, logger


class TestMonitorDescription(TestCase):
    """
    Test Monitor Description
    """
    def test_default_value(self):
        """
        Test Default values
        """
        monitor_description = MonitorDescription(application_name='test',ip='10.0.0.1')
        self.assertEqual(monitor_description.ip, "10.0.0.1")
        self.assertEqual(monitor_description.monitor_type, 'JMX')
        self.assertEqual(monitor_description.host_group_names, ['JMX'])
        self.assertEqual(monitor_description.monitor_template_names, ["Template JMX Generic"])
        self.assertEqual(monitor_description.api_url, "http://java.zabbix.dooioo.com/api_jsonrpc.php")
        self.assertEqual(monitor_description.http_test_port, 9600)
        self.assertEqual(monitor_description.http_test_resource, "/api/it/ping")
        self.assertEqual(monitor_description.interface_port, 10811)
        self.assertEqual(monitor_description.interface_type, 4)

    def test_server_monitor_description(self):
        monitor_description = MonitorDescription(application_name='ap',ip='10.0.0.1', monitor_type="SERVER", interface_port=8080
                                                 , host_group_names=['Linux'], monitor_template_names=['Linux']
                                                 , http_test_port=9000)
        self.assertEqual(monitor_description.ip, "10.0.0.1")
        self.assertEqual(monitor_description.monitor_type, 'SERVER')
        self.assertEqual(monitor_description.host_group_names, ['Linux'])
        self.assertEqual(monitor_description.monitor_template_names, ['Template JMX Generic', 'Linux'])
        self.assertEqual(monitor_description.api_url, "http://server.zabbix.dooioo.com/api_jsonrpc.php")
        self.assertEqual(monitor_description.http_test_port, 9000)
        self.assertEqual(monitor_description.interface_port, 8080)
        self.assertEqual(monitor_description.interface_type, 1)


class TestZabbixClient(TestCase):
    def setUp(self):
        monitor_description = MonitorDescription(ip='10.0.0.1')
        self.z_client = ZabbixClient(monitor_description)

    def test_login(self):
        self.z_client.login()
        logger.debug(self.z_client.auth)
        self.assertIsNotNone(self.z_client.auth)

    def test_api_info(self):
        api_info = self.z_client.api_version()
        logger.debug(api_info)
        self.assertIsNotNone(api_info)

    def test_get_existing_host_group_name(self):
        self.z_client.login()
        host_group_names = self.z_client.get_exiting_host_group_ids()
        self.assertEqual(host_group_names[0]['name'], 'JMX')

    def test_get_multiple_existing_host_group_name(self):
        self.z_client.login()
        self.z_client.monitor_description.host_group_names.append('Solr')
        host_group_ids = self.z_client.get_exiting_host_group_ids()
        self.assertEqual(host_group_ids,[{"groupid":'8'},{"groupid":'9'}])

    def test_get_existing_templates(self):
        self.z_client.login()
        templates = self.z_client.get_existing_templates()
        self.assertEqual(templates[0]['host'], 'Template JMX Generic')

    def test_get_multiple_templates(self):
        self.z_client.monitor_description = MonitorDescription(ip='10.0.0.1'
                                                               , monitor_template_names=['Template App IMAP Service']
                                                               )
        self.z_client.login()
        templates = self.z_client.get_existing_templates()
        self.assertEqual(templates[1]['host'], 'Template JMX Generic')
        self.assertEqual(templates[0]['host'], 'Template App IMAP Service')

    def test_create_new_monitor_for_host(self):
        self.z_client.monitor_description = MonitorDescription(ip='10.22.16.136')
        self.z_client.create_monitor()

    def test_get_trigger_info():
        self.z_client.login()
        triggers = self.z_client.trigger.get()
        
