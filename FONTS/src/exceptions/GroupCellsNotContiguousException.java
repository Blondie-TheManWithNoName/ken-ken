package exceptions;

public class GroupCellsNotContiguousException extends Exception {
	public GroupCellsNotContiguousException(int row, int col) {
		super(String.format("Group cells must be contiguous. Trying to add cell (%d, %d) to a group that is not contiguous.", row, col));
	}
}
