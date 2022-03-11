package fr.open.roman.unitedcooking.models.exception.notfound;

public class NotFoundCookingRecipeException extends NotFoundException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String message;

	public NotFoundCookingRecipeException(String message) {
		super(message);
	}
	
}
