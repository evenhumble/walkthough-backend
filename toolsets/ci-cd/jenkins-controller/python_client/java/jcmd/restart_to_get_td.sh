#!/usr/bin/env bash

function error_exit(){
  if ["$?" -ne "0"]; then
    echo "[ERROR]: 脚本执行异常"
    exit 1
  fi
}

sshpass -p $3 ssh -t -o StrictHostKeychecking=no  $2@$1
error_exit

echo " 传输get_threaddump_tomcat.sh"
echo "tomcat 地址是: $4"
yes | pscp -pw $3 /root/jenkins/scripts/thread_dump/get_threaddump_tomcat.sh $2@$1:$4
error_exit
sshpass -p $3 ssh -t -o StrictHostKeychecking=no $2@$1 "cd $4;sh get_threaddump_tomcat.sh $4"