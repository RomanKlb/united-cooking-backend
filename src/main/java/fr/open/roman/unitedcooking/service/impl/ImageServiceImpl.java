package fr.open.roman.unitedcooking.service.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import fr.open.roman.unitedcooking.models.CookingRecipe;
import fr.open.roman.unitedcooking.models.Image;
import fr.open.roman.unitedcooking.models.exception.notfound.NotFoundCookingRecipeException;
import fr.open.roman.unitedcooking.service.CookingRecipeService;
import fr.open.roman.unitedcooking.service.ImageService;

@Service
public class ImageServiceImpl implements ImageService{

	private static final String DOSSIER_IMAGES = "src/main/webapp/images/";

	private final CookingRecipeService cookingRecipeService;

	public ImageServiceImpl(CookingRecipeService cookingRecipeService) {
		super();
		this.cookingRecipeService = cookingRecipeService;
	}

	@Override
	public void saveFile(Long id, MultipartFile multipartFile) throws IOException, NotFoundCookingRecipeException {
		Path chemin = Paths.get(DOSSIER_IMAGES);

		if (!Files.exists(chemin)) {
			Files.createDirectories(chemin);
		}

		Optional<CookingRecipe> cookingRecipe = cookingRecipeService.recoveryCookingRecipeById(id);

		if(cookingRecipe.isPresent()) {
			Image image = new Image(id.toString() + ".jpg", null);
			cookingRecipe.get().setImage(image);

			try (InputStream inputStream = multipartFile.getInputStream()) {
				Path cheminFichier = chemin.resolve(image.getName());
				Files.copy(inputStream, cheminFichier, StandardCopyOption.REPLACE_EXISTING);
			} catch (IOException ioe) {
				throw new IOException("Erreur d'écriture : " + image.getName(), ioe);
			}	
		} else {
			throw new NotFoundCookingRecipeException("Erreur : la recette n'a pas été trouvé pour permettre de sauvegarder l'image");
		}
	}

	@Override
	public void deleteFile(String nom) throws IOException {
		Path chemin = Paths.get(DOSSIER_IMAGES);
		if (!Files.exists(chemin)) {
			Files.createDirectories(chemin);
		}
		String image = chemin.toString() + "\\" + nom;
		File file = new File(image);
		if(file != null) {
			file.delete();
		}
	}




}
