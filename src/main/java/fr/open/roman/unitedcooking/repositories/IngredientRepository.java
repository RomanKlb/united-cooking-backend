package fr.open.roman.unitedcooking.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.open.roman.unitedcooking.models.Ingredient;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long>{

	Optional<Ingredient> findByName(String name);
}
