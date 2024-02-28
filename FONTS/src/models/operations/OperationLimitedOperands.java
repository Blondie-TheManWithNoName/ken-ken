package models.operations;

public abstract class OperationLimitedOperands extends Operation {
	protected final int nOperands;

	public OperationLimitedOperands(String symbol, int target, int nOperands) {
		super(symbol, target);
		this.nOperands = nOperands;
	}

	public int getNOperands() {
		return nOperands;
	}
}
