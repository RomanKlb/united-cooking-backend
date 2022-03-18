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

import fr.open.roman.unitedcooking.models.Type;
import fr.open.roman.unitedcooking.models.dto.TypeReceipt;
import fr.open.roman.unitedcooking.models.exception.AlreadyTakenException;
import fr.open.roman.unitedcooking.models.exception.AlreadyTypeCreatedException;
import fr.open.roman.unitedcooking.service.TypeService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/unitedcooking/type")
public class TypeController {

	Logger log = LoggerFactory.getLogger(this.getClass());

	private final TypeService typeService;

	public TypeController(TypeService typeService) {
		super();
		this.typeService = typeService;
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
	@ExceptionHandler(fr.open.roman.unitedcooking.models.exception.AlreadyTypeCreatedException.class)
	@ResponseStatus(code = HttpStatus.CONFLICT)
	public String processAlreadyTypeCreatedException(Exception exception) {
		return exception.getMessage();
	}
	

	@PostMapping("/save")
	public Type addType(@Valid @RequestBody TypeReceipt dto, BindingResult result) throws AlreadyTakenException, AlreadyTypeCreatedException {
		log.info("addType in TypeController est appelée");
		if (typeService.existsByName(dto.getName())) {
			log.info("le nom de cette type est déjà pris : " + dto.getName());
			throw new AlreadyTakenException("Le nom de ce type est déjà pris !");
		}
		if (result.hasErrors()) {
			List<ObjectError> errors = result.getAllErrors();
			for (ObjectError objectError : errors) {
				log.error("Validation error ->" + objectError.getDefaultMessage());
			}
		}
		return typeService.createType(dto.getName());
	}

	@GetMapping("/all")
	public List<Type> recoveryAllTypes() {
		log.info("recoveryAllTypes in TypeController est appelée");
		return typeService.recoveryAllTypes();
	}
}
