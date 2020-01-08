#!/usr/bin/env bash

function error_exit(){
  if ["$?" -ne 0]; then
    echo "[ERROR]: 脚本执行异常"
    exit 1
  fi
}

# $1 is tomcat location
TOMCAT_PATH=$1

if [ $TOMCAT_PATH ]
then
 echo "TOMCAT 地址是:$TOMCAT_PATH"
else
 echo "TOMCAT 地址没有输入,请输入TOMCAT地址作为此脚本的第一个参数"
 exit 1
fi

for k in $(jcmd -l | grep -v 'JCmd' | awk '{print $1}')
do
  if [ "`jcmd $k VM.system_properties | grep $TOMCAT_PATH | wc -L`" -gt 0 ]
  then
    echo "查找the PID for $TOMCAT_PATH"
    jcmd $k Thread.print > thread_$k_`date -d today +"%Y-%m-%d-%H_%M_%S"`.log
    sleep 10

    echo "======== 开始重启TOMCAT: 首先停止已有TOMCAT==================="
    cd $TOMCAT_PATH
    sh bin/shutdown.sh
    echo -e "检查$TOMCAT_PATH TOMCAT进程 进程ID:$k"
    sleep 10
    echo "kill tomcat thread 如果TOMCAT没有被shutdown"
    if  ps -p $k > /dev/null
    then
       echo "tomcat pid $k is still running, kill it"
       kill -9 $k
    fi
    echo "========开始重启TOMCAT============"
    sleep 10
    sh bin/startup.sh
    exit 0
 fi
done


echo "启动TOMCAT"
cd $TOMCAT_PATH
sh bin/startup.sh


