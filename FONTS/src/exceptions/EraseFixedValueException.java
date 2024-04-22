package exceptions;

/**
 * Exception thrown when trying to erase a fixed value.
 */
public class EraseFixedValueException extends Exception {
	/**
	 * Constructs a new EraseFixedValueException with the default message.
	 */
	public EraseFixedValueException() {
		super("A fixed value cannot be erased.");
	}
}
