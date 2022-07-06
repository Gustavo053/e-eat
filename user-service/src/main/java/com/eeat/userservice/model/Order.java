package com.eeat.userservice.model;

import lombok.Data;

@Data
public class Order {
    private String orderContent;
    private String complement;
    private Long restaurantId;
    private Long userId;
    private Long addressId;
    private EOrderStatus status;
}
