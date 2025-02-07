# Install Zookeeper

https://kafka.apache.org/downloads

#### Download 
```bash
kafka_2.13-3.9.0.tgz
```
#### Open Properties File and change Path
```bash
 \config\zookeeper.properties
```

#### Deploy Zookeeper
```bash
./zookeeper-server-start.sh ../config/zookeeper.properties
```

#### Deploy kafka
```bash
 ./kafka-server-start.sh ../config/server.properties
 ```
#### Create Topics
```bash
./kafka-topics.sh --create --topic topic-test --bootstrap-server localhost:9092 --replication-factor 1 --partitions 2
 ```
#### Describe Topics
```bash
./kafka-topics.sh --describe --topic topic-test --bootstrap-server localhost:9092
 ```
#### List Topics
```bash
./kafka-topics.sh --list --bootstrap-server localhost:9092
```
#### Kafka Console Consumer
```bash
 ./kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic topic-test --group group1
```

#### Kafka Console Producer
```bash
 ./bin/kafka-console-producer.sh --broker-list localhost:9092,localhost:9093,localhost:9094 --topic topic-test
```
#### Describe group Consumer
```bash
 ./bin/kafka-consumer-groups.sh --bootstrap-server localhost:9092 --group grupo1 --describe
```

