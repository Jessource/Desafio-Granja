package com.precojusto.granja.repositories;


import com.precojusto.granja.model.Duck;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface DuckRepository extends JpaRepository<Duck, UUID> {
    List<Duck> findAllByMother(Duck mother);
}
