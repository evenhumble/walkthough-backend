#!/usr/bin/env bash
# create topic
bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic test
# list topic
bin/kafka-topics.sh --list --zookeeper localhost:2181
# test topic
# send message
bin/kafka-console-producer.sh --broker-list localhost:9092 --topic test
# consume message
bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic test --from-beginning
