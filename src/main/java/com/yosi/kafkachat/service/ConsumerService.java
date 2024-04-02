package com.yosi.kafkachat.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ConsumerService {

    @KafkaListener(topics = "${spring.kafka.consumer.group-id.dev}", groupId = "group-dev")
    public void receiveMessage(String message) {
        log.info("[group-dev] receive message : {}", message);
    }

    @KafkaListener(topics = "${spring.kafka.consumer.group-id.stg}", groupId = "group-stg")
    public void stgReceiveMessage(String message) {
        log.info("[group-stg] receive message : {}", message);
    }
}
