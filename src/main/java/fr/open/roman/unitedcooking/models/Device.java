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
public class Device {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message = "Le nom de l'appareil ne peut pas être vide")
	@NotBlank(message = "Le nom de l'appareil doit être complété")
	private String name;
	
	@JsonIgnore
	@ManyToMany(mappedBy = "devices")
	private List<CookingRecipe> cookingRecipes;

	public Device(String name) {
		super();
		this.name = name;
	}
	
}
