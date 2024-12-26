package ru.globux.tacos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;
import java.util.Objects;


public class Taco {
    @NotNull
    @Size(min = 5, message = "Name must be at least 5 characters long")
    private String name;

    @NotNull
    @Size(min = 1, message = "You must choose at least 1 ingredient")
    private List<Ingredient> ingredients;

    public Taco() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Taco taco = (Taco) o;
        return Objects.equals(name, taco.name) && Objects.equals(ingredients, taco.ingredients);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, ingredients);
    }

    @Override
    public String toString() {
        return "Taco{" +
                "name='" + name + '\'' +
                ", ingredients=" + ingredients +
                '}';
    }
}
