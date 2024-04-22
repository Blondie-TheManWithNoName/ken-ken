package exceptions;

/**
 * Exception thrown when a value is out of bounds for a KenKen.
 */
public class ValueOutOfBoundsException extends Exception {
	/**
	 * Constructs a ValueOutOfBoundsException with the specified value.
	 *
	 * @param value the value that is out of bounds
	 */
	public ValueOutOfBoundsException(int value) {
		super(String.format("Value %d out of bounds for this KenKen.", value));
	}
}
