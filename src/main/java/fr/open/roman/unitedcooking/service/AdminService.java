package fr.open.roman.unitedcooking.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import fr.open.roman.unitedcooking.models.Admin;
import fr.open.roman.unitedcooking.models.CookingRecipe;
import fr.open.roman.unitedcooking.models.exception.notfound.NotfoundAdminException;
import fr.open.roman.unitedcooking.payload.request.SignupAdminRequest;

public interface AdminService {

	Admin createAdmin(@Valid SignupAdminRequest signupAdminRequest);
	
	Optional<Admin> recoveryAdminById(Long id);
	Optional<Admin> recoveryAdminByPseudo(String pseudo);
	
	Boolean existsByPseudo(String pseudo);
	Boolean existsByEmail(String email);
	
	List<Admin> recoveryAllAdmins();
	
	boolean deleteAdmin(Long id);

	void moderateOneOfCookingRecipe(CookingRecipe cookingRecipe, Long idAdmin) throws NotfoundAdminException;
}
