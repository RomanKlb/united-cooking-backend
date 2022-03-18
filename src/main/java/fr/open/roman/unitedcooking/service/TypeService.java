package fr.open.roman.unitedcooking.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import fr.open.roman.unitedcooking.models.Type;
import fr.open.roman.unitedcooking.models.exception.AlreadyTypeCreatedException;

public interface TypeService {

	Type createType(@Valid String name) throws AlreadyTypeCreatedException;
	
	Optional<Type> recoveryTypeByName(String name);
	Optional<Type> recoveryTypeById(Long id);
	
	List<Type> recoveryAllTypes();
	
	boolean deleteType(Long id);

	boolean existsByName(String name);
}
