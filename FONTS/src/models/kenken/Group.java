package models.kenken;

import exceptions.*;
import models.operations.Operation;
import models.operations.OperationLimitedOperands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class that represents a group of cells in the KenKen puzzle
 */
public class Group {
	// Operation for the group
	private final Operation operation;
	// List of cells
	private final List<Cell> cells = new ArrayList<>();

	/**
	 * Constructor for group
	 * @param operation takes the operation to be applied to the group
	 */
	public Group(Operation operation) {
		this.operation = operation;
	}

	/**
	 * Getter of operation from the group
	 * @return the operation of the group
	 */
	public Operation getOperation() {
		return operation;
	}

	/**
	 * Getter for the cells of the group
	 * @return the list of cells of the group
	 */
	public List<Cell> getCells() {
		return cells;
	}

	/**
	 * Adds a cell to the group
	 * @param cell, cell to be added to the group
	 * @throws TooManyOperandsException when the operation accepts a limited number of operands/cells (OperationDivision, OperationEquality, OperationPower, OperationSubtraction)
	 * @throws CellAlreadyInGroupException when the cell is already part of a group
	 */
	public void addCell(Cell cell) throws TooManyOperandsException, CellAlreadyInGroupException {
		if (operation instanceof OperationLimitedOperands && cells.size() >=  ((OperationLimitedOperands) operation).getNOperands())
			throw new TooManyOperandsException(operation.getSymbol(), ((OperationLimitedOperands) operation).getNOperands());
		if (cell.hasGroup())
			throw new CellAlreadyInGroupException(cell.getRow(), cell.getCol());
		cells.add(cell);
		cell.setGroup(this);
	}

	/**
	 * Checks if group has a cell
	 * @param cell to check
	 * @return true if group contains the cell, false otherwise
	 */
	public boolean hasCell(Cell cell) {
		return cells.contains(cell);
	}

	/**
	 * Getter for a cell given the index on the group list
	 * @param index indicates te cell on the group list
	 * @return cell
	 */
	public Cell getCell(int index) {
		return cells.get(index);
	}

	/**
	 * Checks if all the cells on the group are next to each other in order to fulfill the KenKen rules
	 * @return true if all the cells on the group are adjacent, false otherwise
	 */
	public boolean cellsAreAdjacent() {
		if (cells.size() < 2)
			return true;
		for (Cell currentCell : cells) {
			boolean adjacent = false;
			for (Cell otherCell : cells) {
				if (currentCell.isAdjacent(otherCell)) {
					adjacent = true;
					break;
				}
			}
			if (!adjacent)
				return false;
		}
		return true;
	}

	/**
	 * Getter for the notation of the group
	 * @return the notation of the current group
	 */
	public String getNotation() {
		return operation.getNotation();
	}

	/**
	 * Checks if a value, along with the non-empty cells of the group, is valid to reach the target of the current group
	 * @param value to be checked along with the other operands of the group
	 * @param size, size of KenKen to determine maximum value that can be written
	 * @return true if the value fits the KenKen and Group conditions
	 */
	public boolean isValidMove(int value, int size, int boardSolved[][]) {
		int[] operands = cells.stream()
				.filter(cell -> boardSolved[cell.getRow()][cell.getCol()] != 0)
				.mapToInt(cell -> boardSolved[cell.getRow()][cell.getCol()])
				.toArray();
		int[] newOperands = Arrays.copyOf(operands, operands.length + 1);
		newOperands[operands.length] = value;
		return operation.isValidCandidate(newOperands, cells.size(), size);
	}

	/**
	 * Checks if the group is not empty, and then if all the values fulfill its target
	 * @return true if all the cells of the current group are not empty and fulfill the operation target
	 * @throws OperandsDoNotMatchException when the operation get more operands than needed (Used for Operations with Limited Operands)
	 * @throws NonIntegerResultException when the result or the operation is negative
	 */
	public boolean check() throws OperandsDoNotMatchException, NonIntegerResultException {
		for (Cell cell : cells)
			if (cell.isEmpty())
				return false;
		return operation.check(cells.stream().mapToInt(Cell::getValue).toArray());
	}
}
