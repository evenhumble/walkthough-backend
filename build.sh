#!/usr/bin/env bash


mvn clean package -Dmaven.test.skip=true
## mvn package -DskipTests