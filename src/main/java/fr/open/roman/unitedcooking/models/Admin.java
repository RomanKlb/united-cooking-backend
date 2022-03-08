package fr.open.roman.unitedcooking.models;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
			@NotNull(message = "Le numéro de téléphone ne peut pas être vide") @NotBlank(message = "Le numéro de téléphone doit être complété") 
			@Length(min = 10, message = "votre numéro de téléphone est composé de 10 chiffres minimum") String phoneNumber) {
		super();
		this.phoneNumber = phoneNumber;
	}
	
	
}
