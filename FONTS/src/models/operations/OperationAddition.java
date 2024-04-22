package models.operations;

/**
 * OperationAddition class is a class that extends Operation class and implements
 * the abstract methods of the Operation class. It is used to solve the sum of the
 * operands that the solver write.
 */
public class OperationAddition extends Operation {
	/**
	 * Constructor to create an OperationAddition object
	 * @param target the target value
	 */
	public OperationAddition(int target) {
		super("+", target);
	}

	/**
	 * Method to check if the operands are valid candidates for the target
	 */
	@Override
	public boolean isValidCandidate(int[] operands, int groupSize, int max) {
		if (operands.length == groupSize)
			return calculate(operands) == target;
		return calculate(operands) < target;
	}

	/**
	 * Method to calculate the sum of the operands
	 */
	@Override
	public int calculate(int[] operands) {
		int result = 0;
		for (int operand : operands)
			result += operand;
		return result;
	}
}
