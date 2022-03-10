package fr.open.roman.unitedcooking.models.exception;

public class AlreadyTypeCreatedException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String message;

	public AlreadyTypeCreatedException(String message) {
		super();
		this.message = message;
	}
	
	

}
