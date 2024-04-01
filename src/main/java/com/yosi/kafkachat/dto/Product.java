package com.yosi.kafkachat.dto;

import lombok.Data;

@Data
public class Product {
    private String productId;
    private String productName;
    private int quantity;
    private ProductOptions options;
}
