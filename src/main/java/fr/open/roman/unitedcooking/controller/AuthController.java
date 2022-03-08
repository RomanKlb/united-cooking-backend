package fr.open.roman.unitedcooking.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.open.roman.unitedcooking.payload.request.LoginRequest;
import fr.open.roman.unitedcooking.payload.request.SignupAdminRequest;
import fr.open.roman.unitedcooking.payload.request.SignupMemberRequest;
import fr.open.roman.unitedcooking.payload.response.JwtResponse;
import fr.open.roman.unitedcooking.payload.response.MessageResponse;
import fr.open.roman.unitedcooking.security.jwt.JwtUtils;
import fr.open.roman.unitedcooking.security.services.impl.UserDetailsImpl;
import fr.open.roman.unitedcooking.service.AdminService;
import fr.open.roman.unitedcooking.service.MemberService;
import fr.open.roman.unitedcooking.service.RoleService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/unitedcooking/auth")
public class AuthController {

	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	AdminService adminService;
	
	@Autowired
	MemberService memberService;
	
	@Autowired
	RoleService roleService;
	
	@Autowired
	JwtUtils jwtUtils;
	
	
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getPseudo(), loginRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();		
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());
		return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getId(), userDetails.getPseudo(), userDetails.getEmail(), roles));
	}
	
	
	@PostMapping("/signup")
	public ResponseEntity<?> registerMember(@Valid @RequestBody SignupMemberRequest signupMemberRequest) {
		if (adminService.existsByPseudo(signupMemberRequest.getPseudo()) || memberService.existsByPseudo(signupMemberRequest.getPseudo())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Le pseudo est déjà pris !"));
		}
		
		if (adminService.existsByEmail(signupMemberRequest.getEmail()) || memberService.existsByPseudo(signupMemberRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("L'adresse email est déjà prise !"));
		}
		
		memberService.createMember(signupMemberRequest);
		return ResponseEntity.ok(new MessageResponse("Vous êtes maintenant membre de United Cooking! Votre pseudo est : " + signupMemberRequest.getPseudo()));
	}
	
	
	@PostMapping("/signup-admin")
	public ResponseEntity<?> registerAdmin(@Valid @RequestBody SignupAdminRequest signupAdminRequest) {
		if (adminService.existsByPseudo(signupAdminRequest.getPseudo()) || memberService.existsByPseudo(signupAdminRequest.getPseudo())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Le pseudo est déjà pris !"));
		}
		
		if (adminService.existsByEmail(signupAdminRequest.getEmail()) || memberService.existsByPseudo(signupAdminRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("L'adresse email est déjà prise !"));
		}
		adminService.createAdmin(signupAdminRequest);
		return ResponseEntity.ok(new MessageResponse("Vous êtes maintenant admin de United Cooking! Votre pseudo est : " + signupAdminRequest.getPseudo()));
	}
}
