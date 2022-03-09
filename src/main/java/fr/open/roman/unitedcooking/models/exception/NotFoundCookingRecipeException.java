package fr.open.roman.unitedcooking.models.exception;

public class NotFoundCookingRecipeException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String message;

	public NotFoundCookingRecipeException(String message) {
		super();
		this.message = message;
	}
	
}
