package fr.open.roman.unitedcooking.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.open.roman.unitedcooking.models.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{

	Optional<Category> findByName(String name);
}
