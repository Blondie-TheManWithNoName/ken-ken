package exceptions;

/**
 * Thrown when a cell does not belong to any group.
 */
public class ShapeCannotFitKenKenException extends Exception {
	/**
	 * Constructs a CellHasNoGroupException with a default message.
	 */
	public ShapeCannotFitKenKenException() {
		super("This shape cannot fit a KenKen of this size.");
	}

	/**
	 * Constructs a CellHasNoGroupException with a custom message.
	 *
	 * @param
	 * @param
	 */
	public ShapeCannotFitKenKenException(int size) {
		super(String.format("Selected shapes cannot fill up a KenKen of size %d.", size));
	}
}
