package exceptions;

/**
 * This exception is thrown when the user does not select any operation for the KenKen topology.
 */
public class ChooseOperationException extends Exception {
	/**
	 * Constructs a new OperandsDoNotMatchException.
	 */
	public ChooseOperationException() {
		super("You must select at least one operation to generate a KenKen.");
	}
}
