package fr.open.roman.unitedcooking.service.impl;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import fr.open.roman.unitedcooking.models.Category;
import fr.open.roman.unitedcooking.repositories.CategoryRepository;
import fr.open.roman.unitedcooking.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	private final CategoryRepository categoryRepository;
	
	public CategoryServiceImpl(CategoryRepository categoryRepository) {
		super();
		this.categoryRepository = categoryRepository;
	}

	
	@Override
	public Category createCategory(@Valid String name) {
		return categoryRepository.save(new Category(name));
	}

	@Override
	public Optional<Category> recoveryCategoryByName(String name) {
		return categoryRepository.findByName(name);
	}
	
	@Override
	public Optional<Category> recoveryCategoryById(Long id) {
		return categoryRepository.findById(id);
	}

	@Override
	public List<Category> recoveryAllCategories() {
		return categoryRepository.findAll();
	}

	@Override
	public boolean deleteCategory(Long id) {
		if(categoryRepository.existsById(id)) {
			categoryRepository.deleteById(id);
			return true;
		} else {
			return false;
		}
	}


	@Override
	public boolean existsByName(String name) {
		return categoryRepository.existsByName(name);
	}

}
