package models.operations;

import exceptions.OperandsDoNotMatchException;

import java.util.List;

public abstract class OperationLimitedOperands extends Operation {
	protected final int nOperands;

	public OperationLimitedOperands(String symbol, int target, int nOperands) {
		super(symbol, target);
		this.nOperands = nOperands;
	}

	public int getNOperands() {
		return nOperands;
	}

	@Override
	public List<int[]> inverse(int max, int size) throws OperandsDoNotMatchException {
		if (size != nOperands)
			throw new OperandsDoNotMatchException(symbol, nOperands, size);
		return super.inverse(max, size);
	}

	public List<int[]> inverse(int max) throws OperandsDoNotMatchException {
		return inverse(max, nOperands);
	}
}
