package fr.open.roman.unitedcooking.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import fr.open.roman.unitedcooking.models.Review;
import fr.open.roman.unitedcooking.models.dto.ReviewReceipt;
import fr.open.roman.unitedcooking.models.exception.notfound.NotFoundCookingRecipeException;
import fr.open.roman.unitedcooking.models.exception.notfound.NotFoundMemberException;

public interface ReviewService {

	Review createReview(@Valid ReviewReceipt reviewReceipt) throws NotFoundMemberException, NotFoundCookingRecipeException;
	
	Optional<Review> recoveryReviewById(Long id);
	
	List<Review> recoveryAllReviewsByMember(String pseudo) throws NotFoundMemberException;
	List<Review> recoveryAllReviews();
	
	boolean deleteReview(Long id);
}
