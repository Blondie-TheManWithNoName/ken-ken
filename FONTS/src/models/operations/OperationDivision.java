package models.operations;

import exceptions.NonIntegerResultException;
import exceptions.OperandsDoNotMatchException;

import java.util.Arrays;

/**
 * OperationDivision class is a class that extends OperationLimitedOperands class and implements the calculate and isValidCandidate methods
 * This class is used to represent the division operation
 */
public class OperationDivision extends OperationLimitedOperands {
	/**
	 * Constructor of the OperationDivision class
	 * @param target the target value of the division operation
	 */
	public OperationDivision(int target) {
		super("/", target, 2);
	}

	/**
	 * Check if the operands that the solver write are valid to solve the division
	 */
	@Override
	public boolean isValidCandidate(int[] operands, int groupSize, int max) {
		if (operands.length == nOperands) {
			try {
				return calculate(operands) == target;
			} catch (OperandsDoNotMatchException ignored) {
			} catch (NonIntegerResultException e) {
				return false;
			}
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
	 * Calculate the division of the operands the solver write
	 */
	@Override
	public int calculate(int[] operands) throws OperandsDoNotMatchException, NonIntegerResultException {
		if (operands.length != nOperands)
			throw new OperandsDoNotMatchException(symbol, nOperands, operands.length);
		if (operands[0] < operands[1] && operands[1] % operands[0] == 0)
			return operands[1] / operands[0];
		if (operands[0] % operands[1] == 0)
			return operands[0] / operands[1];
		throw new NonIntegerResultException(symbol);
	}
}
