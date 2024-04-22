package models.operations;

import exceptions.OperandsDoNotMatchException;

/**
 * OperationEquality is a class that extends OperationLimitedOperands and implements the methods
 * isValidCandidate and calculate. It is used to represent the equality operation.
 */
public class OperationEquality extends OperationLimitedOperands {
	/**
	 * Constructor of the OperationEquality class
	 * @param target The target value of the equality
	 */
	public OperationEquality(int target) {
		super("=", target, 1);
	}

	/**
	 * Check if the operands that the solver write are valid to solve the equality
	 */
	@Override
	public boolean isValidCandidate(int[] operands, int groupSize, int max) {
		return operands[0] == target;
	}

	/**
	 * Calculate the result of the equality
	 */
	@Override
	public int calculate(int[] operands) throws OperandsDoNotMatchException {
		if (operands.length != nOperands) 
			throw new OperandsDoNotMatchException(symbol, nOperands, operands.length);
		return operands[0];
	}
}
