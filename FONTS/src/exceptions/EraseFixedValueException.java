package exceptions;

public class EraseFixedValueException extends Exception {
	public EraseFixedValueException() {
		super("A fixed value cannot be erased.");
	}
}
