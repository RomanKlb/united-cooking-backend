package fr.open.roman.unitedcooking.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import fr.open.roman.unitedcooking.models.Admin;
import fr.open.roman.unitedcooking.models.Role;
import fr.open.roman.unitedcooking.models.enums.ERole;
import fr.open.roman.unitedcooking.payload.request.SignupAdminRequest;
import fr.open.roman.unitedcooking.repositories.AdminRepository;
import fr.open.roman.unitedcooking.service.AdminService;
import fr.open.roman.unitedcooking.service.RoleService;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	PasswordEncoder encoder;
	
	private final AdminRepository adminRepository;
	private final RoleService roleService;
	
	public AdminServiceImpl(AdminRepository adminRepository, RoleService roleService) {
		super();
		this.adminRepository = adminRepository;
		this.roleService = roleService;
	}

	
	@Override
	public void createAdmin(@Valid SignupAdminRequest signupAdminRequest) {
		Admin admin = new Admin(signupAdminRequest.getPseudo(), encoder.encode(signupAdminRequest.getPassword()), signupAdminRequest.getEmail(),
				signupAdminRequest.getPhoneNumber());
		
		Set<Role> roles = new HashSet<>();
			Role adminRole = roleService.recoveryRoleName(ERole.ROLE_ADMIN)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(adminRole);
		admin.setRoles(roles);
		adminRepository.save(admin);
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

}
