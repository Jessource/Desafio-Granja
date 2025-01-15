package com.precojusto.granja.mappers;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class RegisterSale {
    private UUID customerId;
    private List<UUID> ducks;
}
