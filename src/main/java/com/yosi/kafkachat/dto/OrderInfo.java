package com.yosi.kafkachat.dto;

import lombok.Data;

@Data
public class OrderInfo {
    private String orderId;
    private String orderDate;
    private String orderStatus;
}
