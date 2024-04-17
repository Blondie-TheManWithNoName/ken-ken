package exceptions;

public class CannotLoadKenKenException extends Exception {
	public CannotLoadKenKenException() {
		super("The file cannot be found or does not contain a valid KenKen.");
	}
}
