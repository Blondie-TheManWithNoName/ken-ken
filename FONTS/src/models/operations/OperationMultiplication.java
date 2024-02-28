package models.operations;

public class OperationMultiplication extends Operation {
	public OperationMultiplication(int target) {
		super("*", target);
	}

	@Override
	public int calculate(int[] operands) {
		int result = 1;
		for (int operand : operands)
			result *= operand;
		return result;
	}
}
