package exceptions;

public class GroupCellsNotContiguousException extends Exception {
	public GroupCellsNotContiguousException() {
		super("Group cells must be contiguous.");
	}
}
