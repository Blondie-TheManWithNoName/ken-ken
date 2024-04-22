package exceptions;

/**
 * Thrown when the scores cannot be loaded from the file.
 */
public class CannotLoadScoresException extends Exception {
	/**
	 * Constructs a new CannotLoadScoresException with the default message.
	 */
	public CannotLoadScoresException() {
		super("Cannot load scores from file");
	}
}
