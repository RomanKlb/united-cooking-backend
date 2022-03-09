package fr.open.roman.unitedcooking.models.exception;

public class NotFoundTypeException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String message;

	public NotFoundTypeException(String message) {
		super();
		this.message = message;
	}
	
}
