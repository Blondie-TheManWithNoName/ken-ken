package exceptions;

public class CellAlreadyInGroupException extends Exception {
	public CellAlreadyInGroupException(int row, int col) {
		super(String.format("The cell (%d, %d) is already in group.", row, col));
	}
}
