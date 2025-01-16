package com.precojusto.granja.mappers;

import lombok.Getter;
import lombok.Setter;
import java.util.UUID;

@Setter
@Getter
public class CreateDuck {
    private String name;
    private UUID mother;
    private Character sex;
}
