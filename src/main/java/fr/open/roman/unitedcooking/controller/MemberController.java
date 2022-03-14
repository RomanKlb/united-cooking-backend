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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import fr.open.roman.unitedcooking.models.CookingRecipe;
import fr.open.roman.unitedcooking.models.exception.notfound.NotFoundCookingRecipeException;
import fr.open.roman.unitedcooking.models.exception.notfound.NotFoundMemberException;
import fr.open.roman.unitedcooking.service.CookingRecipeService;
import fr.open.roman.unitedcooking.service.MemberService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/unitedcooking/member")
public class MemberController {

	Logger log = LoggerFactory.getLogger(this.getClass());

	private final MemberService memberService;
	private final CookingRecipeService cookingRecipeService;

	public MemberController(MemberService memberService, CookingRecipeService cookingRecipeService) {
		super();
		this.memberService = memberService;
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


	@PostMapping("/favorites/add/{idCookingRecipe}/{idMember}")
	public void addOneInFavorites(@PathVariable Long idCookingRecipe, @PathVariable Long idMember) 
			throws NotFoundMemberException, NotFoundCookingRecipeException {
		log.info("addOneInFavorites in MemberController est appelée");
		if(cookingRecipeService.existsById(idCookingRecipe)) {
			memberService.addToListOfFavoritesCookingRecipes(cookingRecipeService.recoveryCookingRecipeById(idCookingRecipe).get(), idMember);
		}
		throw new NotFoundCookingRecipeException("La recette n'a pas été trouvé pour permettre l'ajout en favoris");
		
	}
	
	@PostMapping("/favorites/delete/{idCookingRecipe}/{idMember}")
	public void deleteOneOfFavorites(@PathVariable Long idCookingRecipe, @PathVariable Long idMember) 
			throws NotFoundCookingRecipeException, NotFoundMemberException {
		log.info("deleteOneOfFavorites in MemberController est appelée");
		if(cookingRecipeService.existsById(idCookingRecipe)) {
			memberService.deleteOneToListOfFavoritesCookingRecipes(cookingRecipeService.recoveryCookingRecipeById(idCookingRecipe).get(), idMember);
		}
		throw new NotFoundCookingRecipeException("La recette n'a pas été trouvé pour permettre la suppression en favoris");
		
	}
	
	@GetMapping("/favorites/all/{idMember}")
	public List<CookingRecipe> recoveryAllFavorites(@PathVariable Long idMember) throws NotFoundMemberException{
		log.info("recoveryAllFavorites in MemberController est appelée");
		return memberService.recoveryAllFavorites(idMember);
	}
	
	@GetMapping("/created/all/{idMember}")
	public List<CookingRecipe> recoveryAllCreated(@PathVariable Long idMember) throws NotFoundMemberException{
		log.info("recoveryAllCreated in MemberController est appelée");
		return memberService.recoveryAllCreated(idMember);
	}
	
	
}
