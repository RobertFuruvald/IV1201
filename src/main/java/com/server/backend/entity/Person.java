package com.server.backend.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "person")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer personId;

    private String name;
    private String surname;
    private String pnr;
    private String email;
    private String password;

    @Column(name = "role_id")
    private Integer roleId;

    private String username;
}
