package fr.open.roman.unitedcooking.service.impl;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import fr.open.roman.unitedcooking.models.Ingredient;
import fr.open.roman.unitedcooking.repositories.IngredientRepository;
import fr.open.roman.unitedcooking.service.IngredientService;

@Service
public class IngredientServiceImpl implements IngredientService{

	private final IngredientRepository ingredientRepository;
	
	public IngredientServiceImpl(IngredientRepository ingredientRepository) {
		super();
		this.ingredientRepository = ingredientRepository;
	}

	
	@Override
	public Ingredient createIngredient(@Valid String name) {
		return ingredientRepository.save(new Ingredient(name));
	}

	@Override
	public Optional<Ingredient> recoveryIngredientByName(String name) {
		return ingredientRepository.findByName(name);
	}

	@Override
	public Optional<Ingredient> recoveryIngredientById(Long id) {
		return ingredientRepository.findById(id);
	}

	@Override
	public List<Ingredient> recoveryAllIngredients() {
		return ingredientRepository.findAll();
	}

}
