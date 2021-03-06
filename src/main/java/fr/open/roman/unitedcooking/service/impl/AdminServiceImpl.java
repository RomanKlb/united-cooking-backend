package fr.open.roman.unitedcooking.service.impl;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import fr.open.roman.unitedcooking.models.Admin;
import fr.open.roman.unitedcooking.models.CookingRecipe;
import fr.open.roman.unitedcooking.models.Role;
import fr.open.roman.unitedcooking.models.enums.ERole;
import fr.open.roman.unitedcooking.models.exception.notfound.NotfoundAdminException;
import fr.open.roman.unitedcooking.payload.request.SignupAdminRequest;
import fr.open.roman.unitedcooking.repositories.AdminRepository;
import fr.open.roman.unitedcooking.service.AdminService;
import fr.open.roman.unitedcooking.service.CookingRecipeService;
import fr.open.roman.unitedcooking.service.RoleService;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	PasswordEncoder encoder;

	private final AdminRepository adminRepository;
	private final RoleService roleService;
	private final CookingRecipeService cookingRecipeService;

	public AdminServiceImpl(AdminRepository adminRepository, RoleService roleService, CookingRecipeService cookingRecipeService) {
		super();
		this.adminRepository = adminRepository;
		this.roleService = roleService;
		this.cookingRecipeService = cookingRecipeService;
	}


	@Override
	public Admin createAdmin(@Valid SignupAdminRequest signupAdminRequest) {
		Admin admin = new Admin(signupAdminRequest.getPseudo(), encoder.encode(signupAdminRequest.getPassword()), signupAdminRequest.getEmail(),
				signupAdminRequest.getPhoneNumber());

		Set<Role> roles = new HashSet<>();
		Role adminRole = roleService.recoveryRoleName(ERole.ROLE_ADMIN)
				.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
		roles.add(adminRole);
		Role memberRole = roleService.recoveryRoleName(ERole.ROLE_MEMBER)
				.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
		roles.add(memberRole);
		admin.setRoles(roles);
		return adminRepository.save(admin);
	}

	@Override
	public Optional<Admin> recoveryAdminById(Long id) {
		return adminRepository.findById(id);
	}
	
	@Override
	public Optional<Admin> recoveryAdminByPseudo(String pseudo) {
		return adminRepository.findByPseudo(pseudo);
	}

	@Override
	public Boolean existsByPseudo(String pseudo) {
		return adminRepository.existsByPseudo(pseudo);
	}

	@Override
	public Boolean existsByEmail(String email) {
		return adminRepository.existsByEmail(email);
	}

	@Override
	public List<Admin> recoveryAllAdmins() {
		return adminRepository.findAll();
	}

	@Override
	public boolean deleteAdmin(Long id) {
		if(adminRepository.existsById(id)) {
			adminRepository.deleteById(id);
			return true;
		} else {
			return false;
		}
	}


	@Override
	public void moderateOneOfCookingRecipe(CookingRecipe cookingRecipe, Long idAdmin) throws NotfoundAdminException {
		if(adminRepository.existsById(idAdmin)) {
			cookingRecipe.setAdmin(adminRepository.findById(idAdmin).get());
			cookingRecipe.setModerateCookingRecipe(LocalDateTime.now());
			cookingRecipeService.patchModerateByAdmin(cookingRecipe);
		}
		throw new NotfoundAdminException("L'admin n'a pas ??t?? trouv?? pour permettre la mod??ration de la recette " + cookingRecipe.getName());
	}

}
