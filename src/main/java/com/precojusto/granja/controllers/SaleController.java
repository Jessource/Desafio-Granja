package com.precojusto.granja.controllers;

import com.precojusto.granja.mappers.ObjectReturn;
import com.precojusto.granja.mappers.RegisterSale;
import com.precojusto.granja.model.Sale;
import com.precojusto.granja.services.SaleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.rmi.server.UID;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/sales")
@Tag(name = "Vendas", description = "Endpoints para vendas de patos")
public class SaleController {

    @Autowired
    private SaleService saleService;

    @PostMapping
    @Operation(summary = "Registra uma venda de patos")
    public ResponseEntity<?> registerSale(@RequestBody RegisterSale body) {
        try {
            Sale sale = saleService.registerSale(body);
            return ResponseEntity.status(HttpStatus.CREATED).body(sale);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ObjectReturn(e.getMessage()));
        }
    }
    @GetMapping("/paginated")
    public Page<Sale> getAllPaginatedSales(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "saleDate") String sortBy,
            @RequestParam(defaultValue = "desc") String sortDirection
    ) {
        Sort sort = Sort.by(sortBy);
        sort = sortDirection.equalsIgnoreCase("asc") ? sort.ascending() : sort.descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return saleService.listAllSales(pageable);
    }

    @GetMapping
    @Operation(summary = "lista todas as vendas realizadas")
    public ResponseEntity<List<Sale>> getAllSales() {
        List<Sale> sales = saleService.listAllSales();
        return ResponseEntity.ok(sales);
    }
}
