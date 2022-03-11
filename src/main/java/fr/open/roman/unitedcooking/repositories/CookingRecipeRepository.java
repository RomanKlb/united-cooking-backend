package fr.open.roman.unitedcooking.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.open.roman.unitedcooking.models.CookingRecipe;

@Repository
public interface CookingRecipeRepository extends JpaRepository<CookingRecipe, Long>{

	Boolean existsByName(String name);
	
}
