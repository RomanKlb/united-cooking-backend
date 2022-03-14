package fr.open.roman.unitedcooking.controller;

import java.io.IOException;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import fr.open.roman.unitedcooking.models.exception.notfound.NotFoundCookingRecipeException;
import fr.open.roman.unitedcooking.service.ImageService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/unitedcooking/cooking-recipe")
public class ImageController {
	
	private final ImageService imageService;
	
	public ImageController(ImageService imageService) {
		super();
		this.imageService = imageService;
	}


	@PostMapping("/image/{id}")
	public void patchCookingRecipeImage(@PathVariable Long id, @RequestParam("file") MultipartFile file) throws NotFoundCookingRecipeException, IOException {
		imageService.saveFile(id, file);
	}
	
	@PostMapping("/image/{nom}")
	public void deleteCookingRecipeImage(@PathVariable String nom) throws IOException {
		imageService.deleteFile(nom);
	}
}
