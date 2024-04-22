package models.kenken;

import exceptions.EraseFixedValueException;
import exceptions.RewriteFixedPositionException;

/**
 * Class to represent a cell in the KenKen puzzle
 */
public class Cell {
	// Row index
	private final int row;
	// Column index
	private final int col;
	// Value that the cell holds
	private int value;
	// Fixed value cell
	private boolean fixed;
	// Group which the cell is part of
	private Group group;

	/**
	 * Constructor to create a cell
	 * @param row index
	 * @param col index
	 */
	public Cell(int row, int col) {
		this.row = row;
		this.col = col;
	}

	/**
	 * Getter for the cells' row
	 * @return index of the row
	 */
	public int getRow() {
		return row;
	}

	/**
	 * Getter for the cells' column
	 * @return index of the column
	 */
	public int getCol() {
		return col;
	}

	/**
	 * Getter for the cells' column
	 * @return the value holding
	 */
	public int getValue() {
		return value;
	}

	/**
	 * Getter for group the cell belongs to
	 * @return the group
	 */
	public Group getGroup() {
		return group;
	}

	/**
	 * Check if the cell is fixed or not
	 * @return true if is fixed, false otherwise
	 */
	public boolean isFixed() {
		return fixed;
	}

	/**
	 * Setter to set a value on a cell
	 * @param value to be set on the cell
	 * @throws RewriteFixedPositionException when the cell is fixed, cannot change the value
	 */
	public void setValue(int value) throws RewriteFixedPositionException {
		if (fixed)
			throw new RewriteFixedPositionException(row, col);
		this.value = value;
	}

	/**
	 * Setter to set a fixed value on a cell
	 * @param value to be set as fixed on the cell
	 */
	public void setFixedValue(int value) {
		this.value = value;
		this.fixed = true;
	}

	/**
	 * Setter to set the group which the cell will belong to
	 * @param group which the cell will belong to
	 */
	public void setGroup(Group group) {
		this.group = group;
	}

	/**
	 * Set cell value to 0, empty it
	 * @throws EraseFixedValueException when the cell is fixed, cannot change the value
	 */
	public void erase() throws EraseFixedValueException {
		if (fixed)
			throw new EraseFixedValueException();
		value = 0;
	}

	/**
	 * Set the cell to value 0, empty it, even if it fixed. Resets fixed to false.
	 */
	public void clear() {
		value = 0;
		fixed = false;
	}

	/**
	 * Checks if cells has a group or not
	 * @return true if the cells belongs to a group, false otherwise
	 */
	public boolean hasGroup() {
		return group != null;
	}

	/**
	 * Checks if cells is empty or not
	 * @return true if the cell is empty, false otherwise
	 */
	public boolean isEmpty() {
		return value == 0;
	}

	/**
	 * Checks if the current cell and the param cell are next to each other
	 * @param cell, cell to be checked with current cell
	 * @return true if they are next to each other, false otherwise
	 */
	public boolean isAdjacent(Cell cell) {
		return Math.abs(row - cell.getRow()) + Math.abs(col - cell.getCol()) == 1;
	}

	/**
	 * Clears group from the cell
	 */
	public void clearGroup() {
		group = null;
	}
}
