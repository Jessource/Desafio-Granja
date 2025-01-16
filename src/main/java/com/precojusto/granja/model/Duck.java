package com.precojusto.granja.model;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
public class Duck {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column
    private Boolean available;

    @ManyToOne
    @JoinColumn(name = "mother_id")
    @JsonBackReference
    private Duck mother;

    @Column(nullable = false)
    private Character sex;

    @Column
    private BigDecimal saleValue;
}
