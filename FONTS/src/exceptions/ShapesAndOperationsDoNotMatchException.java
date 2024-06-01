package exceptions;

/**
 * This exception is thrown when the number of operands do not match the expected number of operands for a symbol.
 */
public class ShapesAndOperationsDoNotMatchException extends Exception {
	/**
	 * Constructs a new OperandsDoNotMatchException with the specified symbol, expected number of operands, and actual number of operands.
	 * @param symbol The symbol that the operands do not match for.
	 */
	public ShapesAndOperationsDoNotMatchException(String symbol) {
		super(String.format("The operation with symbol %s, will not be used because it does not match with any chosen shape.", symbol));
	}
}
