package models.operations;

import exceptions.OperandsDoNotMatchException;

public class OperationEquality extends OperationLimitedOperands {
	public OperationEquality(int target) {
		super("=", target, 1);
	}

	@Override
	public boolean isValidCandidate(int[] operands, int groupSize, int max) {
		return operands[0] == target;
	}

	@Override
	public int calculate(int[] operands) throws OperandsDoNotMatchException {
		if (operands.length != nOperands) 
			throw new OperandsDoNotMatchException(symbol, nOperands, operands.length);
		return operands[0];
	}
}
