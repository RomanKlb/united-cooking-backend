package fr.open.roman.unitedcooking.service.impl;

import java.util.HashSet;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import fr.open.roman.unitedcooking.models.CookingRecipe;
import fr.open.roman.unitedcooking.models.Member;
import fr.open.roman.unitedcooking.models.Role;
import fr.open.roman.unitedcooking.models.enums.ERole;
import fr.open.roman.unitedcooking.models.exception.notfound.NotFoundMemberException;
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
	public Member createMember(@Valid SignupMemberRequest signupMemberRequest) {
		Member member = new Member(signupMemberRequest.getPseudo(), encoder.encode(signupMemberRequest.getPassword()), signupMemberRequest.getEmail(),
				signupMemberRequest.getName(), signupMemberRequest.getSurname());

		Set<Role> roles = new HashSet<>();
		Role memberRole = roleService.recoveryRoleName(ERole.ROLE_MEMBER)
				.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
		roles.add(memberRole);
		member.setRoles(roles);
		return memberRepository.save(member);
	}

	@Override
	public Optional<Member> recoveryMemberById(Long id) {
		return memberRepository.findById(id);
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

	@Override
	public boolean deleteMember(Long id) {
		if(memberRepository.existsById(id)) {
			memberRepository.deleteById(id);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void addToListOfCreatedCookingRecipes(Member member, CookingRecipe cookingRecipe) throws NotFoundMemberException {
		member.getCreatedCookingRecipes().add(cookingRecipe);
		memberRepository.save(member);
	}

	@Override
	public void deleteToListOfCreatedCookingRecipes(Member member, CookingRecipe cookingRecipe) {
		member.getCreatedCookingRecipes().remove(cookingRecipe);
		memberRepository.save(member);
	}

	@Override
	public void addToListOfFavoritesCookingRecipes(CookingRecipe cookingRecipe, Long idMember) throws NotFoundMemberException {
		Optional<Member> member = recoveryMemberById(idMember);
		if(member.isPresent()) {
				member.get().getFavoritesOfCookingRecipes().add(cookingRecipe);
				memberRepository.save(member.get());
		}
		throw new NotFoundMemberException("Le membre n'a pas été trouvé pour permettre l'enregistrement d'une recette en favori !");
	}


	@Override
	public void deleteOneToListOfFavoritesCookingRecipes(CookingRecipe cookingRecipe, Long idMember) throws NotFoundMemberException {
		Optional<Member> member = recoveryMemberById(idMember);
		if(member.isPresent()) {
				member.get().getFavoritesOfCookingRecipes().remove(cookingRecipe);
				memberRepository.save(member.get());
		}
		throw new NotFoundMemberException("Le membre n'a pas été trouvé pour permettre l'enregistrement d'une recette en favori !");
	}


	@Override
	public List<CookingRecipe> recoveryAllFavorites(Long idMember) throws NotFoundMemberException {
		if(memberRepository.existsById(idMember)) {
			return memberRepository.findById(idMember).get().getFavoritesOfCookingRecipes();
		}
		throw new NotFoundMemberException("Le membre n'a pas été trouvé pour permettre de retrouver sa liste de favoris");
	}


	@Override
	public List<CookingRecipe> recoveryAllCreated(Long idMember) throws NotFoundMemberException {
		if(memberRepository.existsById(idMember)) {
			return memberRepository.findById(idMember).get().getCreatedCookingRecipes();
		}
		throw new NotFoundMemberException("Le membre n'a pas été trouvé pour permettre de retrouver sa liste de création");
		
	}




}
