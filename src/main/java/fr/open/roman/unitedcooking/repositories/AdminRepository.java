package fr.open.roman.unitedcooking.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.open.roman.unitedcooking.models.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long>{

	Optional<Admin> findByPseudo(String pseudo);

	Boolean existsByPseudo(String pseudo);
	Boolean existsByEmail(String email);
}
