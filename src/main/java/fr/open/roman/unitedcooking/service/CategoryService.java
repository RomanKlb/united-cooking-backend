package fr.open.roman.unitedcooking.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import fr.open.roman.unitedcooking.models.Category;

public interface CategoryService {

	void createCategory(@Valid String name);
	
	Optional<Category> recoveryCategoryByName(String name);
	Optional<Category> recoveryCategoryById(Long id);
	
	List<Category> recoveryAllCategories();
}
