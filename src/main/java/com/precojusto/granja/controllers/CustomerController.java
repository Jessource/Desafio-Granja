package com.precojusto.granja.controllers;

import com.precojusto.granja.model.Customer;
import com.precojusto.granja.services.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
@Tag(name = "Cliente", description = "Endpoints para gerenciamento de clientes")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping
    @Operation(summary = "Cadastrar um novo cliente", description = "Cadastra um cliente no sistema com detalhes como nome e elegibilidade para desconto.")
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        return ResponseEntity.ok(customerService.save(customer));
    }

    @GetMapping
    @Operation(summary = "Listar todos os clientes", description = "Obtém uma lista de todos os clientes cadastrados no sistema.")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return ResponseEntity.ok(customerService.findAll());
    }
}

