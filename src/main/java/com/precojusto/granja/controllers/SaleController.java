package com.precojusto.granja.controllers;

import com.precojusto.granja.mappers.RegisterSale;
import com.precojusto.granja.model.Sale;
import com.precojusto.granja.services.SaleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<Sale> registerSale(@RequestBody RegisterSale body) {

        Sale sale = saleService.registerSale(body);
        return ResponseEntity.status(HttpStatus.CREATED).body(sale);
    }
}
