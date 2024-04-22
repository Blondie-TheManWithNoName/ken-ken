package exceptions;

/**
 * This exception is thrown when the desired operation cannot be created.
 */
public class CannotCreateOperationException extends Exception {
	/**
	 * Constructs a new CannotCreateOperationException with the default message.
	 */
	public CannotCreateOperationException() {
		super("Cannot create the desired operation.");
	}
}
