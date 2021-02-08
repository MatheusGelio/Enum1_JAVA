package model.exceptions;

public class HourContractException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public HourContractException(String message) {
		super(message);
	}
}
