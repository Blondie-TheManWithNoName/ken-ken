package exceptions;

public class InvalidUsernameException extends Exception {
	public InvalidUsernameException() {
		super("The username cannot contain spaces");
	}
}
