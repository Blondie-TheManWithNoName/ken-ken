package models.operations;

/**
 * Class that represents an operation with a limited number of operands
 */
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
	 * Method to get the maximum number of operands of the LimitedOperands operation
	 * @return maximum of operands of the LimitedOperands operation
	 */
	public int getNOperands() {
		return nOperands;
	}
}
