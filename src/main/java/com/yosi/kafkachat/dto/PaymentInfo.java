package com.yosi.kafkachat.dto;

import lombok.Data;

@Data
public class PaymentInfo {
    private String method;
    private int amount;
    private String status;
}
