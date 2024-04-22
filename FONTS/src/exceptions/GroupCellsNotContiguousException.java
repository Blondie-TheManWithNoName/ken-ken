package exceptions;

/**
 * Thrown when a group of cells is not contiguous.
 */
public class GroupCellsNotContiguousException extends Exception {
	/**
	 * Constructs a GroupCellsNotContiguousException with a default message.
	 */
	public GroupCellsNotContiguousException() {
		super("Group cells must be contiguous.");
	}
}
