# # vim /etc/sysconfig/network
# # NETWORKING=yes  #使用网络
# # HOSTNAME=bigdata-senior01.chybinmy.com  #设置主机名
# vim /etc/hosts
# 192.168.100.10 bigdata-senior01.chybinmy.com
# service iptables status
# service iptables stop
# chkconfig iptables off
# vim /etc/sysconfig/selinux
# SELINUX=disabled


./bin/hdfs namenode -format
./sbin/start-dfs.sh