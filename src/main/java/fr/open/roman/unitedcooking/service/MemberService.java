package fr.open.roman.unitedcooking.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import fr.open.roman.unitedcooking.models.CookingRecipe;
import fr.open.roman.unitedcooking.models.Member;
import fr.open.roman.unitedcooking.models.exception.notfound.NotFoundMemberException;
import fr.open.roman.unitedcooking.payload.request.SignupMemberRequest;

public interface MemberService {

	Member createMember(@Valid SignupMemberRequest signupMemberRequest);
	
	Optional<Member> recoveryMemberById(Long id);
	Optional<Member> recoveryMemberByPseudo(String pseudo);
	
	Boolean existsByPseudo(String pseudo);
	Boolean existsByEmail(String email);
	
	List<Member> recoveryAllMembers();
	
	boolean deleteMember(Long id);

	void addToListOfCreatedCookingRecipes(Member member, CookingRecipe cookingRecipe) throws NotFoundMemberException;
	void addToListOfFavoritesCookingRecipes(CookingRecipe cookingRecipe, Long idMember) throws NotFoundMemberException;
	
	public void deleteToListOfCreatedCookingRecipes(Member member, CookingRecipe cookingRecipe);
	void deleteOneToListOfFavoritesCookingRecipes(CookingRecipe cookingRecipe, Long idMember) throws NotFoundMemberException;

	List<CookingRecipe> recoveryAllFavorites(Long idMember) throws NotFoundMemberException;
	List<CookingRecipe> recoveryAllCreated(Long idMember) throws NotFoundMemberException;

}
