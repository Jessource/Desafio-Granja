package com.precojusto.granja.services;

import com.precojusto.granja.model.Duck;
import com.precojusto.granja.repositories.DuckRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DuckService {

    @Autowired
    private DuckRepository duckRepository;

    public Duck save(Duck duck) {
        return duckRepository.save(duck);
    }

    public List<Duck> findAll() {
        return duckRepository.findAll();
    }
}