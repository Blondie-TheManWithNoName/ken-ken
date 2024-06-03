package exceptions;

/**
 * This exception is thrown when the user does not select any shape for the KenKen topology.
 */
public class ChooseTopologyException extends Exception {
	/**
	 * Constructs a new OperandsDoNotMatchException.
	 */
	public ChooseTopologyException() {
		super("You must select at least one shape to generate a KenKen.");
	}
}
