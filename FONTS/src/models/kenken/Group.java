package models.kenken;

import exceptions.*;
import models.operations.Operation;
import models.operations.OperationLimitedOperands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Group {
	private final Operation operation;
	private final List<Cell> cells = new ArrayList<>();

	public Group(Operation operation) {
		this.operation = operation;
	}

	public Operation getOperation() {
		return operation;
	}

	public void addCell(Cell cell) throws TooManyOperandsException, CellAlreadyInGroupException {
		if (operation instanceof OperationLimitedOperands && cells.size() >=  ((OperationLimitedOperands) operation).getNOperands())
			throw new TooManyOperandsException(operation.getSymbol(), ((OperationLimitedOperands) operation).getNOperands());
		if (cell.hasGroup())
			throw new CellAlreadyInGroupException(cell.getRow(), cell.getCol());
		cells.add(cell);
		cell.setGroup(this);
	}

	public boolean hasCell(Cell cell) {
		return cells.contains(cell);
	}

	public Cell getCell(int index) {
		return cells.get(index);
	}

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

	public String getNotation() {
		return operation.getNotation();
	}

	public boolean isValidMove(int value, int size) {
		int[] operands = cells.stream().mapToInt(Cell::getValue).filter(v -> v != 0).toArray();
		int[] newOperands = Arrays.copyOf(operands, operands.length + 1);
		newOperands[operands.length] = value;
		return operation.isValidCandidate(newOperands, cells.size(), size);
	}

	public boolean check() throws OperandsDoNotMatchException, NonIntegerResultException {
		for (Cell cell : cells)
			if (cell.getValue() == 0)
				return false;
		return operation.check(cells.stream().mapToInt(Cell::getValue).toArray());
	}
}
