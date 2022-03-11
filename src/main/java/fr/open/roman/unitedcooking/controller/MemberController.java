package fr.open.roman.unitedcooking.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.open.roman.unitedcooking.models.exception.notfound.NotFoundCookingRecipeException;
import fr.open.roman.unitedcooking.models.exception.notfound.NotFoundMemberException;
import fr.open.roman.unitedcooking.service.MemberService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/unitedcooking/member")
public class MemberController {

	Logger log = LoggerFactory.getLogger(this.getClass());

	private final MemberService memberService;

	public MemberController(MemberService memberService) {
		super();
		this.memberService = memberService;
	}


	@PostMapping("/{idCookingRecipe}/{idMember}")
	public void addFavorites(@PathVariable Long idCookingRecipe, @PathVariable Long idMember) 
			throws NotFoundMemberException, NotFoundCookingRecipeException {
		memberService.addToListOfFavoritesCookingRecipes(idCookingRecipe, idMember);
	}
}
