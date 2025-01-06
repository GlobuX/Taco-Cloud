package ru.globux.tacos.data;

import org.springframework.data.repository.CrudRepository;
import ru.globux.tacos.Ingredient;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {

}
