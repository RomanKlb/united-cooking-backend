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
public class SignupMemberRequest {

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
	
	@NotNull(message = "Le nom ne peut pas être vide")
	@NotBlank(message = "Le nom doit être complété")
	private String name;

	@NotNull(message = "Le prénom ne peut pas être vide")
	@NotBlank(message = "Le prénom doit être complété")
	private String surname;
	
}

