package fr.open.roman.unitedcooking.models.exception.notfound;

public class NotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	String message;

	public NotFoundException(String message) {
		super();
		this.message = message;
	}
	
	
}
