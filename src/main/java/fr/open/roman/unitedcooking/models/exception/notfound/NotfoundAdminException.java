package fr.open.roman.unitedcooking.models.exception.notfound;

public class NotfoundAdminException extends NotFoundException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String message;

	public NotfoundAdminException(String message) {
		super(message);
	}
}
