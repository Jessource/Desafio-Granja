package com.precojusto.granja.repositories;

import com.precojusto.granja.model.Duck;
import com.precojusto.granja.model.Sale;
import org.hibernate.query.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;
import java.util.UUID;

@Repository
public interface SaleRepository extends JpaRepository<Sale, UUID> {
    Sale findByDucks(List<Duck> ducks);
}