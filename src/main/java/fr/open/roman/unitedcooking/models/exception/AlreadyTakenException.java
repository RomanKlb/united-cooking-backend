package fr.open.roman.unitedcooking.models.exception;

public class AlreadyTakenException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	String name;

	public AlreadyTakenException(String name) {
		super();
		this.name = name;
	}
	
	
}
