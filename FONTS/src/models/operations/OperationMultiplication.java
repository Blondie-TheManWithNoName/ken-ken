package models.operations;

public class OperationMultiplication extends Operation {
	public OperationMultiplication(int target) {
		super("*", target);
	}

	@Override
	public boolean isValidCandidate(int[] operands, int groupSize, int max) {
		if (operands.length == groupSize)
			return calculate(operands) == target;
		return calculate(operands) <= target;
	}

	@Override
	public int calculate(int[] operands) {
		int result = 1;
		for (int operand : operands)
			result *= operand;
		return result;
	}
}
