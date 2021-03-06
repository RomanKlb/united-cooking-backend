package fr.open.roman.unitedcooking.models;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class CookingRecipe {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message = "Le nom de la recette ne peut pas être vide")
	@NotBlank(message = "Le nom de la recette doit être complété")
	private String name;
	
	@NotNull(message = "Le temps de préparation ne peut pas être vide")
	private LocalTime preparationTime;
	
	@NotNull(message = "Le temps de cuisson ne peut pas être vide")
	private LocalTime cookingTime;
	
	@NotNull(message = "La description ne peut pas être vide")
	@NotBlank(message = "La description doit être complété")
	@Length(max = 10000, message = "La description doit être de maximum 10 000 caractères")
	private String description;
	
	private LocalDateTime createdCookingRecipe;
	
	private LocalDateTime moderateCookingRecipe;
	
	@OneToOne(cascade = CascadeType.REMOVE)
	private Image image;
	
	@JsonIgnore
	@OneToMany(targetEntity=Review.class, mappedBy = "cookingRecipe", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	@ToString.Exclude
	private List<Review> reviews;
	
	@NotNull(message = "Les ingrédients ne peuvent pas être vide")
	@NotEmpty(message = "Les ingrédients doivent être complété")
	@ToString.Exclude
	@JsonIgnore
	@ManyToMany
//	@JoinTable(name = "ingredientsOfCookingRecipes")
	private List<Ingredient> ingredients;
	
	@ToString.Exclude
	@JsonIgnore
	@ManyToMany
//	@JoinTable(name = "devicesOfCookingRecipe")
	private List<Device> devices;
	
	@NotNull(message = "La catégorie ne peut pas être vide")
	@ManyToOne
	private Category category;
	
	@ManyToOne
	private Type type;
	
	@NotNull(message = "Le membre doit être présent")
	@ManyToOne
	private Member member;
	
	@OneToOne
	private Admin admin;

	public CookingRecipe() {
		createdCookingRecipe = LocalDateTime.now();
	}

	public CookingRecipe(
			@NotNull(message = "Le nom de la recette ne peut pas être vide") @NotBlank(message = "Le nom de la recette doit être complété") String name,
			@NotNull(message = "Le temps de préparation ne peut pas être vide") @NotBlank(message = "Le temps de préparation doit être complété") LocalTime preparationTime,
			@NotNull(message = "Le temps de cuisson ne peut pas être vide") @NotBlank(message = "Le temps de cuisson doit être complété") LocalTime cookingTime,
			@NotNull(message = "La description ne peut pas être vide") @NotBlank(message = "La description doit être complété") @Length(max = 10000, message = "La description doit être de maximum 10 000 caractères") String description,
			@NotNull(message = "Les ingrédients ne peuvent pas être vide") @NotBlank(message = "Les ingrédients doivent être complété") List<Ingredient> ingredients,
			List<Device> devices,
			@NotNull(message = "La catégorie ne peut pas être vide") Category category,
			Type type, @NotNull(message = "Le membre doit être présent") Member member) {
		this();
		this.name = name;
		this.preparationTime = preparationTime;
		this.cookingTime = cookingTime;
		this.description = description;
		this.ingredients = ingredients;
		this.devices = devices;
		this.category = category;
		this.type = type;
		this.member = member;
	}
	
	


}
