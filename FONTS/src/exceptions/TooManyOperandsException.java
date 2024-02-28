package exceptions;

public class TooManyOperandsException extends Exception {
	public TooManyOperandsException(String symbol, int maxOperands) {
		super(String.format("Trying to add another cell in a group with the %s operation when this operation requires %d operands only.", symbol, maxOperands));
	}
}
