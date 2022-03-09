package fr.open.roman.unitedcooking.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import fr.open.roman.unitedcooking.models.CookingRecipe;
import fr.open.roman.unitedcooking.models.dto.CookingRecipeReceipt;
import fr.open.roman.unitedcooking.models.exception.NotFoundCategoryException;
import fr.open.roman.unitedcooking.models.exception.NotFoundMemberException;
import fr.open.roman.unitedcooking.models.exception.NotFoundTypeException;


public interface CookingRecipeService {

	CookingRecipe createcookingRecipe(@Valid CookingRecipeReceipt cookingRecipeReceipt) 
			throws NotFoundCategoryException, NotFoundTypeException, NotFoundMemberException;
	
	Optional<CookingRecipe> recoveryCookingRecipeById(Long id);
	
	List<CookingRecipe> recoveryAllCookingRecipes();
}
