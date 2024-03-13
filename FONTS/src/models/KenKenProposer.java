package models;

import exceptions.CellAlreadyInGroupException;
import exceptions.GroupCellsNotContiguousException;
import exceptions.TooManyOperandsException;
import exceptions.ValueOutOfBoundsException;
import models.color.ColorFactory;
import models.operations.Operation;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class KenKenProposer {
	public static final int MAX_SIZE = 15;
	private final KenKen kenKen;
	private final Map<Group, Color> groupColors = new LinkedHashMap<>();
	private final Map<Cell, Group> cellGroups = new LinkedHashMap<>();

	public KenKenProposer(int size) {
		this.kenKen = new KenKen(size);
	}

	public int getSize() {
		return kenKen.getSize();
	}

	public KenKen getKenKen() {
		return kenKen;
	}

	public void setFixedPosition(int row, int column, int value) throws ValueOutOfBoundsException {
		kenKen.setFixedPosition(row, column, value);
	}

	public void clearFixedPosition(int row, int column) {
		kenKen.clearValue(row, column);
	}

	public boolean anyGroup() {
		return !groupColors.isEmpty();
	}

	public Group createGroup(Operation operation) {
		Group group = new Group(operation);
		groupColors.put(group, ColorFactory.nextColor(new ArrayList<>(groupColors.values())));
		return group;
	}

	public String[] getGroupNotationsEnum() {
		return IntStream.range(0, groupColors.size()).mapToObj(i -> String.format("%d) %s", i+1, ((Group) groupColors.keySet().toArray()[i]).getNotation())).toArray(String[]::new);
	}

	public Group getGroup(int index) {
		return (Group) groupColors.keySet().toArray()[index];
	}

	public Group getCellGroup(int row, int col) {
		return cellGroups.get(kenKen.getCell(row, col));
	}

	public Color getGroupColor(Group group) {
		return groupColors.get(group);
	}

	public void addCellToGroup(int row, int col, Group group) {
		cellGroups.put(kenKen.getCell(row, col), group);
	}

	public void removeCellGroup(int row, int col) {
		cellGroups.remove(kenKen.getCell(row, col));
	}

	public void deleteGroup(Group group) {
		groupColors.remove(group);
		cellGroups.entrySet().removeIf(entry -> entry.getValue() == group);
	}

	public void generateGroups() throws TooManyOperandsException {
		if (cellGroups.size() < getSize() * getSize()); // TODO: throw new CellsWithNoGroupException();

		for (Group group : new ArrayList<>(groupColors.keySet()))
			if (!cellGroups.containsValue(group))
				deleteGroup(group);

		for (Group group : groupColors.keySet()) {
			kenKen.addGroup(group.getOperation());
			for (Cell cell : cellGroups.keySet())
				if (cellGroups.get(cell) == group) {
					try {
						kenKen.addCellToLastGrop(cell.getRow(), cell.getCol());
					} catch (GroupCellsNotContiguousException | CellAlreadyInGroupException ignored) {
					} catch (TooManyOperandsException e) {
						kenKen.clearGroups();
						throw e;
					}
				}
		}

		// TODO: checkCellGroups();
	}
}
