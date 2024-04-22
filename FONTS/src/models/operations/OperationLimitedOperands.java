package models.operations;

import exceptions.OperandsDoNotMatchException;

import java.util.List;

public abstract class OperationLimitedOperands extends Operation {
	protected final int nOperands;

	/**
	 * Constructor of operations with limited operands
	 * @param symbol symbol that determines which operation we have to do
	 * @param target number that must be the result of the operation
	 * @param nOperands maximum of operands we can use to solve the operation
	 */
	public OperationLimitedOperands(String symbol, int target, int nOperands) {
		super(symbol, target);
		this.nOperands = nOperands;
	}

	/**
	 * function to get the maximum of operands
	 * @return maximum of operands of the LimitedOperands operation
	 */
	public int getNOperands() {
		return nOperands;
	}


}
