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

	/**
	 * Method to get the symbol of the operation
	 * @return symbol of the operation
	 */
	public String getSymbol() {
		return symbol;
	}

	/**
	 * Method to get the target of the operation
	 * @return target of the operation
	 */
	public int getTarget() {
		return target;
	}

	/**
	 * Method to get the string that represents the operation (symbol+target)
	 * @return symbol char and target char
	 */
	public String getNotation() {
		return symbol + target;
	}

	/**
	 * check if the operands that the solver write are valid to solve the operation
	 * @param operands array of numbers that the solver write
	 * @param groupSize number of cells of the group
	 * @param max (only used in LimitedOperands) maximum value allowed to put on the KenKen
	 * @return true if the operands are valid to solve the operation, false otherwise
	 */
	public abstract boolean isValidCandidate(int[] operands, int groupSize, int max);

	/**
	 * calculate the result of the operation
	 * @param operands array of numbers that the solver write
	 * @return result of the operation
	 * @throws OperandsDoNotMatchException if the operands have not the excepted result, throw this exception
	 * @throws NonIntegerResultException if the result is a negative number, throw this exception
	 */
	public abstract int calculate(int[] operands) throws OperandsDoNotMatchException, NonIntegerResultException;

	/**
	 * check if the result is equal to the target
	 * @param operands array of numbers that the solver write
	 * @return true if the result is equal to the target, false otherwise
	 * @throws OperandsDoNotMatchException if the operands have not the excepted result, throw this exception
	 * @throws NonIntegerResultException if the result is a negative number, throw this exception
	 */
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
