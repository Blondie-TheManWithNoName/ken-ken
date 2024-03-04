package models;

import exceptions.EraseFixedValueException;
import exceptions.RewriteFixedPositionException;
import exceptions.ValueOutOfBoundsException;

public class KenKenSolver {
	private final KenKen kenKen;

	public KenKenSolver(KenKen kenKen) {
		this.kenKen = kenKen;
	}

	public KenKen getKenKen() {
		return kenKen;
	}

	public boolean solve() {
		return solve(0, 0);
	}

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

	private boolean isValidMove(int row, int col, int value) {
		for (int i = 0; i < kenKen.getSize(); i++) {
			if (kenKen.getValue(row, i) == value || kenKen.getValue(i, col) == value)
				return false;
		}

		return kenKen.getGroup(row, col).isValidMove(value, kenKen.getSize());
	}
}
