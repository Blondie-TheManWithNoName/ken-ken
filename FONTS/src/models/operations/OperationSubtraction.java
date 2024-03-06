package models.operations;

import exceptions.OperandsDoNotMatchException;

import java.util.Arrays;

public class OperationSubtraction extends OperationLimitedOperands {
	public OperationSubtraction(int target) {
		super("-", target, 2);
	}

	@Override
	public boolean isValidCandidate(int[] operands, int groupSize, int max) {
		if (operands.length == nOperands) {
			try {
				return calculate(operands) == target;
			} catch (OperandsDoNotMatchException ignored) {}
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

	@Override
	public int calculate(int[] operands) throws OperandsDoNotMatchException {
		if (operands.length != nOperands)
			throw new OperandsDoNotMatchException(symbol, nOperands, operands.length);
		if (operands[0] < operands[1])
			return operands[1] - operands[0];
		return operands[0] - operands[1];
	}
}
