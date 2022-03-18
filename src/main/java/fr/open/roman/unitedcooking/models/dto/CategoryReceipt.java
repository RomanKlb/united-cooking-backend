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
public class CategoryReceipt {

	@NotNull(message = "Le nom de la catégorie ne peut pas être vide")
	@NotBlank(message = "Le nom de la catégorie doit être remplis")
	private String name;
}
