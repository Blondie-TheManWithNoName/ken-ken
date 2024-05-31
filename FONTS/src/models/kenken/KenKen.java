package models.kenken;

import exceptions.*;
import models.operations.Operation;

import java.util.ArrayList;
import java.util.List;

/**
 * KenKen class, to create kenken
 */
public class KenKen {
	/* Size of KenKen */
	private final int size;
	/* Matrix containing all the KenKen cells*/
	private final Cell[][] board;

	public final int[][] boardSolved;

	/* List of groups */
	private final List<Group> groups = new ArrayList<>();

	/**
	 * Constructor method, creates matrix cell and initializes every as an empty one
	 * @param size, size of KenKen
	 */
	public KenKen(int size) {
		this.size = size;
		this.board = new Cell[size][size];
		this.boardSolved = new int[size][size];
		for (int i = 0; i < size; i++)
			for (int j = 0; j < size; j++)
				this.board[i][j] = new Cell(i, j);
	}

	public int[][] getBoardSolved(){return boardSolved;}

	/**
	 * Consultor method for the KenKen size
	 * @return size
	 */
	public int getSize() {
		return size;
	}

	/**
	 * Getter method for a cell given its coordinates
	 * @param row, defines the row of the KenKen to get the cell from
	 * @param col, defines the column of the KenKen to get the cell from
	 * @return the cell
	 */
	public Cell getCell(int row, int col) {
		return board[row][col];
	}

	/**
	 * Getter method for the groups of the KenKen
	 * @return list with the groups
	 */
	public List<Group> getGroups() {
		return groups;
	}

	/**
	 * Getter method for a group given coordinates of a cell
	 * @param row, defines the row of the KenKen to get the cell from
	 * @param col, defines the column of the KenKen to get the cell from
	 * @return group
	 */
	public Group getGroup(int row, int col) {
		return board[row][col].getGroup();
	}

	/**
	 * Getter method for the value of a cell given its coordinates
	 * @param row, defines the row of the KenKen to get the cell from
	 * @param col, defines the column of the KenKen to get the cell from
	 * @return the value
	 */
	public int getValue(int row, int col) {
		return board[row][col].getValue();
	}
	public int getValueSolved(int row, int col) {return boardSolved[row][col];}
	/**
	 * Method to add a new group to the KenKen
	 * @param operation, operation to be perfomed on the group
	 */
	public void addGroup(Operation operation) {
		groups.add(new Group(operation));
	}

	/**
	 * Adds the cell of the given coordinates to the last group created
	 * @param row, defines the row of the KenKen to get the cell from
	 * @param col, defines the column of the KenKen to get the cell from
	 * @throws TooManyOperandsException when the operation accepts a limited number of operands (OperationDivision, OperationEquality, OperationPower, OperationSubtraction)
	 * @throws CellAlreadyInGroupException when the cell is already part of a group
	 */
	public void addCellToLastGroup(int row, int col) throws TooManyOperandsException, CellAlreadyInGroupException {
		groups.get(groups.size() - 1).addCell(board[row][col]);
	}

	/**
	 * Method to set a fixed value to a cell
	 * @param row, defines the row of the KenKen to get the cell from
	 * @param col, defines the column of the KenKen to get the cell from
	 * @param value, defines the number of the fixed value of the cell
	 * @throws ValueOutOfBoundsException when the value is smaller than 1 or bigger than the KenKen size
	 */
	public void setFixedPosition(int row, int col, int value) throws ValueOutOfBoundsException {
		if (value < 1 || value > size)
			throw new ValueOutOfBoundsException(value);
		board[row][col].setFixedValue(value);
	}

	/**
	 *
	 * @param row, defines the row of the KenKen to get the cell from
	 * @param col, defines the column of the KenKen to get the cell from
	 * @param value, defines the number of the value to put on the cell
	 * @throws ValueOutOfBoundsException when the value is smaller than 1 or bigger than the KenKen size
	 * @throws RewriteFixedPositionException when trying to modify a position with a fixed value already set
	 */
	public void setPosition(int row, int col, int value) throws ValueOutOfBoundsException, RewriteFixedPositionException {
		if (value < 1 || value > size)
			throw new ValueOutOfBoundsException(value);
		board[row][col].setValue(value);
	}

	public void setPositoinSolved(int row, int col, int value) {boardSolved[row][col] = value;}

	/**
	 * Method to set the value of a cell to 0, if its not fixed
	 * @param row, defines the row of the KenKen to get the cell from
	 * @param col, defines the column of the KenKen to get the cell from
	 * @throws EraseFixedValueException when trying to remove the value of a position with a fixed value already set
	 */
	public void erasePosition(int row, int col) throws EraseFixedValueException {
		board[row][col].erase();
	}

	/**
	 * Method to set the value of a cell to 0, fixed or not
	 * @param row, defines the row of the KenKen to get the cell from
	 * @param col, defines the column of the KenKen to get the cell from
	 */
	public void clearPosition(int row, int col) {
		board[row][col].clear();
	}

	/**
	 * Method to erase all cells from the KenKen
	 */
	public void erase() {
		for (int i = 0; i < size; i++)
			for (int j = 0; j < size; j++) {
				try {
					board[i][j].erase();
				} catch (EraseFixedValueException ignored) {}
			}
	}

	/**
	 * Method to check if all cells are assigned to a group and if all the groups fulfill with the KenKen rules
	 * @throws CellHasNoGroupException when a cell is not assigned to a group
	 * @throws GroupCellsNotContiguousException when a group contains one or more cells not contiguous
	 */
	public void checkCellsGroups() throws CellHasNoGroupException, GroupCellsNotContiguousException {
		for (int i = 0; i < size; i++)
			for (int j = 0; j < size; j++)
				if (!board[i][j].hasGroup())
					throw new CellHasNoGroupException(i, j);
		for (Group group : groups)
			if (!group.cellsAreAdjacent())
				throw new GroupCellsNotContiguousException();
	}

	/**
	 * Method to check if two cells are in the same group
	 * @param row1, defines the row of the KenKen to get the first cell from
	 * @param col1, defines the column of the KenKen to get the first cell from
	 * @param row2, defines the row of the KenKen to get the second cell from
	 * @param col2, defines the column of the KenKen to get the second cell from
	 * @return true if they are in the same group and false if not
	 */
	public boolean sameGroup(int row1, int col1, int row2, int col2) {
		return board[row1][col1].getGroup().hasCell(board[row2][col2]);
	}

	/**
	 * Checks if a cell has a fixed value
	 * @param row, defines the row of the KenKen to get the cell from
	 * @param col, defines the column of the KenKen to get the cell from
	 * @return true if cell is fixed, false if not fixed
	 */
	public boolean isFixed(int row, int col) {
		return board[row][col].isFixed();
	}

	/**
	 * Checks if all the cells have a value
	 * @return true if all the cells have a value
	 */
	public boolean isFull() {
		for (int i = 0; i < size; i++)
			for (int j = 0; j < size; j++)
				if (board[i][j].isEmpty())
					return false;
		return true;
	}

	/**
	 *
	 * Checks if the KenKen
	 * @return true if the KenKen fulfills the rules, false otherwise
	 * @throws OperandsDoNotMatchException when the operands dont have the expected result
	 * @throws NonIntegerResultException when the result or the operation is negative
	 */
	public boolean check() throws OperandsDoNotMatchException, NonIntegerResultException {
		if (numbersRepeatInRows() || numbersRepeatInColumns())
			return false;
		for (Group group : groups)
			if (!group.check())
				return false;
		return true;
	}

	/**
	 * Removes all the groups from the cells and the KenKen
	 */
	public void clearGroups() {
		for (Cell[] row : board)
			for (Cell cell : row)
				cell.clearGroup();
		groups.clear();
	}

	/**
	 * Check if all the rows contain different numbers
	 * @return true if numbers in a row are repeated, false otherwise
	 */
	private boolean numbersRepeatInRows() {
		for (int i = 0; i < size; i++) {
			boolean[] numbers = new boolean[size];
			for (int j = 0; j < size; j++) {
				int value = board[i][j].getValue();
				if (value == 0)
					return false;
				if (numbers[value - 1])
					return true;
				numbers[value - 1] = true;
			}
		}
		return false;
	}

	/**
	 * Check if all the column contain different numbers
	 * @return true if numbers in a column are repeated, false otherwise
	 */
	private boolean numbersRepeatInColumns() {
		for (int i = 0; i < size; i++) {
			boolean[] numbers = new boolean[size];
			for (int j = 0; j < size; j++) {
				int value = board[j][i].getValue();
				if (value == 0)
					return false;
				if (numbers[value - 1])
					return true;
				numbers[value - 1] = true;
			}
		}
		return false;
	}
}
