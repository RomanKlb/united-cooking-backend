package fr.open.roman.unitedcooking.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import fr.open.roman.unitedcooking.models.CookingRecipe;
import fr.open.roman.unitedcooking.models.dto.CookingRecipeReceipt;
import fr.open.roman.unitedcooking.models.exception.AlreadyTakenException;
import fr.open.roman.unitedcooking.models.exception.notfound.NotFoundCategoryException;
import fr.open.roman.unitedcooking.models.exception.notfound.NotFoundCookingRecipeException;
import fr.open.roman.unitedcooking.models.exception.notfound.NotFoundMemberException;
import fr.open.roman.unitedcooking.models.exception.notfound.NotFoundTypeException;
import fr.open.roman.unitedcooking.service.CookingRecipeService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/unitedcooking/cooking-recipe")
public class CookingRecipeController {

	Logger log = LoggerFactory.getLogger(this.getClass());

	private final CookingRecipeService cookingRecipeService;

	public CookingRecipeController(CookingRecipeService cookingRecipeService) {
		super();
		this.cookingRecipeService = cookingRecipeService;
	}

	@ExceptionHandler(javax.validation.ConstraintViolationException.class)
	@ResponseStatus(code=HttpStatus.UNPROCESSABLE_ENTITY)
	public List<String> processInvalidDataWithDetails(ConstraintViolationException exception) {
		return exception.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.toList());
	}
	@ExceptionHandler(fr.open.roman.unitedcooking.models.exception.AlreadyTakenException.class)
	@ResponseStatus(code = HttpStatus.CONFLICT)
	public String processAlreadyTakenException(Exception exception) {
		return exception.getMessage();
	}
	@ExceptionHandler(fr.open.roman.unitedcooking.models.exception.notfound.NotFoundException.class)
	@ResponseStatus(code = HttpStatus.CONFLICT)
	public String processNotFoundException(Exception exception) {
		return exception.getMessage();
	}

	@PostMapping("/save")
	public CookingRecipe addCookingRecipe(@Valid @RequestBody CookingRecipeReceipt cookingRecipeReceipt, BindingResult result) 
			throws NotFoundCategoryException, NotFoundTypeException, NotFoundMemberException, AlreadyTakenException{
		log.info("addCookingRecipe in CookingRecipeController est appelée");
		if (cookingRecipeService.existsByName(cookingRecipeReceipt.getName())) {
			log.info("le nom de cette recette est déjà pris : " + cookingRecipeReceipt.getName());
			throw new AlreadyTakenException("Le nom de cette recette est déjà pris !");
		}
		if (result.hasErrors()) {
			List<ObjectError> errors = result.getAllErrors();
			for (ObjectError objectError : errors) {
				log.error("Validation error ->" + objectError.getDefaultMessage());
			}
		}
		return cookingRecipeService.createcookingRecipe(cookingRecipeReceipt);
	}

	@GetMapping("/{id}")
	public CookingRecipe recoveryOneCookingRecipe(@PathVariable Long id) throws NotFoundCookingRecipeException{
		log.info("recoveryOneCookingRecipe in CookingRecipeController est appelée");
		if(cookingRecipeService.existsById(id)) {
			Optional<CookingRecipe> cookingRecipe = cookingRecipeService.recoveryCookingRecipeById(id);
			return cookingRecipe.get();
		} else {
			throw new NotFoundCookingRecipeException("La recette avec pour id = " + id + " n'a pas été trouvé !");
		}
	}
	
	@GetMapping("/all")
	public List<CookingRecipe> recoveryAllCookingRecipes() {
		log.info("recoveryAllCookingRecipes in CookingRecipeController est appelée");
		return cookingRecipeService.recoveryAllCookingRecipes();
	}
}

