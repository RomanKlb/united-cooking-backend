package fr.open.roman.unitedcooking.payload.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupAdminRequest {

	@NotNull(message = "Le pseudo ne peut pas être vide")
	@NotBlank(message = "Le pseudo doit être complété")
	@Pattern(regexp = "^([A-Za-z0-9]+)*", message = "votre pseudo doit être composé uniquement de majuscule, minuscule et/ou chiffre")
	private String pseudo;

	@NotNull(message = "L'email ne peut pas être vide")
	@NotBlank(message="Merci de préciser une adresse email")
	@Email
	private String email;

	@NotNull(message = "Le mot de passe ne peut pas être vide")
	@NotBlank(message = "Le mot de passe doit être complété")
	@Length(min = 6, message = "Mettre un mot de passe de minimum 6 caractères")
	private String password;
	
	@NotNull(message = "Le numéro de téléphone ne peut pas être vide")
	@NotBlank(message = "Le numéro de téléphone doit être complété")
	@Length(min = 10, message = "votre numéro de téléphone est composé de 10 chiffres minimum")
	private String phoneNumber;
}
