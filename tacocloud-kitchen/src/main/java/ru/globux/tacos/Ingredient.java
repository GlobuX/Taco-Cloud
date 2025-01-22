package ru.globux.tacos;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
public class Ingredient {

  private final String name;
  private final Type type;

  public Ingredient() {
    this.type = null;
    this.name = null;
  }

  public Ingredient(Type type, String name) {
    this.type = type;
    this.name = name;
  }

  public enum Type {
    WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
  }

}
