package exceptions;

/**
 * Exception thrown when trying to rewrite a fixed position in the board.
 */
public class RewriteFixedPositionException extends Exception {
	/**
	 * Constructor.
	 * @param row The row of the fixed position.
	 * @param col The column of the fixed position.
	 */
	public RewriteFixedPositionException(int row, int col) {
		super(String.format("Cannot rewrite fixed position (%d, %d)", row, col));
	}
}
