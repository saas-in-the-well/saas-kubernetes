# kafka-chat
kafka sample application

- #### kafka_2.12-3.7.0.tgz 다운로드 및 설치
   https://kafka.apache.org/downloads

- ##### kafka 압축 해제 및 경로 이동
  - cd {UserPath}/kafka_2.12-3.7.0/bin

- ##### zookeeper 실행
  - zookeeper-server-start ../config/zookeeper.properties

- #### Socket Server Settings
  - vi {UserPath}/kafka_2.12-3.7.0/config/server.properties
  - #listeners=PLAINTEXT://:9092 => listeners=PLAINTEXT://localhost:9092 (linlisteners 수정)

- #### kafka 실행
  - kafka-server-start ../config/server.properties

- #### API 호출 http://localhost:8080/kafka/send/message
  - ChatController broadcastGroupMessage -> KafkaTemplate Topic 생성 및 메시지 전달
  - ![postman](./img/postman.png)

- ### log 확인
![log](./img/sample0.png)
![log](./img/sample1.png)

---

- #### 토픽 목록 조회
  - kafka-topics --bootstrap-server localhost:9092 --list
- #### 토픽 생성
  - kafka-topics --bootstrap-server localhost:9092 --partitions 1 --create --topic {토픽명}
