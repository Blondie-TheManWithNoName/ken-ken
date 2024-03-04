package exceptions;

public class RewriteFixedPositionException extends Exception {
	public RewriteFixedPositionException(int row, int col) {
		super(String.format("Cannot rewrite fixed position (%d, %d)", row, col));
	}
}
