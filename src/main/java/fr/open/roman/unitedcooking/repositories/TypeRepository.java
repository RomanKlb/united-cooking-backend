package fr.open.roman.unitedcooking.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.open.roman.unitedcooking.models.Type;

@Repository
public interface TypeRepository extends JpaRepository<Type, Long>{

	Optional<Type> findByName(String name);
}
