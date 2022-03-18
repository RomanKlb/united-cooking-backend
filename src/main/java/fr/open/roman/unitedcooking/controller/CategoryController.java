package fr.open.roman.unitedcooking.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import fr.open.roman.unitedcooking.models.Category;
import fr.open.roman.unitedcooking.models.dto.CategoryReceipt;
import fr.open.roman.unitedcooking.models.exception.AlreadyTakenException;
import fr.open.roman.unitedcooking.service.CategoryService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/unitedcooking/category")
public class CategoryController {

	Logger log = LoggerFactory.getLogger(this.getClass());

	private final CategoryService categoryService;

	public CategoryController(CategoryService categoryService) {
		super();
		this.categoryService = categoryService;
	}

	@ExceptionHandler(javax.validation.ConstraintViolationException.class)
	@ResponseStatus(code=HttpStatus.UNPROCESSABLE_ENTITY)
	public List<String> processInvalidDataWithDetails(ConstraintViolationException exception) {
		return exception.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.toList());
	}
	@ExceptionHandler(fr.open.roman.unitedcooking.models.exception.AlreadyTakenException.class)
	@ResponseStatus(code = HttpStatus.CONFLICT)
	public String processAlreadyTakenException(Exception exception) {
		return exception.getMessage();
	}
	

	@PostMapping("/save")
	public Category addCategory(@Valid @RequestBody CategoryReceipt dto, BindingResult result) throws AlreadyTakenException {
		log.info("addCategory in CategoryController est appelée");
		if (categoryService.existsByName(dto.getName())) {
			log.info("le nom de cette category est déjà pris : " + dto.getName());
			throw new AlreadyTakenException("Le nom de cette category est déjà pris !");
		}
		if (result.hasErrors()) {
			List<ObjectError> errors = result.getAllErrors();
			for (ObjectError objectError : errors) {
				log.error("Validation error ->" + objectError.getDefaultMessage());
			}
		}
		return categoryService.createCategory(dto.getName());
	}

	@GetMapping("/all")
	public List<Category> recoveryAllCategories() {
		log.info("recoveryAllCategories in CategoryController est appelée");
		return categoryService.recoveryAllCategories();
	}
}
