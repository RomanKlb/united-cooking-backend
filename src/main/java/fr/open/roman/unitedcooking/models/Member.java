package fr.open.roman.unitedcooking.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
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
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Member extends User {

	@NotNull(message = "Le nom ne peut pas être vide")
	@NotBlank(message = "Le nom doit être complété")
	private String name;

	@NotNull(message = "Le prénom ne peut pas être vide")
	@NotBlank(message = "Le prénom doit être complété")
	private String surname;

	@JsonIgnore
	@ToString.Exclude
	@OneToMany(targetEntity=Review.class, mappedBy = "member" , fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	private List<Review> reviews;

	@JsonIgnore
	@ToString.Exclude
	@OneToMany(targetEntity=CookingRecipe.class, fetch = FetchType.LAZY)
	@JoinTable(name = "favoritesOfCookingRecipes")
	private List<CookingRecipe> favoritesOfCookingRecipes;


	public Member(
			@NotNull(message = "Le nom ne peut pas être vide") @NotBlank(message = "Le nom doit être complété") String name,
			@NotNull(message = "Le prénom ne peut pas être vide") @NotBlank(message = "Le prénom doit être complété") String surname) {
		super();
		this.name = name;
		this.surname = surname;
	}



}
