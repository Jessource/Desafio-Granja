package com.precojusto.granja.model;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@Data
public class Duck {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @OneToMany
    private List<Duck> children;

    @Column(nullable = false)
    private Double price;

    @ManyToOne
    private Duck mother;

    @Column(nullable = false)
    private Character sex;
}
