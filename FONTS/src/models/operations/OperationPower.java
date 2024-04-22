package models.operations;

import exceptions.NonIntegerResultException;
import exceptions.OperandsDoNotMatchException;

import java.util.Arrays;

/**
 * OperationPower class is used to solve the power operation (big^small)
 */
public class OperationPower extends OperationLimitedOperands {
	/**
	 * Constructor of OperationPower
	 * @param target the target number of the operation
	 */
	public OperationPower(int target) {
		super("^", target, 2);
	}

	/**
	 * Check if the operands the solver write are valid to solve the power (big^small)
	 * @param operands array of numbers that the solver write
	 * @param groupSize number of cells of the group
	 * @param max (only used in LimitedOperands) maximum value allowed to put on the KenKen
	 * @return true if the operands are valid to solve the power, false otherwise
	 */
	@Override
	public boolean isValidCandidate(int[] operands, int groupSize, int max) {
		if (operands.length == nOperands) {
			try {
				return check(operands);
			} catch (OperandsDoNotMatchException | NonIntegerResultException ignored) {}
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
	 * Calculate the result of the power (big^small)
	 * @param operands array of numbers that the solver write
	 * @return the result of the power
	 * @throws OperandsDoNotMatchException if the operands have not the excepted result, throw this exception
	 */
	@Override
	public int calculate(int[] operands) throws OperandsDoNotMatchException {
		if (operands.length != nOperands)
			throw new OperandsDoNotMatchException(symbol, nOperands, operands.length);
		if (operands[0] < operands[1])
			return (int) Math.pow(operands[1], operands[0]);
		return (int) Math.pow(operands[0], operands[1]);
	}
}
