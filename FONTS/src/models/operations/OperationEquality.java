package models.operations;

import exceptions.OperandsDoNotMatchException;

public class OperationEquality extends OperationLimitedOperands {
	public OperationEquality(int target) {
		super("=", target, 1);
	}

	@Override
	public int calculate(int[] operands) throws OperandsDoNotMatchException {
		if (operands.length != nOperands) 
			throw new OperandsDoNotMatchException(symbol, nOperands, operands.length);
		if (operands[0] == target) return operands[0];
        else throw new OperandsDoNotMatchException(symbol, target, operands[0]);
	}
}
    
