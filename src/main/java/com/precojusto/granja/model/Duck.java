package com.precojusto.granja.model;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    @Column
    private Boolean available;

    @OneToMany
    @JoinTable(
            name = "duck_children",
            joinColumns = @JoinColumn(name = "parent_id"),
            inverseJoinColumns = @JoinColumn(name = "child_id")
    )
    private List<Duck> children;

    @ManyToOne
    @JoinColumn(name = "mother_id")
    @JsonBackReference
    private Duck mother;

    @Column(nullable = false)
    private Character sex;
}
