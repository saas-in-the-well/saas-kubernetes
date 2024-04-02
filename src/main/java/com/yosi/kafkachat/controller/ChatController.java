package com.yosi.kafkachat.controller;

import com.yosi.kafkachat.dto.Order;
import com.yosi.kafkachat.service.OrderCreateService;
import com.yosi.kafkachat.service.ProducerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@CrossOrigin
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/kafka")
public class ChatController {

    private final ProducerService producerService;
    private final OrderCreateService orderCreateService;

    @PostMapping("/send/message")
    public void broadcastGroupMessage(String message) {
        producerService.sendMessage(message);
    }

    @PostMapping("/order")
    public void order(@RequestBody Order order) {
        producerService.order(order);
    }

    @PostMapping("/create-order")
    public void orderDataCreate(){
        List<Order> orders = orderCreateService.orderDataCreate();
        for (Order order : orders) {
            producerService.order(order);
        }
    }
}
