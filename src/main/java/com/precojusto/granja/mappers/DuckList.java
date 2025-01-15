package com.precojusto.granja.mappers;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Setter
@Getter
public class DuckList {
    private UUID id;
    private String name;
    private List<SimplifiedDuck> children;
    private SimplifiedDuck mother;
    private Boolean available;
    private Character sex;
}
