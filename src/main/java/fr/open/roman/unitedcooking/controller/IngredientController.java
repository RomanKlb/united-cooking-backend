package fr.open.roman.unitedcooking.controller;

import java.util.List;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import fr.open.roman.unitedcooking.models.Ingredient;
import fr.open.roman.unitedcooking.models.dto.IngredientReceipt;
import fr.open.roman.unitedcooking.models.exception.AlreadyTakenException;
import fr.open.roman.unitedcooking.service.IngredientService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/unitedcooking/ingredient")
public class IngredientController {

	Logger log = LoggerFactory.getLogger(this.getClass());
	
	private final IngredientService ingredientService;

	public IngredientController(IngredientService ingredientService) {
		super();
		this.ingredientService = ingredientService;
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
	

	@PostMapping("/save")
	public Ingredient addIngredient(@Valid @RequestBody IngredientReceipt dto, BindingResult result) throws AlreadyTakenException {
		log.info("addIngredient in IngredientController est appelée");
		if (ingredientService.existsByName(dto.getName())) {
			log.info("le nom de cette ingrédient est déjà pris : " + dto.getName());
			throw new AlreadyTakenException("Le nom de cette ingrédient est déjà pris !");
		}
		if (result.hasErrors()) {
			List<ObjectError> errors = result.getAllErrors();
			for (ObjectError objectError : errors) {
				log.error("Validation error ->" + objectError.getDefaultMessage());
			}
		}
		return ingredientService.createIngredient(dto.getName());
	}

	@GetMapping("/all")
	public List<Ingredient> recoveryAllIngredients() {
		log.info("recoveryAllIngredients in IngredientController est appelée");
		return ingredientService.recoveryAllIngredients();
	}
}
