package fr.open.roman.unitedcooking.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import fr.open.roman.unitedcooking.models.Role;
import fr.open.roman.unitedcooking.models.enums.ERole;
import fr.open.roman.unitedcooking.repositories.RoleRepository;
import fr.open.roman.unitedcooking.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService{

	private final RoleRepository roleRepository;
	
	public RoleServiceImpl(RoleRepository roleRepository) {
		super();
		this.roleRepository = roleRepository;
	}

	
	@Override
	public void createRoleName(ERole name) {
		roleRepository.save(new Role(name));
	}
	
	@Override
	public Optional<Role> recoveryRoleName(ERole name) {
		return roleRepository.findByName(name);
	}


	@Override
	public List<Role> recoveryAllRoles() {
		return roleRepository.findAll();
	}

}
