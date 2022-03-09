package fr.open.roman.unitedcooking.controller;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Controller;

import fr.open.roman.unitedcooking.models.enums.ERole;
import fr.open.roman.unitedcooking.payload.request.SignupAdminRequest;
import fr.open.roman.unitedcooking.payload.request.SignupMemberRequest;
import fr.open.roman.unitedcooking.service.AdminService;
import fr.open.roman.unitedcooking.service.CategoryService;
import fr.open.roman.unitedcooking.service.DeviceService;
import fr.open.roman.unitedcooking.service.IngredientService;
import fr.open.roman.unitedcooking.service.MemberService;
import fr.open.roman.unitedcooking.service.RoleService;
import fr.open.roman.unitedcooking.service.TypeService;

@Controller
public class initController {

	private final RoleService roleService;
	private final MemberService memberService;
	private final AdminService adminService;
	private final CategoryService categoryService;
	private final TypeService typeService;
	private final DeviceService deviceService;
	private final IngredientService ingredientService;

	public initController(RoleService roleService, MemberService memberService, AdminService adminService, 
			CategoryService categoryService, TypeService typeService, DeviceService deviceService, 
			IngredientService ingredientService) {
		super();
		this.roleService = roleService;
		this.memberService = memberService;
		this.adminService = adminService;
		this.categoryService = categoryService;
		this.typeService = typeService;
		this.deviceService = deviceService;
		this.ingredientService = ingredientService;
	}


	@PostConstruct
	private void init() {
		initRoles();
		initCategories();
		initTypes();
		initDevices();
		initIngredients();
		initAdmins();
		initMembers();
	}

	private void initRoles() {
		if(roleService.recoveryAllRoles().size() < 1) {
			roleService.createRole(ERole.ROLE_MEMBER);
			roleService.createRole(ERole.ROLE_ADMIN);
		}
	}
	
	private void initMembers() {
		if(memberService.recoveryAllMembers().size() < 1) {
			SignupMemberRequest member1 = new SignupMemberRequest();
			member1.setPseudo("mKw");
			member1.setPassword("azerty");
			member1.setEmail("mkw@gmail.com");
			member1.setName("K");
			member1.setSurname("M");
			memberService.createMember(member1);
		
		}
	}

	private void initAdmins() {
		if(adminService.recoveryAllAdmins().size() < 1) {
			SignupAdminRequest admin1 = new SignupAdminRequest();
			admin1.setPseudo("admin");
			admin1.setEmail("admin@open.com");
			admin1.setPassword("azerty");
			admin1.setPhoneNumber("0123456789");
			adminService.createAdmin(admin1);
		}
	}

	private void initCategories() {
		if(categoryService.recoveryAllCategories().size() < 1) {
			categoryService.createCategory("Entrée");
			categoryService.createCategory("Plat");
			categoryService.createCategory("Dessert");
			categoryService.createCategory("Goûter");
		}
	}
	
	private void initIngredients() {
		if(ingredientService.recoveryAllIngredients().size() < 1) {
			ingredientService.createIngredient("Oignon");
			ingredientService.createIngredient("Lait");
			ingredientService.createIngredient("Oeuf");
			ingredientService.createIngredient("Pate");
			ingredientService.createIngredient("Sel");
			ingredientService.createIngredient("Poivre");
			ingredientService.createIngredient("Fromage");
		}
	}

	private void initDevices() {
		if(deviceService.recoveryAllDevices().size() < 1) {
			deviceService.createDevice("Thermomix");	
			deviceService.createDevice("Four");	
			deviceService.createDevice("Micro-onde");	
			deviceService.createDevice("Poêlon");	
			deviceService.createDevice("Caquelon");	
			deviceService.createDevice("Thermomix");
		}
	}

	private void initTypes() {
		if(typeService.recoveryAllTypes().size() < 1) {
			typeService.createType("Healthy food");
			typeService.createType("Junk food");
		}
	}

}
