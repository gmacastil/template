package co.com.telefonica.integration.utility.core.exception;

public class ValidationException extends Exception {

	private static final long serialVersionUID = 1L;

	public ValidationException(String message, Throwable cause) {
		super(message, cause);
	}

	public ValidationException(String errorMessage) {
		super(errorMessage);
	}
}