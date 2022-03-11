package fr.open.roman.unitedcooking.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import fr.open.roman.unitedcooking.models.CookingRecipe;
import fr.open.roman.unitedcooking.models.dto.CookingRecipeReceipt;
import fr.open.roman.unitedcooking.models.exception.notfound.NotFoundCategoryException;
import fr.open.roman.unitedcooking.models.exception.notfound.NotFoundMemberException;
import fr.open.roman.unitedcooking.models.exception.notfound.NotFoundTypeException;


public interface CookingRecipeService {

	CookingRecipe createCookingRecipeAndAddInListOfCreatedRecipesOfMember(@Valid CookingRecipeReceipt cookingRecipeReceipt) 
			throws NotFoundCategoryException, NotFoundTypeException, NotFoundMemberException;
	
	Optional<CookingRecipe> recoveryCookingRecipeById(Long id);
	
	List<CookingRecipe> recoveryAllCookingRecipes();
	
	boolean deleteCookingRecipe(Long id);
	
	Boolean existsByName(String name);

	Boolean existsById(Long id);
}
