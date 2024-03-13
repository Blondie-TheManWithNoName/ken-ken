package exceptions;

public class CellHasNoGroupException extends Exception {
	public CellHasNoGroupException() {
		super("All cells must belong to a group.");
	}

	public CellHasNoGroupException(int row, int col) {
		super(String.format("All cells must belong to a group. Cell (%d, %d) does not belong to any group.", row, col));
	}
}
