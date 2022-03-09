package fr.open.roman.unitedcooking.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import fr.open.roman.unitedcooking.models.exception.NotFoundCookingRecipeException;

public interface ImageService {

	void saveFile(Long id, MultipartFile multipartFile) throws IOException, NotFoundCookingRecipeException;
	
	void deleteFile(String nom) throws IOException;
}

