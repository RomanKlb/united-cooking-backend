package fr.open.roman.unitedcooking.security.services.impl;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import fr.open.roman.unitedcooking.models.Admin;
import fr.open.roman.unitedcooking.models.Member;
import fr.open.roman.unitedcooking.repositories.AdminRepository;
import fr.open.roman.unitedcooking.repositories.MemberRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private AdminRepository adminRepository;
	

	@Transactional
	@Override
	public UserDetails loadUserByUsername(String pseudo) throws UsernameNotFoundException {
		
		Optional<Member> member = memberRepository.findByPseudo(pseudo);
		
		if(member.isPresent()) {
			return UserDetailsImpl.build(member.get());
		} else {
			Optional<Admin> admin = adminRepository.findByPseudo(pseudo);
			if(admin.isPresent()) {
				return UserDetailsImpl.build(admin.get());
			} else {
				throw new UsernameNotFoundException("User Not Found with pseudo: " + pseudo);
			}
		}
	}
}
