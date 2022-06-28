package com.eeat.restaurantservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Order {
    @Id
    private Long id;
    private String orderContent;
    private String complement;

    @ManyToOne
    private Restaurant restaurant;
    private Long userId;
    private Long addressId;
}
