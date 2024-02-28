package exceptions;

public class NonIntegerResultException extends Exception {
	public NonIntegerResultException(String symbol) {
		super(String.format("The result of the operation %s is not an integer", symbol));
	}
}
