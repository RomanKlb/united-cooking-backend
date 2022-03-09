package fr.open.roman.unitedcooking.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import fr.open.roman.unitedcooking.models.Member;
import fr.open.roman.unitedcooking.payload.request.SignupMemberRequest;

public interface MemberService {

	Member createMember(@Valid SignupMemberRequest signupMemberRequest);
	
	Optional<Member> recoveryMemberByPseudo(String pseudo);
	
	Boolean existsByPseudo(String pseudo);
	Boolean existsByEmail(String email);
	
	List<Member> recoveryAllMembers();
}
