package fr.open.roman.unitedcooking.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import fr.open.roman.unitedcooking.models.Type;

public interface TypeService {

	Type createType(@Valid String name);
	
	Optional<Type> recoveryTypeByName(String name);
	Optional<Type> recoveryTypeById(Long id);
	
	List<Type> recoveryAllTypes();
}
