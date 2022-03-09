package fr.open.roman.unitedcooking.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import fr.open.roman.unitedcooking.models.Role;
import fr.open.roman.unitedcooking.models.enums.ERole;

public interface RoleService {

	void createRole(@Valid ERole name);

	Optional<Role> recoveryRoleName(ERole name);
	
	List<Role> recoveryAllRoles();
	
}
