# -*- coding: utf-8 -*-


class ThreadInfo(object):
    def __init__(self, id, cpu, mem):
        self.id = id
        self.cpu = cpu
        self.mem = mem

class ThreadInfoReader(object):

    def __init__(self,file_path):
        self.file_path=file_path
        self.thread_infos= []


    def parse_dump_file(self):
        """
        pass thread dump file
        :return:
        """
        pass

    def top(self):
        """
        return top n waiting threads
        :param n:
        :return:
        """
        pass
