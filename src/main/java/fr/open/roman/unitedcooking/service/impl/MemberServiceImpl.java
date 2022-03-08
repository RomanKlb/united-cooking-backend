package fr.open.roman.unitedcooking.service.impl;

import java.util.HashSet;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import fr.open.roman.unitedcooking.models.Member;
import fr.open.roman.unitedcooking.models.Role;
import fr.open.roman.unitedcooking.models.enums.ERole;
import fr.open.roman.unitedcooking.payload.request.SignupMemberRequest;
import fr.open.roman.unitedcooking.repositories.MemberRepository;
import fr.open.roman.unitedcooking.service.MemberService;
import fr.open.roman.unitedcooking.service.RoleService;

@Service
public class MemberServiceImpl implements MemberService{
	@Autowired
	PasswordEncoder encoder;
	
	private final RoleService roleService;
	private final MemberRepository memberRepository;
	
	public MemberServiceImpl(MemberRepository memberRepository, RoleService roleService) {
		super();
		this.roleService = roleService;
		this.memberRepository = memberRepository;
	}

	
	@Override
	public void createMember(@Valid SignupMemberRequest signupMemberRequest) {
		Member member = new Member(signupMemberRequest.getPseudo(), encoder.encode(signupMemberRequest.getPassword()), signupMemberRequest.getEmail(),
				signupMemberRequest.getName(), signupMemberRequest.getSurname());
		
		Set<Role> roles = new HashSet<>();
			Role memberRole = roleService.recoveryRoleName(ERole.ROLE_MEMBER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(memberRole);
		member.setRoles(roles);
		memberRepository.save(member);
	}

	@Override
	public Optional<Member> recoveryMemberByPseudo(String pseudo) {
		return memberRepository.findByPseudo(pseudo);
	}

	@Override
	public Boolean existsByPseudo(String pseudo) {
		return memberRepository.existsByPseudo(pseudo);
	}

	@Override
	public Boolean existsByEmail(String email) {
		return memberRepository.existsByEmail(email);
	}

	@Override
	public List<Member> recoveryAllMembers() {
		return memberRepository.findAll();
	}

}
