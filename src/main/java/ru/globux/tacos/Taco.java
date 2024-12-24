package ru.globux.tacos;

import lombok.Data;

import java.util.List;
import java.util.Objects;

@Data
public class Taco {
    private String name;
    private List<Ingredient> ingredients;
}
