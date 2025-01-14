package com.precojusto.granja.controllers;
import com.precojusto.granja.model.Duck;
import com.precojusto.granja.services.DuckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ducks")
public class DuckController {

    @Autowired
    private DuckService duckService;

    @PostMapping
    public ResponseEntity<Duck> createDuck(@RequestBody Duck duck) {
        return ResponseEntity.ok(duckService.save(duck));
    }

    @GetMapping
    public ResponseEntity<List<Duck>> getAllDucks() {
        return ResponseEntity.ok(duckService.findAll());
    }
}
