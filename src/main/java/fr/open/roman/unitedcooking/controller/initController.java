package fr.open.roman.unitedcooking.controller;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Controller;

import fr.open.roman.unitedcooking.models.enums.ERole;
import fr.open.roman.unitedcooking.service.AdminService;
import fr.open.roman.unitedcooking.service.MemberService;
import fr.open.roman.unitedcooking.service.RoleService;

@Controller
public class initController {

	private final RoleService roleService;
	private final MemberService memberService;
	private final AdminService adminService;
	
	public initController(RoleService roleService, MemberService memberService, AdminService adminService) {
		super();
		this.roleService = roleService;
		this.memberService = memberService;
		this.adminService = adminService;
	}
	
	
	@PostConstruct
	private void init() {
		initRole();
		
	}

	private void initRole() {
		if(roleService.recoveryAllRoles().size() < 1) {
			roleService.createRoleName(ERole.ROLE_MEMBER);
			roleService.createRoleName(ERole.ROLE_ADMIN);
		}
	}
	
	
}
