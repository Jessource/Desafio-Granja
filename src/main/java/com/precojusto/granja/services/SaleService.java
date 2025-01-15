package com.precojusto.granja.services;

import com.precojusto.granja.mappers.RegisterSale;
import com.precojusto.granja.model.Customer;
import com.precojusto.granja.model.Duck;
import com.precojusto.granja.model.Sale;
import com.precojusto.granja.repositories.CustomerRepository;
import com.precojusto.granja.repositories.DuckRepository;
import com.precojusto.granja.repositories.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class SaleService {

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private DuckRepository duckRepository;

    public Sale registerSale(RegisterSale body) {

        Customer customer = customerRepository.findById(body.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        List<Duck> ducks = duckRepository.findAllById(body.getDucks());
        if (ducks.isEmpty()) {
            throw new RuntimeException("Nenhum pato encontrado");
        }

        boolean hasUnavailableDuck = ducks.stream().anyMatch(duck -> !duck.getAvailable());
        if (hasUnavailableDuck) {
            throw new RuntimeException("Um ou mais patos selecionados não estão disponíveis para venda");
        }

        BigDecimal totalPrice = BigDecimal.valueOf(25);
        for (Duck duck : ducks) {
            if (duck.getChildren().isEmpty()) {
                totalPrice = new BigDecimal(70);
            } else if (duck.getChildren().size() == 1) {
                totalPrice = new BigDecimal(50);
            }
        }
        if (customer.getHasDiscount()) {
            totalPrice = totalPrice.multiply(new BigDecimal("0.8"));
        }

        // Registrar venda
        Sale sale = new Sale();
        sale.setCustomer(customer);
        sale.setDucks(ducks);
        sale.setTotalPrice(totalPrice);
        sale.setSaleDate(LocalDateTime.now());

        return saleRepository.save(sale);
    }
}
