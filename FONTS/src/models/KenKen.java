package models;

import exceptions.*;
import models.operations.Operation;

import java.util.ArrayList;
import java.util.List;

public class KenKen {
	private final int size;
	private final Cell[][] board;
	private final List<Group> groups = new ArrayList<>();

	public KenKen(int size) {
		this.size = size;
		this.board = new Cell[size][size];
		for (int i = 0; i < size; i++)
			for (int j = 0; j < size; j++)
				this.board[i][j] = new Cell(i, j);
	}

	public int getSize() {
		return size;
	}

	public List<Group> getGroups() {
		return groups;
	}

	public int getValue(int row, int col) {
		return board[row][col].getValue();
	}

	public void addGroup(Operation operation) {
		groups.add(new Group(operation));
	}

	public void addCellToLastGrop(int row, int col) throws GroupCellsNotContiguousException, TooManyOperandsException, CellAlreadyInGroupException {
		groups.get(groups.size() - 1).addCell(board[row][col]);
	}

	public void setFixedPosition(int row, int col, int value) throws ValueOutOfBoundsException {
		if (value < 1 || value > size)
			throw new ValueOutOfBoundsException(value);
		board[row][col].setFixedValue(value);
	}

	public void setPosition(int row, int col, int value) throws ValueOutOfBoundsException {
		if (value < 1 || value > size)
			throw new ValueOutOfBoundsException(value);
		board[row][col].setValue(value);
	}

	public void erasePosition(int row, int col) throws EraseFixedValueException {
		board[row][col].erase();
	}

	public boolean hasTopBorder(int row, int col) throws CellHasNoGroupException {
		if (!board[row][col].hasGroup())
			throw new CellHasNoGroupException(row, col);
		return row == 0;
	}

	public boolean hasLeftBorder(int row, int col) throws CellHasNoGroupException {
		if (!board[row][col].hasGroup())
			throw new CellHasNoGroupException(row, col);
		if (col == 0)
			return true;
		return !board[row][col].getGroup().hasCell(board[row][col - 1]);
	}

	public boolean hasBottomBorder(int row, int col) throws CellHasNoGroupException {
		if (!board[row][col].hasGroup())
			throw new CellHasNoGroupException(row, col);
		if (row == size - 1)
			return true;
		return !board[row][col].getGroup().hasCell(board[row + 1][col]);
	}

	public boolean hasRightBorder(int row, int col) throws CellHasNoGroupException {
		if (!board[row][col].hasGroup())
			throw new CellHasNoGroupException(row, col);
		return col == size - 1;
	}

	public boolean isFixed(int row, int col) {
		return board[row][col].isFixed();
	}

	public boolean isFull() {
		for (int i = 0; i < size; i++)
			for (int j = 0; j < size; j++)
				if (board[i][j].getValue() == 0)
					return false;
		return true;
	}

	public boolean check() throws OperandsDoNotMatchException, NonIntegerResultException {
		if (numbersRepeatInRows() || numbersRepeatInColumns())
			return false;
		for (Group group : groups)
			if (!group.check())
				return false;
		return true;
	}

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
