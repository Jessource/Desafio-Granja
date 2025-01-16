package com.precojusto.granja.controllers;
import com.precojusto.granja.mappers.CreateDuck;
import com.precojusto.granja.mappers.DuckList;
import com.precojusto.granja.mappers.ObjectReturn;
import com.precojusto.granja.model.Duck;
import com.precojusto.granja.model.Sale;
import com.precojusto.granja.services.DuckService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public ResponseEntity<?> createDuck(@RequestBody CreateDuck duck) {
        try {
            return ResponseEntity.ok(duckService.save(duck));
        } catch(Exception e) {
            return ResponseEntity.badRequest().body(new ObjectReturn(e.getMessage()));
        }
    }


    @GetMapping("/paginated")
    public Page<Duck> getAllPaginatedDucks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "saleDate") String sortBy,
            @RequestParam(defaultValue = "desc") String sortDirection
    ) {
        Sort sort = Sort.by(sortBy);
        sort = sortDirection.equalsIgnoreCase("asc") ? sort.ascending() : sort.descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return duckService.listAllDucks(pageable);
    }

    @GetMapping
    @Operation(summary = "Listar todos os patos", description = "Obtém uma lista de todos os pato cadastrados no sistema.")
    public ResponseEntity<List<DuckList>> getAllDucks() {
        return ResponseEntity.ok(duckService.findAll());
    }
}
