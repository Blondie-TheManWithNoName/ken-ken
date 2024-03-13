package models.kenken;

import exceptions.*;
import models.topologies.Topology;
import models.operations.Operation;
import models.operations.OperationFactory;

import java.util.*;

public class KenKenGenerator {
	private final int size;
	private final int fixedValues;
	private final Topology topology;

	public KenKenGenerator(int size, int fixedValues, Topology topology) {
		this.size = size;
		this.fixedValues = fixedValues;
		this.topology = topology;
	}

	public KenKen generate() throws CellHasNoGroupException, GroupCellsNotContiguousException, CannotCreateOperationException {
		KenKen kenKen = new KenKen(size);
		generateLatinSquare(kenKen);
		generateGroups(kenKen);

		kenKen.clear();
		return kenKen;
	}

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

	private void generateGroups(KenKen kenKen) throws CellHasNoGroupException, GroupCellsNotContiguousException, CannotCreateOperationException {
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
				groupValues.add(kenKen.getValue(position[0], position[1]));
			int[] operands = groupValues.stream().mapToInt(i -> i).toArray();
			Operation operation = OperationFactory.createOperation(operands);
			int result = 0;
			try {
				result = operation.calculate(operands);
			} catch (OperandsDoNotMatchException | NonIntegerResultException ignored) {}
			groupOperations.put(
					group,
					OperationFactory.createOperation(operation.getClass(), result)
			);
		}

		for (int group : groups.keySet()) {
			kenKen.addGroup(groupOperations.get(group));
			for (int[] position : groups.get(group)) {
				try {
					kenKen.addCellToLastGrop(position[0], position[1]);
				} catch (TooManyOperandsException | CellAlreadyInGroupException ignored) {}
			}
		}

		kenKen.checkCellsGroups();
	}

	private boolean generateGroups(int[][] groupMap, int row, int col, int nextGroup) {
		if (row == size) {
			row = 0;
			if (++col == size)
				return !hasEmptySpaces(groupMap);
		}

		if (groupMap[row][col] != 0)
			return generateGroups(groupMap, row + 1, col, nextGroup);

		int lowRand = new Random().nextInt(3);
		for (int i = lowRand; i < lowRand + 4; i++) {
			int[][] shape = topology.rotateQuarters(i);
			if (topologyFits(groupMap, shape, row, col)) {
				createGroup(groupMap, shape, row, col, nextGroup);
				if (generateGroups(groupMap, row + 1, col, nextGroup + 1))
					return true;
				removeGroup(groupMap, shape, row, col);
			}
		}

		return generateGroups(groupMap, row + 1, col, nextGroup);
	}

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

	private void removeGroup(int[][] groupMap, int[][] shape, int row, int col) {
		for (int i = 0; i < shape.length; i++)
			for (int j = 0; j < shape[0].length; j++)
				if (shape[i][j] != 0)
					groupMap[row + i][col + j] = 0;
	}

	private boolean hasEmptySpaces(int[][] groupMap) {
		for (int i = 0; i < size; i++)
			for (int j = 0; j < size; j++)
				if (groupMap[i][j] == 0)
					return true;
		return false;
	}
}
