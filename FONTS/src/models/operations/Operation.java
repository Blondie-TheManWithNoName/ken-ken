package models.operations;

import exceptions.OperandsDoNotMatchException;

public abstract class Operation {
	protected final String symbol;
	protected final int target;

	public Operation(String symbol, int target) {
		this.symbol = symbol;
		this.target = target;
	}

	public String getSymbol() {
		return symbol;
	}

	public String getNotation() {
		return symbol + target;
	}

	public abstract int calculate(int[] operands) throws OperandsDoNotMatchException;

	public boolean check(int[] operands) throws OperandsDoNotMatchException {
		return calculate(operands) == target;
	}
}
