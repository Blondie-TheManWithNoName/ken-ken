package models.operations;

import exceptions.NonIntegerResultException;
import exceptions.OperandsDoNotMatchException;

import java.util.*;

public abstract class Operation {
	protected final String symbol;
	protected final int target;

	public Operation(String symbol, int target) {
		this.symbol = symbol;
		this.target = target;
	}

	public String getSymbol() {
		return symbol;
	}

	public String getNotation() {
		return symbol + target;
	}

	public abstract int calculate(int[] operands) throws OperandsDoNotMatchException, NonIntegerResultException;

	public boolean check(int[] operands) throws OperandsDoNotMatchException, NonIntegerResultException {
		return calculate(operands) == target;
	}

	public List<int[]> inverse(int max, int size) throws OperandsDoNotMatchException {
		List<int[]> result = new ArrayList<>();

		generateNPlets(result, new int[size], max, 0, 1);

		return result.isEmpty() ? null : result;
	}

	private boolean exceedsMaxRepeats(int[] array) {
		Map<Integer, Integer> countMap = new HashMap<>();
		int maxRepeats = array.length / 2;

		for (int num : array) {
			countMap.put(num, countMap.getOrDefault(num, 0) + 1);
			if (countMap.get(num) > maxRepeats)
				return true;
		}

		return false;
	}

	private void generateNPlets(List<int[]> result, int[] nPlet, int max, int index, int current) throws OperandsDoNotMatchException {
		if (index == nPlet.length) {
			try {
				if (calculate(nPlet) == target && !exceedsMaxRepeats(nPlet))
					result.add(nPlet.clone());
			} catch (NonIntegerResultException ignored) {}
			return;
		}

		for (int i = current; i <= max; i++) {
			nPlet[index] = i;
			generateNPlets(result, nPlet, max, index + 1, i);
		}
	}
}
