package fr.open.roman.unitedcooking.models.exception;

public class NotFoundCategoryException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String message;

	public NotFoundCategoryException(String message) {
		super();
		this.message = message;
	}

}
