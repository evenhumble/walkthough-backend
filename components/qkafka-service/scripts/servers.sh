#!/usr/bin/env bash
# starting zookeeper
bin/zookeeper-server-start.sh config/zookeeper.properties
# starting kafka server
bin/kafka-server-start.sh config/server.properties

# copy server configuration
cp config/server.properties config/server-1.properties
#config/server-1.properties:
#    broker.id=1
#    listeners=PLAINTEXT://:9093
#    log.dir=/tmp/kafka-logs-1

# starting another node
bin/kafka-server-start.sh config/server-1.properties &
# bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 3 --partitions 1 --topic my-replicated-topic
# bin/kafka-topics.sh --describe --zookeeper localhost:2181 --topic my-replicated-topic

# connector
# bin/connect-standalone.sh config/connect-standalone.properties config/connect-file-source.properties config/connect-file-sink.properties
