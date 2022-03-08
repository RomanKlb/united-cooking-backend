package fr.open.roman.unitedcooking.models;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Review {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Length(max = 500, message = "Votre commentaire doit être composé d'un maximum de 500 caractères")
	private String remark;
	
	@NotNull(message = "La note ne peut pas être vide")
	@NotBlank(message = "La note doit être complété")
	@Range(min = 0, max = 20, message = "La notation est compris entre 0 et 20")
	private int rating;
	
	private LocalDateTime createdReviews;
	
	private LocalDateTime moderateReviews;
	
	@OneToOne
	private Admin admin;
	
	@NotNull(message = "Le membre ne peut pas être vide")
	@NotBlank(message = "Le membre doit être complété")
	@ManyToOne
	private Member member;
	
	@NotNull(message = "La recette ne peut pas être vide")
	@NotBlank(message = "La recette doit être complété")
	@ManyToOne
	private CookingRecipe cookingRecipe;

	
	public Review() {
		createdReviews = LocalDateTime.now();
	}


	public Review(@Length(max = 500, message = "Votre commentaire doit être composé d'un maximum de 500 caractères") String remark,
			@NotNull(message = "La note ne peut pas être vide") @NotBlank(message = "La note doit être complété") 
			@Range(min = 0, max = 20, message = "La notation est compris entre 0 et 20") int rating,
			@NotNull(message = "Le membre ne peut pas être vide") @NotBlank(message = "Le membre doit être complété") Member member,
			@NotNull(message = "La recette ne peut pas être vide") @NotBlank(message = "La recette doit être complété") CookingRecipe cookingRecipe) {
		super();
		this.remark = remark;
		this.rating = rating;
		this.member = member;
		this.cookingRecipe = cookingRecipe;
	}

	
	
	
	
}
