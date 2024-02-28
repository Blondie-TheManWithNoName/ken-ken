package exceptions;

public class ValueOutOfBoundsException extends Exception {
	public ValueOutOfBoundsException(int value) {
		super(String.format("Value %d out of bounds for this KenKen.", value));
	}
}
