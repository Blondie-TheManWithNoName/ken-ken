package exceptions;

/**
 * Thrown when a cell does not belong to any group.
 */
public class CellHasNoGroupException extends Exception {
	/**
	 * Constructs a CellHasNoGroupException with a default message.
	 */
	public CellHasNoGroupException() {
		super("All cells must belong to a group.");
	}

	/**
	 * Constructs a CellHasNoGroupException with a custom message.
	 *
	 * @param row The row of the cell that does not belong to any group.
	 * @param col The column of the cell that does not belong to any group.
	 */
	public CellHasNoGroupException(int row, int col) {
		super(String.format("All cells must belong to a group. Cell (%d, %d) does not belong to any group.", row, col));
	}
}
