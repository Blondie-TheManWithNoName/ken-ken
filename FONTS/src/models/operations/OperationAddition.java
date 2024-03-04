package models.operations;

public class OperationAddition extends Operation {
	public OperationAddition(int target) {
		super("+", target);
	}

	@Override
	public boolean isValidCandidate(int[] operands, int groupSize, int max) {
		if (operands.length == groupSize)
			return calculate(operands) == target;
		return calculate(operands) < target;
	}

	@Override
	public int calculate(int[] operands) {
		int result = 0;
		for (int operand : operands)
			result += operand;
		return result;
	}
}
