package exceptions;

/**
 * This exception is thrown when the result of an operation is not an integer.

 */
public class NonIntegerResultException extends Exception {
	/**
	 * Constructor for the exception.
	 *
	 * @param symbol The symbol of the operation that caused the exception.
	 */
	public NonIntegerResultException(String symbol) {
		super(String.format("The result of the operation %s is not an integer", symbol));
	}
}
