package com.yosi.kafkachat.service;

import com.yosi.kafkachat.dto.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Slf4j
@RequiredArgsConstructor
@Service
public class OrderCreateService {
    private static final int NUM_ORDERS = 10000;
    public List<Order> orderDataCreate() {
        return generateOrders(NUM_ORDERS);
    }

    private static List<Order> generateOrders(int numOrders) {
        List<Order> orders = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < numOrders; i++) {
            Order order = new Order();
            order.setOrderInfo(generateOrderInfo(random));
            order.setCustomerInfo(generateCustomerInfo(random));
            order.setPaymentInfo(generatePaymentInfo(random));
            order.setDeliveryInfo(generateDeliveryInfo(random));
            order.setProducts(generateProducts(random));
            order.setCustomerMemo("Customer memo for order " + (i + 1));
            orders.add(order);
        }

        return orders;
    }

    private static OrderInfo generateOrderInfo(Random random) {
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setOrderId("ODR" + (random.nextInt(900000) + 100000));
        // Assuming order dates between 2020-01-01 and 2024-12-31
        int year = 2020 + random.nextInt(5);
        int month = 1 + random.nextInt(12);
        int day = 1 + random.nextInt(28); // Assume all months have 28 days
        orderInfo.setOrderDate(String.format("%04d-%02d-%02d", year, month, day));
        String[] statuses = {"Pending", "Processing", "Shipped", "Delivered"};
        orderInfo.setOrderStatus(statuses[random.nextInt(statuses.length)]);
        return orderInfo;
    }

    private static CustomerInfo generateCustomerInfo(Random random) {
        CustomerInfo customerInfo = new CustomerInfo();
        customerInfo.setName("Customer " + (random.nextInt(9000) + 1000));
        customerInfo.setEmail("customer" + (random.nextInt(9000) + 1000) + "@example.com");
        customerInfo.setPhone("+1-" + (random.nextInt(900) + 100) + "-" + (random.nextInt(900) + 100) + "-" + (random.nextInt(9000) + 1000));
        return customerInfo;
    }

    private static PaymentInfo generatePaymentInfo(Random random) {
        PaymentInfo paymentInfo = new PaymentInfo();
        String[] methods = {"Credit Card", "Debit Card", "PayPal", "Cash on Delivery"};
        paymentInfo.setMethod(methods[random.nextInt(methods.length)]);
        paymentInfo.setAmount(random.nextInt(1000) + 50); // Random amount between 50 and 1049
        String[] statuses = {"Pending", "Processing", "Completed", "Failed"};
        paymentInfo.setStatus(statuses[random.nextInt(statuses.length)]);
        return paymentInfo;
    }

    private static DeliveryInfo generateDeliveryInfo(Random random) {
        DeliveryInfo deliveryInfo = new DeliveryInfo();
        deliveryInfo.setAddress("Address " + (random.nextInt(900) + 100));
        deliveryInfo.setPostalCode("PC" + (random.nextInt(90000) + 10000));
        deliveryInfo.setDeliveryNotes("Delivery notes for order " + (random.nextInt(9000) + 1000));
        deliveryInfo.setTrackingNumber("TRK" + (random.nextInt(900000) + 100000));
        return deliveryInfo;
    }

    private static List<Product> generateProducts(Random random) {
        List<Product> products = new ArrayList<>();
        int numProducts = random.nextInt(5) + 1; // Generate between 1 and 5 products per order
        for (int i = 0; i < numProducts; i++) {
            Product product = new Product();
            product.setProductId("PROD" + (random.nextInt(900) + 100));
            product.setProductName("Product " + (random.nextInt(9000) + 1000));
            product.setQuantity(random.nextInt(5) + 1); // Random quantity between 1 and 5
            // Sample product options
            ProductOptions options = new ProductOptions();
            options.setColor("Color " + (random.nextInt(10) + 1));
            options.setSize("Size " + (random.nextInt(5) + 1));
            product.setOptions(options);
            products.add(product);
        }
        return products;
    }
}
