package fr.open.roman.unitedcooking.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
public class Ingredient {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message = "L'ingrédient ne peut pas être vide")
	@NotBlank(message = "L'ingrédient doit être complété")
	private String name;
	
	@JsonIgnore
	@ManyToMany(mappedBy = "ingredients")
	private List<CookingRecipe> cookingRecipes;

	public Ingredient(String name) {
		super();
		this.name = name;
	}
	
	
}
