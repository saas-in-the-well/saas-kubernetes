package com.yosi.kafkachat.service;

import com.yosi.kafkachat.dto.Order;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProducerService {

    @Value("${spring.kafka.consumer.group-id.dev}")
    private String devTopic;

    @Value("${spring.kafka.consumer.group-id.stg}")
    private String stgTopic;

    private final KafkaTemplate<String, String> kafkaTemplate;
    public void sendMessage(String message) {
        log.info("send message : {}", message);
        kafkaTemplate.send(devTopic, message);
    }

    public void order(Order order) {
        log.info("order : {}", order);
        kafkaTemplate.send(stgTopic, order.toString());
    }
}
