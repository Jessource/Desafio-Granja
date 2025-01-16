package com.precojusto.granja.mappers;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
public class DuckList {
    private UUID id;
    private String name;
    private SimplifiedDuck mother;
    private Boolean available;
    private Character sex;
    private BigDecimal saleValue;
}
