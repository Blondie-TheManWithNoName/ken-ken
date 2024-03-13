package models.kenken;

import exceptions.EraseFixedValueException;
import exceptions.RewriteFixedPositionException;

public class Cell {
	private final int row;
	private final int col;
	private int value;
	private boolean fixed;
	private Group group;

	public Cell(int row, int col) {
		this.row = row;
		this.col = col;
	}

	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}

	public int getValue() {
		return value;
	}

	public Group getGroup() {
		return group;
	}

	public boolean isFixed() {
		return fixed;
	}

	public void setValue(int value) throws RewriteFixedPositionException {
		if (fixed)
			throw new RewriteFixedPositionException(row, col);
		this.value = value;
	}

	public void setFixedValue(int value) {
		this.value = value;
		this.fixed = true;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public void erase() throws EraseFixedValueException {
		if (fixed)
			throw new EraseFixedValueException();
		value = 0;
	}

	public void clear() {
		value = 0;
		fixed = false;
	}

	public boolean hasGroup() {
		return group != null;
	}

	public boolean isAdjacent(Cell cell) {
		return Math.abs(row - cell.getRow()) + Math.abs(col - cell.getCol()) == 1;
	}

	public void clearGroup() {
		group = null;
	}
}
