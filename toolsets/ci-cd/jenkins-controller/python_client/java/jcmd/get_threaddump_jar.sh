#!/usr/bin/env bash


sshpass -p $3 ssh -t -o StrictHostKeychecking=no $2@$1 "cd $4;sh jarautodeploy.sh $4 $6 $7 $8 $1 $9 \"${10}\""


