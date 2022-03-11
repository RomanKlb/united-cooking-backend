package fr.open.roman.unitedcooking.models.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
public class ReviewReceipt {

	@Length(max = 500, message = "Votre commentaire doit être composé d'un maximum de 500 caractères")
	String remark;
	
	@NotNull(message = "La note ne peut pas être vide")
	@NotBlank(message = "La note doit être complété")
	@Range(min = 0, max = 20, message = "La notation est compris entre 0 et 20")
	int rating;
	
	@NotNull(message = "Le membre ne peut pas être vide")
	@NotBlank(message = "Le membre doit être complété")
	String memberPseudo;
	
	@NotNull(message = "La recette ne peut pas être vide")
	@NotBlank(message = "La recette doit être complété")
	Long idCookingRecipe;
}
