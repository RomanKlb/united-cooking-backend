package fr.open.roman.unitedcooking.models;

import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Admin extends User {

	@NotNull(message = "Le numéro de téléphone ne peut pas être vide")
	@NotBlank(message = "Le numéro de téléphone doit être complété")
	@Length(min = 10, message = "votre numéro de téléphone est composé de 10 chiffres minimum")
	private String phoneNumber;


	public Admin(
			@NotNull(message = "Le pseudo ne peut pas être vide") @NotBlank(message = "Le pseudo doit être complété") @Pattern(regexp = "^([A-Za-z0-9]+)*", message = "votre pseudo doit être composé uniquement de majuscule, minuscule et/ou chiffre") String pseudo,
			@NotNull(message = "Le mot de passe ne peut pas être vide") @NotBlank(message = "Le mot de passe doit être complété") @Length(min = 6, message = "Mettre un mot de passe de minimum 6 caractères") String password,
			@NotNull(message = "L'email ne peut pas être vide") @NotBlank(message = "Merci de préciser une adresse email") @Email(message = "Il faut un email valide") String email,
			@NotNull(message = "Le numéro de téléphone ne peut pas être vide") @NotBlank(message = "Le numéro de téléphone doit être complété") 
			@Length(min = 10, message = "votre numéro de téléphone est composé de 10 chiffres minimum") String phoneNumber) {
		super(pseudo, password, email);
		this.phoneNumber = phoneNumber;
	}




}
