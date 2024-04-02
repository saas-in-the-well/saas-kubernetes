package com.yosi.kafkachat.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class Order {
    private OrderInfo orderInfo;
    private CustomerInfo customerInfo;
    private PaymentInfo paymentInfo;
    private DeliveryInfo deliveryInfo;
    private List<Product> products;
    private String customerMemo;
}
