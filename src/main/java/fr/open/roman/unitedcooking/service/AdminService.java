package fr.open.roman.unitedcooking.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import fr.open.roman.unitedcooking.models.Admin;
import fr.open.roman.unitedcooking.payload.request.SignupAdminRequest;

public interface AdminService {

	Admin createAdmin(@Valid SignupAdminRequest signupAdminRequest);
	
	Optional<Admin> recoveryAdminByPseudo(String pseudo);
	
	Boolean existsByPseudo(String pseudo);
	Boolean existsByEmail(String email);
	
	List<Admin> recoveryAllAdmins();
}
