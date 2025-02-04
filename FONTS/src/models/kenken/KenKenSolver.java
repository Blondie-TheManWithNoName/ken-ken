package models.kenken;

import exceptions.EraseFixedValueException;
import exceptions.RewriteFixedPositionException;
import exceptions.ValueOutOfBoundsException;

/**
 * Class to solve a kenken
 */
public class KenKenSolver {
	private final KenKen kenKen;

	/**
	 * Constructor method for the KenKenSolver
	 * @param kenKen the kenken we want to solve
	 */
	public KenKenSolver(KenKen kenKen) {
		this.kenKen = kenKen;
	}

	/**
	 * Consultor method for the kenken
	 * @return the kenken
	 */
	public KenKen getKenKen() {
		return kenKen;
	}

	/**
	 * public method to solve a kenken
	 * @return true if it's possible to solve the kenken, false otherwise
	 */
	public boolean solve() {
		kenKen.erase();
		return solve(0, 0);
	}

	/**
	 * solve the kenken
	 * @param row the row of the cell that we want to write a value
	 * @param col the col of the cell that we want to write a value
	 * @return true if it's possible to solve the kenken, false otherwise
	 */
	private boolean solve(int row, int col) {
		if (row == kenKen.getSize()) {
			row = 0;
			if (++col == kenKen.getSize())
				return true;
		}

		if (kenKen.isFixed(row, col))
			return solve(row + 1, col);

		for (int value = 1; value <= kenKen.getSize(); value++) {
			if (isValidMove(row, col, value)) {
                kenKen.setPositoinSolved(row, col, value);
                if (solve(row + 1, col))
					return true;
				kenKen.setPositoinSolved(row, col, 0);
            }
		}

		return false;
	}

	/**
	 * check if the value can be written in the cell with position row and column
	 * @param row the row of the cell
	 * @param col the column of the cell
	 * @param value the value we want to put in the cell
	 * @return true if the value is valid in the cell, false otherwise
	 */
	private boolean isValidMove(int row, int col, int value) {
		for (int i = 0; i < kenKen.getSize(); i++) {
			if (kenKen.getValueSolved(row, i) == value || kenKen.getValueSolved(i, col) == value)
				return false;
		}
		return kenKen.getGroup(row, col).isValidMove(value, kenKen.getSize(), kenKen.getBoardSolved());
	}
}
