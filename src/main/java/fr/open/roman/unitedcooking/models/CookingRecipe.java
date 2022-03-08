package fr.open.roman.unitedcooking.models;

import java.time.LocalTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

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
public class CookingRecipe {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
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
	
	@OneToOne(cascade = CascadeType.REMOVE)
	private Image image;
	
	@JsonIgnore
	@OneToMany(targetEntity=Review.class, mappedBy = "cookingRecipe", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	@ToString.Exclude
	private List<Review> reviews;
	
	@NotNull(message = "Les ingrédients ne peuvent pas être vide")
	@NotBlank(message = "Les ingrédients doivent être complété")
	@ToString.Exclude
	@OneToMany(targetEntity=Ingredient.class, fetch = FetchType.LAZY)
	@JoinTable(name = "ingredientsOfCookingRecipes")
	private List<Ingredient> ingredients;
	
	@ToString.Exclude
	@OneToMany(targetEntity=Device.class, fetch = FetchType.LAZY)
	@JoinTable(name = "devicesOfCookingRecipe")
	private List<Device> devices;
	
	@NotNull(message = "La catégorie ne peut pas être vide")
	@NotBlank(message = "La catégorie doit être complété")
	@ManyToOne
	private Category category;
	
	@ManyToOne
	private Type type;

	
	public CookingRecipe(
			@NotNull(message = "Le nom de la recette ne peut pas être vide") @NotBlank(message = "Le nom de la recette doit être complété") String name,
			@NotNull(message = "Le temps de préparation ne peut pas être vide") @NotBlank(message = "Le temps de préparation doit être complété") LocalTime preparationTime,
			@NotNull(message = "Le temps de cuisson ne peut pas être vide") @NotBlank(message = "Le temps de cuisson doit être complété") LocalTime cookingTime,
			@NotNull(message = "La description ne peut pas être vide") @NotBlank(message = "La description doit être complété") String description,
			@NotNull(message = "Les ingrédients ne peuvent pas être vide") @NotBlank(message = "Les ingrédients doivent être complété") List<Ingredient> ingredients,
			List<Device> devices,
			@NotNull(message = "La catégorie ne peut pas être vide") @NotBlank(message = "La catégorie doit être complété") Category category,
			Type type) {
		super();
		this.name = name;
		this.preparationTime = preparationTime;
		this.cookingTime = cookingTime;
		this.description = description;
		this.ingredients = ingredients;
		this.devices = devices;
		this.category = category;
		this.type = type;
	}

	
}
