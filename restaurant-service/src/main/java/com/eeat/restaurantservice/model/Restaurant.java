package com.eeat.restaurantservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Restaurant {
    @Id
    private Long id;
    private String name;
    private Integer cnpj;
}
