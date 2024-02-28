package exceptions;

public class OperandsDoNotMatchException extends Exception {
	public OperandsDoNotMatchException(String symbol, int expected, int actual) {
		super(String.format("Operands length do not match for symbol %s. Expected %d operands, but got %d.", symbol, expected, actual));
	}
}
