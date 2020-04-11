ssh-keygen -t rsa
cat /root/.ssh/id_rsa.pub >> /root/.ssh/authorized_keys
chmod 0600 /root/.ssh/authorized_keys



# ./hdfs namenode -format -clusterId CID-8bf63244-0510-4db6-a949-8f74b50f2be9
# java.io.IOException: There appears to be a gap in the edit log


./hadoop-daemon.sh start namenode

#http://10.114.27.117:50070/dfshealth.html#tab-overview

#org.apache.hadoop.hdfs.server.common.InconsistentFSStateException: Director