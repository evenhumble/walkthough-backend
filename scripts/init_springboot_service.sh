#!/usr/bin/env bash

if [ ! -n "$1" ]
then
    echo "please set your module name"
    echo "Usage: init_springboot_service.sh <module_name>"
fi

MODULE=$1
BASE_DIR=`pwd`
SRC_FOLDER=../$MODULE/src/main/java
RES_FOLDER=../$MODULE/src/main/resources
cd $SRC_FOLDER

packages="io.dh.spring.connectit.domain io.dh.spring.connectit.service io.dh.spring.connectit.repository io.dh.spring.connectit.web"
web_packages="dto converter"

for package in $packages
do
   mkdir -p $package
done

cd web

for web_package in $web_packages
do
    mkdir -p $web_package
done

#echo "start init application properties files"
#envs="test stage dev prod"
#cd $BASE_DIR
#cd $RES_FOLDER
#touch application.yml
#for env in $envs
#do
#    touch application-$env.yml
#done