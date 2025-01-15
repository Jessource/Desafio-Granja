package com.precojusto.granja.controllers;
import com.precojusto.granja.mappers.DuckList;
import com.precojusto.granja.model.Duck;
import com.precojusto.granja.services.DuckService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ducks")
@Tag(name = "Pato", description = "Endpoints para gerenciamento de patos")
public class DuckController {

    @Autowired
    private DuckService duckService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    @Operation(summary = "Cadastrar um novo pato", description = "Cadastra um pato no sistema com detalhes como nome, mãe, filhos e sexo.")
    public ResponseEntity<Duck> createDuck(@RequestBody Duck duck) {
        return ResponseEntity.ok(duckService.save(duck));
    }

    @GetMapping
    @Operation(summary = "Listar todos os patos", description = "Obtém uma lista de todos os pato cadastrados no sistema.")
    public ResponseEntity<List<DuckList>> getAllDucks() {
        return ResponseEntity.ok(duckService.findAll());
    }
}
