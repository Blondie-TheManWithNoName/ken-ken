package exceptions;

/**
 * This exception is thrown when the number of operands do not match the expected number of operands for a symbol.
 */
public class OperandsDoNotMatchException extends Exception {
	/**
	 * Constructs a new OperandsDoNotMatchException with the specified symbol, expected number of operands, and actual number of operands.
	 * @param symbol The symbol that the operands do not match for.
	 * @param expected The expected number of operands.
	 * @param actual The actual number of operands.
	 */
	public OperandsDoNotMatchException(String symbol, int expected, int actual) {
		super(String.format("Operands length do not match for symbol %s. Expected %d operands, but got %d.", symbol, expected, actual));
	}
}
