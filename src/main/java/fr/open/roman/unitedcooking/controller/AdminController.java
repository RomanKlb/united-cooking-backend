package fr.open.roman.unitedcooking.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import fr.open.roman.unitedcooking.models.exception.notfound.NotFoundCookingRecipeException;
import fr.open.roman.unitedcooking.models.exception.notfound.NotfoundAdminException;
import fr.open.roman.unitedcooking.service.AdminService;
import fr.open.roman.unitedcooking.service.CookingRecipeService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/unitedcooking/admin")
public class AdminController {
	
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	private final AdminService adminService;
	private final CookingRecipeService cookingRecipeService;
	
	public AdminController(AdminService adminService, CookingRecipeService cookingRecipeService) {
		super();
		this.adminService = adminService;
		this.cookingRecipeService = cookingRecipeService;
	}
	
	@ExceptionHandler(javax.validation.ConstraintViolationException.class)
	@ResponseStatus(code=HttpStatus.UNPROCESSABLE_ENTITY)
	public List<String> processInvalidDataWithDetails(ConstraintViolationException exception) {
		return exception.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.toList());
	}
	@ExceptionHandler(fr.open.roman.unitedcooking.models.exception.notfound.NotFoundException.class)
	@ResponseStatus(code = HttpStatus.CONFLICT)
	public String processNotFoundException(Exception exception) {
		return exception.getMessage();
	}

	@PostMapping("/moderate/{idCookingRecipe}/{idMember}")
	public void moderateOneCookingRecipe(@PathVariable Long idCookingRecipe, @PathVariable Long idAdmin) 
			throws NotFoundCookingRecipeException, NotfoundAdminException {
		log.info("moderateOneCookingRecipe in AdminController est appelée");
		if(cookingRecipeService.existsById(idCookingRecipe)) {
			adminService.moderateOneOfCookingRecipe(cookingRecipeService.recoveryCookingRecipeById(idCookingRecipe).get(), idAdmin);
		}
		throw new NotFoundCookingRecipeException("La recette n'a pas été trouvé pour permettre la modération");
	}
	
}
