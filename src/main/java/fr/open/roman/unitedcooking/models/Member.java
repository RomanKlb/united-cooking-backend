package fr.open.roman.unitedcooking.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

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
			@NotNull(message = "Le pseudo ne peut pas être vide") @NotBlank(message = "Le pseudo doit être complété") @Pattern(regexp = "^([A-Za-z0-9]+)*", message = "votre pseudo doit être composé uniquement de majuscule, minuscule et/ou chiffre") String pseudo,
			@NotNull(message = "Le mot de passe ne peut pas être vide") @NotBlank(message = "Le mot de passe doit être complété") @Length(min = 6, message = "Mettre un mot de passe de minimum 6 caractères") String password,
			@NotNull(message = "L'email ne peut pas être vide") @NotBlank(message = "Merci de préciser une adresse email") @Email(message = "Il faut un email valide") String email,
			@NotNull(message = "Le nom ne peut pas être vide") @NotBlank(message = "Le nom doit être complété") String name,
			@NotNull(message = "Le prénom ne peut pas être vide") @NotBlank(message = "Le prénom doit être complété") String surname) {
		super(pseudo, password, email);
		this.name = name;
		this.surname = surname;	}
	

}
