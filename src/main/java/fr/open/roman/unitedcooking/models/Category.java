package fr.open.roman.unitedcooking.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import fr.open.roman.unitedcooking.models.enums.ECategory;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message = "Le nom de la catégorie ne peut pas être vide")
	@NotBlank(message = "Le nom de la catégorie doit être complété")
	private ECategory name;
	
	@JsonIgnore
	@ToString.Exclude
	@OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
	private List<CookingRecipe> cookingRecipes;

	
	public Category(
			@NotNull(message = "Le nom de la catégorie ne peut pas être vide") @NotBlank(message = "Le nom de la catégorie doit être complété") ECategory name) {
		super();
		this.name = name;
	}
	
}
