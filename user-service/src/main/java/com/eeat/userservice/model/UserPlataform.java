package com.eeat.userservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class UserPlataform {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String login;
    private String password;
    private String email;

    @OneToOne(fetch = FetchType.EAGER, orphanRemoval = true, cascade = CascadeType.PERSIST)
    private Address address;
}
