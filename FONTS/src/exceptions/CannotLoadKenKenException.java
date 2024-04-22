package exceptions;

/**
 * Exception thrown when a KenKen cannot be loaded from a file.
 */
public class CannotLoadKenKenException extends Exception {
	/**
	 * Constructs a CannotLoadKenKenException with a default message.
	 */
	public CannotLoadKenKenException() {
		super("The file cannot be found or does not contain a valid KenKen.");
	}
}
