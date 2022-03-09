package fr.open.roman.unitedcooking.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import fr.open.roman.unitedcooking.models.Type;
import fr.open.roman.unitedcooking.repositories.TypeRepository;
import fr.open.roman.unitedcooking.service.TypeService;

@Service
public class TypeServiceImpl implements TypeService{

	private final TypeRepository typeRepository;

	public TypeServiceImpl(TypeRepository typeRepository) {
		super();
		this.typeRepository = typeRepository;
	}
	

	@Override
	public void createType(String name) {
		typeRepository.save(new Type(name));
	}

	@Override
	public Optional<Type> recoveryTypeByName(String name) {
		return typeRepository.findByName(name);
	}

	@Override
	public Optional<Type> recoveryTypeById(Long id) {
		return typeRepository.findById(id);
	}

	@Override
	public List<Type> recoveryAllTypes() {
		return typeRepository.findAll();
	}
	
	
}
