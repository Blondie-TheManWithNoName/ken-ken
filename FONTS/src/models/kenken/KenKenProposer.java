package models.kenken;

import exceptions.*;
import models.color.ColorFactory;
import models.operations.Operation;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.IntStream;

/**
 * Class to propose a KenKen
 */
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
	 * @return the KenKen the user is proposing
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
	 * Clears a fixed cell
	 * @param row, defines the row of the KenKen to clear the value from
	 * @param col, defines the column of the KenKen to clear the value from
	 */
	public void clearFixedPosition(int row, int col) {
		kenKen.clearPosition(row, col);
	}

	/**
	 * Method to check if at least one group has been created
	 * @return true if there is at least one group, false otherwise
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
	 * Method to get the group notations in an array
	 * @return the group notations in an array
	 */
	public String[] getGroupNotationsEnum() {
		return IntStream.range(0, groupColors.size()).mapToObj(i -> String.format("%d) %s", i+1, ((Group) groupColors.keySet().toArray()[i]).getNotation())).toArray(String[]::new);
	}

	/**
	 * Getter for a group given an index
	 * @param index of the group to get
	 * @return the group at the given index
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
	 * Generates the groups for the KenKen
	 * @throws TooManyOperandsException when an operation has too many operands
	 * @throws CellAlreadyInGroupException when a cell is in more than one group
	 * @throws CellHasNoGroupException when a cell has no group
	 * @throws GroupCellsNotContiguousException when the cells of a group are not contiguous
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
