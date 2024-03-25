# kafka-chat
kafka sample application

- #### kafka_2.12-3.7.0.tgz 다운로드 및 설치
   https://kafka.apache.org/downloads2

- #### 사용자 다운로드 압축 해제 및 경로 이동
   cd /Users/hyeongwon.lee/Desktop/kafka_2.12-3.7.0/bin

- ##### zookeeper 실행
   zookeeper-server-start ../config/zookeeper.properties

- #### Socket Server Settings
   vi {User Path}/kafka_2.12-3.7.0/config/server.properties
   #listeners=PLAINTEXT://:9092 => listeners=PLAINTEXT://localhost:9092 (linlisteners 수정)

- #### kafka 실행
   kafka-server-start ../config/server.properties

- #### API 호출 http://localhost:8080/kafka/send/message
   ChatController broadcastGroupMessage -> KafkaTemplate Topic 생성 및 메시지 전달
