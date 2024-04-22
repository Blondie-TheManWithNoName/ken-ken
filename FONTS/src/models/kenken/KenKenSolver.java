package models.kenken;

import exceptions.EraseFixedValueException;
import exceptions.RewriteFixedPositionException;
import exceptions.ValueOutOfBoundsException;

public class KenKenSolver {
	private final KenKen kenKen;

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
	boolean solve(int row, int col) {
		if (row == kenKen.getSize()) {
			row = 0;
			if (++col == kenKen.getSize())
				return true;
		}

		if (kenKen.isFixed(row, col))
			return solve(row + 1, col);

		for (int value = 1; value <= kenKen.getSize(); value++) {
			if (isValidMove(row, col, value)) {
				try {
					kenKen.setPosition(row, col, value);
				} catch (ValueOutOfBoundsException | RewriteFixedPositionException ignored) {}
				if (solve(row + 1, col))
					return true;
				try {
					kenKen.erasePosition(row, col);
				} catch (EraseFixedValueException ignored) {}
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
			if (kenKen.getValue(row, i) == value || kenKen.getValue(i, col) == value)
				return false;
		}

		return kenKen.getGroup(row, col).isValidMove(value, kenKen.getSize());
	}
}
