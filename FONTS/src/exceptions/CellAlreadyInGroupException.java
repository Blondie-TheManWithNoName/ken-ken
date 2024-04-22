package exceptions;

/**
 * This exception is thrown when a cell is already in a group.
 */
public class CellAlreadyInGroupException extends Exception {
	/**
	 * Constructs a CellAlreadyInGroupException with the specified row and column.
	 *
	 * @param row the row of the cell
	 * @param col the column of the cell
	 */
	public CellAlreadyInGroupException(int row, int col) {
		super(String.format("The cell (%d, %d) is already in group.", row, col));
	}
}
