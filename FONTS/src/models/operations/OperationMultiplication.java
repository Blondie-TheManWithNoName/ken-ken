package models.operations;

public class OperationMultiplication extends Operation {
	public OperationMultiplication(int target) {
		super("*", target);
	}

	/**
	 * check if the operands that the solver write are valid to solve the multiplication
	 * @param operands array of numbers that the solver write
	 * @param groupSize number of cells of the group
	 * @param max (only used in LimitedOperands) maximum value allowed to put on the KenKen
	 * @return true if the operands are valid to solve the multiplication, false otherwise
	 */
	@Override
	public boolean isValidCandidate(int[] operands, int groupSize, int max) {
		if (operands.length == groupSize)
			return calculate(operands) == target;
		return calculate(operands) <= target;
	}

	/**
	 * calculate the result of the multiplication
	 * @param operands array of numbers that the solver write
	 * @return the result of the multiplication
	 */
	@Override
	public int calculate(int[] operands) {
		int result = 1;
		for (int operand : operands)
			result *= operand;
		return result;
	}
}
