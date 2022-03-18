package fr.open.roman.unitedcooking.models.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
public class IngredientReceipt {

	@NotNull(message = "Le nom de l'ingrédient ne peut pas être vide")
	@NotBlank(message = "Le nom de l'ingrédient doit être remplis")
	private String name;
}
