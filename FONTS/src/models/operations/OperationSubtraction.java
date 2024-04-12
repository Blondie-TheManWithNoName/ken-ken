package models.operations;

import exceptions.OperandsDoNotMatchException;

import java.util.Arrays;

public class OperationSubtraction extends OperationLimitedOperands {
	public OperationSubtraction(int target) {
		super("-", target, 2);
	}

	/**
	 * check if the operands the solver write are valid to solve the subtraction
	 * @param operands array of numbers that the solver write
	 * @param groupSize number of cells of the group
	 * @param max (only used in LimitedOperands) maximum value allowed to put on the KenKen
	 * @return true if the operands are valid to solve the subtraction, false otherwise
	 */
	@Override
	public boolean isValidCandidate(int[] operands, int groupSize, int max) {
		if (operands.length == nOperands) {
			try {
				return calculate(operands) == target;
			} catch (OperandsDoNotMatchException ignored) {}
		}
		int[] newOperands = Arrays.copyOf(operands, operands.length + 1);
		for (int i = 1; i <= max; i++) {
			if (i == operands[0])
				continue;
			newOperands[1] = i;
			if (isValidCandidate(newOperands, groupSize, max))
				return true;
		}
		return false;
	}

	/**
	 * calculate the result of the multiplication
	 * @param operands array of numbers that the solver write
	 * @return the result of the subtraction
	 * @throws OperandsDoNotMatchException true if the operands are valid to solve the subtraction, false otherwise
	 */
	@Override
	public int calculate(int[] operands) throws OperandsDoNotMatchException {
		if (operands.length != nOperands)
			throw new OperandsDoNotMatchException(symbol, nOperands, operands.length);
		if (operands[0] < operands[1])
			return operands[1] - operands[0];
		return operands[0] - operands[1];
	}
}
