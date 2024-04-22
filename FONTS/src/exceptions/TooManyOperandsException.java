package exceptions;

/**
 * Exception thrown when trying to add another cell in a group with the operation that requires a fixed number of operands.
 */
public class TooManyOperandsException extends Exception {
	/**
	 * Constructor.
	 * @param symbol The symbol of the operation.
	 * @param maxOperands The maximum number of operands.
	 */
	public TooManyOperandsException(String symbol, int maxOperands) {
		super(String.format("Trying to add another cell in a group with the %s operation when this operation requires %d operands only.", symbol, maxOperands));
	}
}
