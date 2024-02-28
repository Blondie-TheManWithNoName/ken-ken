package exceptions;

public class CellHasNoGroupException extends Exception {
	public CellHasNoGroupException(int row, int col) {
		super(String.format("All cells must belong to a group. Cell (%d, %d) does not belong to any group.", row, col));
	}
}
