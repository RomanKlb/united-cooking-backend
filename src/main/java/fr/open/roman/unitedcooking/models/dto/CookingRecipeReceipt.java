package fr.open.roman.unitedcooking.models.dto;

import java.time.LocalTime;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class CookingRecipeReceipt {

	@NotNull(message = "Le nom de la recette ne peut pas être vide")
	@NotBlank(message = "Le nom de la recette doit être complété")
	private String name;
	
	@NotNull(message = "Le temps de préparation ne peut pas être vide")
	@NotBlank(message = "Le temps de préparation doit être complété")
	private LocalTime preparationTime;
	
	@NotNull(message = "Le temps de cuisson ne peut pas être vide")
	@NotBlank(message = "Le temps de cuisson doit être complété")
	private LocalTime cookingTime;
	
	@NotNull(message = "La description ne peut pas être vide")
	@NotBlank(message = "La description doit être complété")
	@Length(max = 10000, message = "La description doit être de maximum 10 000 caractères")
	private String description;
	
	@NotNull(message = "Les ingrédients ne peuvent pas être vide")
	@NotBlank(message = "Les ingrédients doivent être complété")
	private List<String> ingredients;
	
	private List<String> devices;
	
	@NotNull(message = "La catégorie ne peut pas être vide")
	@NotBlank(message = "La catégorie doit être complété")
	private String categoryName;
	
	private String typeName;
	
	@NotNull(message = "Le membre doit être présent")
	private String memberPseudo;
}
