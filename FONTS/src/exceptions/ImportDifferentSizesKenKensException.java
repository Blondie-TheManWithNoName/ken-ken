package exceptions;

public class ImportDifferentSizesKenKensException extends Exception {
	public ImportDifferentSizesKenKensException(int expected, int actual) {
		super(String.format("The imported KenKens have different sizes, expected %d but got %d", expected, actual));
	}
}
