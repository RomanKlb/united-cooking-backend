package fr.open.roman.unitedcooking.service.impl;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import org.springframework.stereotype.Service;

import fr.open.roman.unitedcooking.models.CookingRecipe;
import fr.open.roman.unitedcooking.models.Member;
import fr.open.roman.unitedcooking.models.Review;
import fr.open.roman.unitedcooking.models.dto.ReviewReceipt;
import fr.open.roman.unitedcooking.models.exception.notfound.NotFoundCookingRecipeException;
import fr.open.roman.unitedcooking.models.exception.notfound.NotFoundMemberException;
import fr.open.roman.unitedcooking.repositories.ReviewRepository;
import fr.open.roman.unitedcooking.service.CookingRecipeService;
import fr.open.roman.unitedcooking.service.MemberService;
import fr.open.roman.unitedcooking.service.ReviewService;

@Service
public class ReviewServiceImpl implements ReviewService	{

	private final ReviewRepository reviewRepository;
	private final MemberService memberService;
	private final CookingRecipeService cookingRecipeService;

	public ReviewServiceImpl(ReviewRepository reviewRepository, MemberService memberService, CookingRecipeService cookingRecipeService) {
		super();
		this.reviewRepository = reviewRepository;
		this.memberService = memberService;
		this.cookingRecipeService = cookingRecipeService;
	}


	@Override
	public Review createReview(@Valid ReviewReceipt dto) throws NotFoundMemberException, NotFoundCookingRecipeException {
		return reviewRepository.save(new Review(
				dto.getRemark(),
				dto.getRating(),
				recuperationMember(dto.getMemberPseudo()), 
				recuperationCookingRecipe(dto.getIdCookingRecipe())
				));
	}

	private CookingRecipe recuperationCookingRecipe(Long idCookingRecipe) throws NotFoundCookingRecipeException {
		Optional<CookingRecipe> cookingRecipe = cookingRecipeService.recoveryCookingRecipeById(idCookingRecipe);
		if(cookingRecipe.isPresent()) {
			return cookingRecipe.get();
		} else {
			throw new NotFoundCookingRecipeException("La recette n'a pas été trouvé pour permettre la sauvegarde du commentaire!");
		}
	}
	private Member recuperationMember(String memberPseudo) throws NotFoundMemberException {
		Optional<Member> member = memberService.recoveryMemberByPseudo(memberPseudo);
		if(member.isPresent()) {
			return member.get();
		} else {
			throw new NotFoundMemberException("Le membre n'a pas été trouvé pour permettre la sauvegarde du commentaire!");
		}
	}


	@Override
	public Optional<Review> recoveryReviewById(Long id) {
		return reviewRepository.findById(id);
	}

	@Override
	public List<Review> recoveryAllReviewsByMember(String pseudo) throws NotFoundMemberException {
		Optional<Member> member = memberService.recoveryMemberByPseudo(pseudo);
		if(member.isPresent()) {
			return reviewRepository.findAllByMember(member.get());
		} else {
			throw new NotFoundMemberException("Le membre n'a pas été trouvé donc nous ne pouvons pas récupérer ses commentaires");
		}
		
	}

	@Override
	public List<Review> recoveryAllReviews() {
		return reviewRepository.findAll();
	}

	@Override
	public boolean deleteReview(Long id) {
		if(reviewRepository.existsById(id)) {
			reviewRepository.deleteById(id);
			return true;
		} else {
			return false;
		}
	}

}
