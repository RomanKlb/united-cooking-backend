package fr.open.roman.unitedcooking.models.exception.notfound;

public class NotFoundTypeException extends NotFoundException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String message;

	public NotFoundTypeException(String message) {
		super(message);
	}
	
}
