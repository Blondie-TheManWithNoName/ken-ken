package models.kenken;

import java.util.*;

import exceptions.*;
import models.operations.Operation;
import models.operations.OperationEquality;
import models.operations.OperationFactory;
import models.operations.OperationLimitedOperands;
import models.topologies.Shape;
import models.topologies.Topology;

import javax.swing.*;

/**
 * A class for generating KenKen puzzles.
 */
public class KenKenGenerator {
	private final int size;
	private final int fixedValues;
	private final Topology topology;
	private final List<Class<? extends Operation>> allowedOperations;
	private KenKen kenKen;

	/**
     * Constructs a KenKenGenerator with the specified size, fixed values, topology, and allowed operations.
     *
     * @param size              the size of the KenKen puzzle grid
     * @param fixedValues       the number of fixed values in the puzzle
     * @param topology          the topology of the puzzle grid
     * @param allowedOperations the list of allowed operation for generating the groups
	 * @throws OperandsDoNotMatchException if the number of operands does not match the topology size
     */
	public KenKenGenerator(int size, int fixedValues, Topology topology, List<Class<? extends Operation>> allowedOperations) throws ShapesAndOperationsDoNotMatchException, OperandsDoNotMatchException, CannotCreateOperationException, ChooseOperationException {
        this.size = size;
        this.fixedValues = fixedValues;
        this.topology = topology;
		for (Class<? extends Operation> operation : allowedOperations)
			if (OperationLimitedOperands.class.isAssignableFrom(operation)) {
					OperationLimitedOperands op = (OperationLimitedOperands) OperationFactory.createOperation(operation, 0);
					int fit = 0;
					for (int i = 0; i < topology.getSize(); ++i) {
						if (op.getNOperands() != topology.getShape(i).getSize()) ++fit;
					}
					if (fit == topology.getSize()) {
//						allowedOperations.remove(operation);
						throw new ShapesAndOperationsDoNotMatchException(op.getSymbol());
					}


			}
        this.allowedOperations = allowedOperations;
		if (allowedOperations.isEmpty())
			throw new ChooseOperationException();
    }

	/**
     * Gets the generated KenKen puzzle.
     *
     * @return the generated KenKen puzzle
     */
	public KenKen getKenKen() {
		return kenKen;
	}

	/**
     * Generates a KenKen puzzle.
	 * 
     * @return true if the puzzle is generated successfully, false otherwise
     */
	public boolean generate() throws ChooseTopologyException {
		kenKen = new KenKen(size);
		generateLatinSquare(kenKen);
		try {
			generateGroups(kenKen);
		} catch (CellHasNoGroupException | GroupCellsNotContiguousException | CannotCreateOperationException e) {
			kenKen = null;
			System.out.println("OKEY " + e);

			return false;
		}

        kenKen.erase();
		return true;
	}

	/**
     * Sets the KenKen values by creating a Latin square adding numbers diagonally in the grid
	 * and shuffling the rows after, so the generated KenKen are not always equal.
     * @param kenKen the KenKen to set the non-fixed and fixed values on
     */
	private void generateLatinSquare(KenKen kenKen) {
		int[][] latinSquare = new int[size][size];
		List<Integer> values = new ArrayList<>();
		for (int i = 0; i < size; i++)
			values.add(i + 1);
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++)
				latinSquare[i][j] = values.get(j);
			Collections.rotate(values, 1);
		}
		Collections.shuffle(Arrays.asList(latinSquare));

		for (int i = 0; i < size; i++)
			for (int j = 0; j < size; j++) {
				try {
					kenKen.setPosition(i, j, latinSquare[i][j]);
				} catch (ValueOutOfBoundsException | RewriteFixedPositionException ignored) {}
			}

		for (int i = 0; i < fixedValues; i++) {
			int row = new Random().nextInt(size);
			int col = new Random().nextInt(size);
			if (kenKen.isFixed(row, col)) {
				i--;
				continue;
			}
			try {
				kenKen.setFixedPosition(row, col, latinSquare[row][col]);
			} catch (ValueOutOfBoundsException ignored) {}
		}
	}

	/**
     * Generates groups of cells for the KenKen puzzle with only allowed operations.
     * The method filters the allowed operation types and selects operations
     * based on the allowed types when generating groups.
     *
     * @param kenKen the KenKen puzzle grid
     * @throws CellHasNoGroupException          if a cell has no group
     * @throws GroupCellsNotContiguousException if group cells are not contiguous
     * @throws CannotCreateOperationException  if an operation cannot be created
     */
	private void generateGroups(KenKen kenKen) throws CellHasNoGroupException, GroupCellsNotContiguousException, CannotCreateOperationException, ChooseTopologyException {
        int[][] groupMap = new int[size][size];

        if (!generateGroups(groupMap, 0, 0, 1))
			throw new CellHasNoGroupException();

        HashMap<Integer, List<int[]>> groups = new HashMap<>();
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++) {
                if (!groups.containsKey(groupMap[i][j]))
                    groups.put(groupMap[i][j], new ArrayList<>());
                groups.get(groupMap[i][j]).add(new int[]{i, j});
            }

        HashMap<Integer, Operation> groupOperations = new HashMap<>();
        for (int group : groups.keySet()) {
            List<Integer> groupValues = new ArrayList<>();
            for (int[] position : groups.get(group))
			{
				groupValues.add(kenKen.getValue(position[0], position[1]));
			}
			int[] operands = groupValues.stream().mapToInt(i -> i).toArray();


			Operation operation;
			int result = 0;
			try {
				if (operands.length == 1)
				{
					result = operands[0];
					operation = OperationFactory.createOperation("=", result);
				}
				else {
					operation = OperationFactory.createOperation(allowedOperations, operands);
					result = operation.calculate(operands);
				}
				groupOperations.put(
						group,
						OperationFactory.createOperation(operation.getClass(), result)
				);
			} catch (OperandsDoNotMatchException | NonIntegerResultException | CannotCreateOperationException e) {
				System.out.println("result" + e);
			}
        }

        for (int group : groups.keySet()) {
            kenKen.addGroup(groupOperations.get(group));
            for (int[] position : groups.get(group)) {
                try {
                    kenKen.addCellToLastGroup(position[0], position[1]);
                } catch (TooManyOperandsException | CellAlreadyInGroupException ignored) {}
            }
        }

//        kenKen.checkCellsGroups();
    }

	/**
     * Recursively generates groups of cells for the KenKen puzzle grid.
	 * 
     * @param groupMap   the map representing groups of cells
     * @param row        the current row index
     * @param col        the current column index
     * @param nextGroup  the index of the next group to be generated
     * @return true if groups are generated successfully, false otherwise
     */
	private boolean generateGroups(int[][] groupMap, int row, int col, int nextGroup) throws ChooseTopologyException {
//		System.out.println("cell" +row+", " +col + " nextGroup" + nextGroup);

		if (row == size) {
			row = 0;
			if (++col == size) {
				if (hasEmptySpaces(groupMap))
					fillUp(groupMap, nextGroup);
				return true;
			}
		}

		if (groupMap[row][col] != 0)
			return generateGroups(groupMap, row + 1, col, nextGroup);

		int lowRand = new Random().nextInt(3);
		if (topology.getSize() < 1)
			throw new ChooseTopologyException();

		int topRand = new Random().nextInt(topology.getSize());
//		for (int j = 0; j < topRand + 4; j++)
		int j = 0;
		do
		{

			for (int i = lowRand; i < lowRand + 4; i++) {
				int[][] shape = topology.rotateQuarters((topRand + j) % topology.getSize(), i);
				if (topologyFits(groupMap, shape, row, col)) {
					createGroup(groupMap, shape, row, col, nextGroup);
					if (generateGroups(groupMap, row + 1, col, nextGroup + 1)) {
						return true;
					}
					removeGroup(groupMap, shape, row, col);
				}
			}
			++j;
//			System.out.println("cell" +row+", " +col);
		} while (j < topology.getSize());

		return generateGroups(groupMap, row + 1, col, nextGroup);
	}

	/**
     * Checks if a given topology fits in the puzzle grid.
	 * 
     * @param groupMap the map representing groups of cells
     * @param shape    the shape representing the topology
     * @param row      the current row index
     * @param col      the current column index
     * @return true if the topology fits, false otherwise
     */
	private boolean topologyFits(int[][] groupMap, int[][] shape, int row, int col) {
		int rowOffset = 0;
		for (int i = 0; i < shape.length; i++)
			if (shape[i][0] == 1) {
				rowOffset = i;
				break;
			}

		for (int i = 0; i < shape.length; i++)
			for (int j = 0; j < shape[0].length; j++)
				if (shape[i][j] == 1) {
					int rotatedRow = row + i - rowOffset;
					int rotatedCol = col + j;

					if (rotatedRow < 0 || rotatedRow >= groupMap.length)
						return false;
					if (rotatedCol < 0 || rotatedCol >= groupMap[0].length)
						return false;
					if (groupMap[rotatedRow][rotatedCol] != 0)
						return false;
				}
		return true;
	}

	/**
     * Creates a group of cells in the puzzle grid.
	 * 
     * @param groupMap   the map representing groups of cells
     * @param shape      the shape representing the topology
     * @param row        the current row index
     * @param col        the current column index
     * @param nextGroup  the index of the next group
     */
	private void createGroup(int[][] groupMap, int[][] shape, int row, int col, int nextGroup) {
		int rowOffset = 0;
		for (int i = 0; i < shape.length; i++)
			if (shape[i][0] == 1) {
				rowOffset = i;
				break;
			}

		for (int i = 0; i < shape.length; i++)
			for (int j = 0; j < shape[0].length; j++)
				if (shape[i][j] == 1)
					groupMap[row + i - rowOffset][col + j] = nextGroup;
	}

	/**
     * Removes a group of cells from the puzzle grid.
	 * 
     * @param groupMap the map representing groups of cells
     * @param shape    the shape representing the topology
     * @param row      the current row index
     * @param col      the current column index
     */
	private void removeGroup(int[][] groupMap, int[][] shape, int row, int col) {
		System.out.println("CELL: [" + row + ", " + col + "]");
		for (int i = 0; i < shape.length; i++)
			for (int j = 0; j < shape[0].length; j++)
				if (shape[i][j] != 0 && row + i < size && col + j < size)
					groupMap[row + i][col + j] = 0;
	}

	/**
	 * Checks if the group map contains empty spaces.
	 * 
	 * @param groupMap the map representing groups of cells
     * @return true if there are empty spaces, false otherwise
	 */
	private boolean hasEmptySpaces(int[][] groupMap) {
		for (int i = 0; i < size; i++)
			for (int j = 0; j < size; j++)
				if (groupMap[i][j] == 0)
					return true;
		return false;
	}

	private void fillUp(int[][] groupMap, int group) {
		for (int i = 0; i < size; i++)
			for (int j = 0; j < size; j++)
				if (groupMap[i][j] == 0) {
					groupMap[i][j] = ++group;
				}
	}

}
