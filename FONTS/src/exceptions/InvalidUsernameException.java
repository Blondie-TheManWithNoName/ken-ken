package exceptions;

/**
 * This exception is thrown when the username contains spaces.
 */
public class InvalidUsernameException extends Exception {
	/**
	 * Constructor for the exception.
	 */
	public InvalidUsernameException() {
		super("The username cannot contain spaces");
	}
}
