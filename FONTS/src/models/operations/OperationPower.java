package models.operations;

import exceptions.OperandsDoNotMatchException;

public class OperationPower extends OperationLimitedOperands {
	public OperationPower(int target) {
		super("^", target, 2);
	}

	@Override
	public int calculate(int[] operands) throws OperandsDoNotMatchException {
		if (operands.length != nOperands)
			throw new OperandsDoNotMatchException(symbol, nOperands, operands.length);
		if (operands[0] < operands[1])
			return operands[1] ^ operands[0];
		return operands[0] ^ operands[1];
	}
    
}
