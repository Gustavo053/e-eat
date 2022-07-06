package com.eeat.restaurantservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class OrderUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String orderContent;
    private String complement;
    private Long restaurantId;
    private Long userId;
    private Long addressId;

    @Enumerated(EnumType.STRING)
    private EOrderStatus status;
}
