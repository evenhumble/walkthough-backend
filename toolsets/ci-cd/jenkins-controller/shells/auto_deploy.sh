#!/usr/bin/env bash

function error_exit() {
  if [ "$?" -ne 0 ]; then
     echo -e "【ERROR】\n  deploy脚本执行异常,请检查jenkins内配置是否正确"
     exit 1
  fi
}

sshpass -p $3 ssh -t -o StrictHostKeychecking=no $2@$1
error_exit

sshpass -p $3 ssh -t -o StrictHostKeychecking=no $2@$1 "cd ~;mkdir -p $4"
error_exit

if [[ ${#12} -gt 0 ]] && [[ "${12}" = "rollback" ]]
then
  echo -e "------------------------------------------------------------------------"
  echo -e "\n开始自动回滚：\n"

  echo -e "[step01]下载回滚脚本..存放位置:$4"
  yes|pscp -pw $3 /root/jenkins/scripts/jarrollback_port.sh $2@$1:$4
  error_exit

  sshpass -p $3 ssh -t -o StrictHostKeychecking=no $2@$1 "cd $4;sh jarrollback_port.sh $4 $6 $7 $8 $1 $9 ${10} \"${11}\""
  error_exit

else
  echo -e "------------------------------------------------------------------------"
  echo -e "\n开始自动部署："

  #确认部署出的jar包
  ls $5|grep -q jar$
  if [ $? == 0 ]; then
    mv $5/$(ls $5 |grep jar |grep -v sources |grep -v javadoc |grep -v original$) ${5}/${7}`date -d today +"%Y%m%d%H%M"`.jar
    echo -e "\n[step01]构建包名为:$(ls $5 |grep jar |grep -v sources |grep -v javadoc |grep -v original$)\n"
  else
    echo -e "无法找到构建出的jar包"
    error_exit
  fi

  #jarpath=${5}"/ROOT.jar"
  #jarname=$(ls ${5}|grep jar$)
  jarpath=$5/$(ls $5 |grep jar$)

  #将部署的脚本传到目标服务器
  echo -e "[step02]下载自动部署脚本..存放位置:$4"
  yes|pscp -pw $3 /root/jenkins/scripts/jarautodeploy_port.sh $2@$1:$4
  error_exit

  #将部署的war包传到目标服务器
  echo -e "\n[step03]下载部署jar包..存放位置:$4"
  yes|pscp -pw $3 $5/$(ls $5 |grep jar |grep -v sources |grep -v javadoc |grep -v original$) $2@$1:$4
  error_exit

  #通过ssh远程执行jarautodeploy.sh
  sshpass -p $3 ssh -t -o StrictHostKeychecking=no $2@$1 "cd $4;sh jarautodeploy_port.sh $4 $6 $7 $8 $1 $9 ${10} \"${11}\""
  error_exit
fi