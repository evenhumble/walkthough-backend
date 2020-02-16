# -*- coding: utf-8 -*-

from jenkinsapi.jenkins import Jenkins


class JenkinsTools:
    def __init__(self, host='http://localhost:8080/'):
        self.host = host
        self.jenkins = Jenkins(host)
        self.exclude_view = ["自动化测试", "调试用", "静态代码检查", "SQL注入日志配置检查", "Preview", "All"]

    def get_all_jobs(self):
        return self.jenkins.get_jobs()

    def get_job(self, job_name):
        return self.jenkins.get_job(job_name)

    def is_not_exclude_view(self, view_name):
        flag = True
        for view in self.exclude_view:
            flag = False if (view_name.find(view) >= 0) else True
            if not flag:
                return flag
        return flag

    def trigger_all_jobs(self):
        for name, view in self.jenkins.views.iteritems():
            if self.is_not_exclude_view(name):
                for job_name, job_url in view.get_job_dict().items():
                    # start invoke jobs
                    print("start trigger job ", job_name)
                    self.jenkins.get_job(job_name).invoke()

    def copy(self):
        self.jenkins.copy_job()

if __name__ == '__main__':
    JenkinsTools().trigger_all_jobs()
