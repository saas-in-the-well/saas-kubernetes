package com.yosi.kafkachat.controller;

import com.yosi.kafkachat.service.ProducerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@CrossOrigin
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/kafka")
public class ChatController {

    private final ProducerService producerService;

    @PostMapping("/send/message")
    public void broadcastGroupMessage(String message) {
        producerService.sendMessage(message);
    }
}
