package fr.open.roman.unitedcooking.models.exception;

public class NotFoundMemberException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String message;
	
	public NotFoundMemberException(String message) {
		super();
		this.message = message;
	}

}
