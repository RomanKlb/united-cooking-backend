package fr.open.roman.unitedcooking.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import fr.open.roman.unitedcooking.models.Ingredient;


public interface IngredientService {

	void createIngredient(@Valid String name);

	Optional<Ingredient> recoveryIngredientByName(String name);
	Optional<Ingredient> recoveryIngredientById(Long id);

	List<Ingredient> recoveryAllIngredients();
}
