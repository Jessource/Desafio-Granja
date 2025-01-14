package com.precojusto.granja.model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Duck")
public class Duck {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "uuid[]")
    private UUID[] children;

    @Column(nullable = false)
    private Double price;

    @Column(name = "mother_id")
    private UUID motherId;

    @Column(nullable = false)
    private Character sex;
}
