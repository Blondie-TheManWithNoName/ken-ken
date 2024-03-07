package exceptions;

public class CannotCreateOperationException extends Exception {
	public CannotCreateOperationException() {
		super("Cannot create the desired operation.");
	}
}
