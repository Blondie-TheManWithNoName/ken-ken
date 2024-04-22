package models.operations;

/**
 * OperationMultiplication class is a class that extends Operation class and implements the methods of the abstract class.
 * This class is used to solve the multiplication operation in the KenKen puzzle.
 */
public class OperationMultiplication extends Operation {
	/**
	 * Constructor of the OperationMultiplication class
	 * @param target the target value of the operation
	 */
	public OperationMultiplication(int target) {
		super("*", target);
	}

	/**
	 * Check if the operands that the solver write are valid to solve the multiplication
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
	 * Calculate the result of the multiplication
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
