package models.kenken;

import exceptions.*;
import models.color.ColorFactory;
import models.operations.Operation;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class KenKenProposer {
	// Maximum size for a KenKen
	public static final int MAX_SIZE = 15;
	// KenKen instance
	private final KenKen kenKen;
	// Map containing all the groups with a color
	private final Map<Group, Color> groupColors = new LinkedHashMap<>();
	// Map containing all the cells linked to a group
	private final Map<Cell, Group> cellGroups = new LinkedHashMap<>();

	/**
	 * Creates a KenKen with the given size
	 * @param size of the KenKen to be created
	 */
	public KenKenProposer(int size) {
		this.kenKen = new KenKen(size);
	}

	/**
	 * Getter for the KenKen size
	 * @return the size of the KenKen
	 */
	public int getSize() {
		return kenKen.getSize();
	}

	/**
	 * Getter for the proposed KenKen
	 * @returns the KenKen the user is proposing
	 */
	public KenKen getKenKen() {
		return kenKen;
	}

	/**
	 * Setter for a fixed cell
	 * @param row, defines the row of the KenKen to put the value on
	 * @param col, defines the column of the KenKen to put the value on
	 * @param value to be set
	 * @throws ValueOutOfBoundsException when the value is less than 1 or more than the KenKen size
	 */
	public void setFixedPosition(int row, int col, int value) throws ValueOutOfBoundsException {
		kenKen.setFixedPosition(row, col, value);
	}

	/**
	 *
	 * @param row, defines the row of the KenKen to clear the value from
	 * @param col, defines the column of the KenKen to clear the value from
	 */
	public void clearFixedPosition(int row, int col) {
		kenKen.clearPosition(row, col);
	}

	/**
	 *
	 * @return
	 */
	public boolean anyGroup() {
		return !groupColors.isEmpty();
	}

	/**
	 * Creates a group for the KenKen
	 * @param operation defines the operation of the group to be created
	 * @return the created group
	 */
	public Group createGroup(Operation operation) {
		Group group = new Group(operation);
		groupColors.put(group, ColorFactory.nextColor(new ArrayList<>(groupColors.values())));
		return group;
	}

	/**
	 *
	 * @return
	 */
	public String[] getGroupNotationsEnum() {
		return IntStream.range(0, groupColors.size()).mapToObj(i -> String.format("%d) %s", i+1, ((Group) groupColors.keySet().toArray()[i]).getNotation())).toArray(String[]::new);
	}

	/**
	 *
	 * @param index
	 * @return
	 */
	public Group getGroup(int index) {
		return (Group) groupColors.keySet().toArray()[index];
	}

	/**
	 * Getter for a group given a cell
	 * @param row, defines the row of the KenKen to get the group from
	 * @param col, defines the column of the KenKen to get the group from
	 * @return the group which the given cell belongs to
	 */
	public Group getCellGroup(int row, int col) {
		return cellGroups.get(kenKen.getCell(row, col));
	}

	/**
	 * Getter for the color of a given group
	 * @param group, defines the group to get the color from
	 * @return the color of the given group
	 */
	public Color getGroupColor(Group group) {
		return groupColors.get(group);
	}

	/**
	 * Adds a cell to a given group
	 * @param row, defines the row of the KenKen to get the cell from
	 * @param col, defines the column of the KenKen to get the cell from
	 * @param group, defines the group to put the cell on
	 * @throws GroupDoesNotExistException when the group does not exist
	 */
	public void addCellToGroup(int row, int col, Group group) throws GroupDoesNotExistException {
		if (!groupColors.containsKey(group))
			throw new GroupDoesNotExistException();
		cellGroups.put(kenKen.getCell(row, col), group);
	}

	/**
	 * Removes a given cell from the group it belongs to
	 * @param row, defines the row of the KenKen to get the cell from
	 * @param col, defines the column of the KenKen to get the cell from
	 */
	public void removeCellGroup(int row, int col) {
		cellGroups.remove(kenKen.getCell(row, col));
	}

	/**
	 * Deletes the given group from the KenKenpropose
	 * @param group to be deleted
	 */
	public void deleteGroup(Group group) {
		groupColors.remove(group);
		cellGroups.entrySet().removeIf(entry -> entry.getValue() == group);
	}

	/**
	 *
	 * @throws TooManyOperandsException
	 * @throws CellAlreadyInGroupException
	 * @throws CellHasNoGroupException
	 * @throws GroupCellsNotContiguousException
	 */
	public void generateGroups() throws TooManyOperandsException, CellAlreadyInGroupException, CellHasNoGroupException, GroupCellsNotContiguousException {
		if (cellGroups.size() < getSize() * getSize())
			throw new CellHasNoGroupException();

		for (Group group : new ArrayList<>(groupColors.keySet()))
			if (!cellGroups.containsValue(group))
				deleteGroup(group);

		for (Group group : groupColors.keySet()) {
			kenKen.addGroup(group.getOperation());
			for (Cell cell : cellGroups.keySet())
				if (cellGroups.get(cell) == group) {
					try {
						kenKen.addCellToLastGroup(cell.getRow(), cell.getCol());
					} catch (CellAlreadyInGroupException | TooManyOperandsException e) {
						kenKen.clearGroups();
						throw e;
					}
				}
		}

		kenKen.checkCellsGroups();
	}
}
