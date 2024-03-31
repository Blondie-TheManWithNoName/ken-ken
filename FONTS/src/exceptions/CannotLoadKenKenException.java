package exceptions;

public class CannotLoadKenKenException extends Exception {
	public CannotLoadKenKenException() {
		super("The file cannot be found or is not a valid KenKen.");
	}
}
