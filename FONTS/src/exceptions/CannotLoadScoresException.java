package exceptions;

public class CannotLoadScoresException extends Exception {
	public CannotLoadScoresException() {
		super("Cannot load scores from file");
	}
}
