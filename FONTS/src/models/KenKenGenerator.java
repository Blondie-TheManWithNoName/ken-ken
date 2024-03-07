package models;

import exceptions.*;
import models.operations.Operation;
import models.operations.OperationFactory;

import java.util.*;

public class KenKenGenerator {
	private final int size;
	private final Topology topology;

	public KenKenGenerator(int size, Topology topology) {
		this.size = size;
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
	}

	private void generateGroups(KenKen kenKen) throws CellHasNoGroupException, GroupCellsNotContiguousException, CannotCreateOperationException {
		int[][] groupMap = new int[size][size];
		while(hasEmptySpaces(groupMap)) {
			// TODO: implement group generation
			groupMap[0][0] = 1;
			groupMap[0][1] = 1;
			groupMap[0][2] = 1;
			groupMap[0][3] = 2;
			groupMap[1][0] = 1;
			groupMap[1][1] = 2;
			groupMap[1][2] = 2;
			groupMap[1][3] = 2;
			groupMap[2][0] = 3;
			groupMap[2][1] = 3;
			groupMap[2][2] = 3;
			groupMap[2][3] = 4;
			groupMap[3][0] = 3;
			groupMap[3][1] = 4;
			groupMap[3][2] = 4;
			groupMap[3][3] = 4;
		}

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

	private boolean hasEmptySpaces(int[][] groupMap) {
		for (int i = 0; i < size; i++)
			for (int j = 0; j < size; j++)
				if (groupMap[i][j] == 0)
					return true;
		return false;
	}
}
