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
./kafka-topics.sh --create --topic testtopic --bootstrap-server localhost:9092 --replication-factor 1 --partitions 2
 ```
#### Describe Topics
```bash
./kafka-topics.sh --describe --topic testtopic --bootstrap-server localhost:9092
 ```
#### List Topics
```bash
./kafka-topics.sh --list --bootstrap-server localhost:9092
```
