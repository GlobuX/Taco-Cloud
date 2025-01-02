package ru.globux.tacos;

import ru.globux.tacos.Ingredient.Type;
import org.springframework.data.cassandra.core.mapping.UserDefinedType;
import java.util.Objects;

@UserDefinedType("ingredient")
public class IngredientUDT {
    private final String name;
    private final Ingredient.Type type;

    private IngredientUDT() {
        this.name = null;
        this.type = null;
    }

    public IngredientUDT(String name, Type type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public Ingredient.Type getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        IngredientUDT that = (IngredientUDT) o;
        return Objects.equals(name, that.name) && type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, type);
    }

    @Override
    public String toString() {
        return "IngredientUDT{" +
                "name='" + name + '\'' +
                ", type=" + type +
                '}';
    }
}
