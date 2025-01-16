package com.precojusto.granja.services;

import com.precojusto.granja.mappers.CreateDuck;
import com.precojusto.granja.mappers.DuckList;
import com.precojusto.granja.model.Duck;
import com.precojusto.granja.model.Sale;
import com.precojusto.granja.repositories.DuckRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class DuckService {
    @Autowired
    private ModelMapper modelMapper;
    private static final Logger logger = LoggerFactory.getLogger(DuckService.class);
    @Autowired
    private DuckRepository duckRepository;

    public Duck save(CreateDuck duck) {
        Duck payload = new Duck();

        if (duck.getMother() != null) {
            Optional<Duck> mother = duckRepository.findById(duck.getMother());
            if (mother.isEmpty()) {
                throw new RuntimeException("Não existe uma pata com o UUID fornecido");
            }
            payload.setMother(mother.get());
        }

        payload.setName(duck.getName());
        payload.setSex(duck.getSex());
        payload.setAvailable(true);
        return duckRepository.save(payload);
    }

    public List<DuckList> findAll() {
        return duckRepository.findAll().stream().map(it -> modelMapper.map(it, DuckList.class)).toList();
    }

    public Page<Duck> listAllDucks(Pageable pageable) {
        return duckRepository.findAll(pageable);
    }

}