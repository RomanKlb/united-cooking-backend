package fr.open.roman.unitedcooking.models.exception.notfound;

public class NotFoundMemberException extends NotFoundException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String message;
	
	public NotFoundMemberException(String message) {
		super(message);
	}

}
