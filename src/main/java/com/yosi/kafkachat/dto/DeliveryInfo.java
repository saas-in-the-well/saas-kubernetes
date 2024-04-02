package com.yosi.kafkachat.dto;

import lombok.Data;

@Data
public class DeliveryInfo {
    private String address;
    private String postalCode;
    private String deliveryNotes;
    private String trackingNumber;
}
