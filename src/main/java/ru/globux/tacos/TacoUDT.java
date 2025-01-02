package ru.globux.tacos;

import org.springframework.data.cassandra.core.mapping.UserDefinedType;

import java.util.List;
import java.util.Objects;

@UserDefinedType("taco")
public class TacoUDT {
    private final String name;
    private final List<IngredientUDT> ingredients;

//    private TacoUDT() {
//        this.name = null;
//        this.ingredients = null;
//    }

    public TacoUDT(String name, List<IngredientUDT> ingredients) {
        this.name = name;
        this.ingredients = ingredients;
    }

    public String getName() {
        return name;
    }

    public List<IngredientUDT> getIngredients() {
        return ingredients;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        TacoUDT tacoUDT = (TacoUDT) o;
        return Objects.equals(name, tacoUDT.name)
                && Objects.equals(ingredients, tacoUDT.ingredients);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, ingredients);
    }

    @Override
    public String toString() {
        return "TacoUDT{" +
                "name='" + name + '\'' +
                ", ingredients=" + ingredients +
                '}';
    }
}
