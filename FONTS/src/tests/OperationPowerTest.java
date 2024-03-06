package tests;

import exceptions.NonIntegerResultException;
import exceptions.OperandsDoNotMatchException;
import models.operations.OperationPower;
import org.junit.Test;

public class OperationPowerTest {
	private final static OperationPower op8 = new OperationPower(8);
	private final static OperationPower op9 = new OperationPower(9);

	@Test
	public void check() throws NonIntegerResultException {
		try {
			assert op8.calculate(new int[]{2, 3}) == 9;
		} catch (OperandsDoNotMatchException e) {
			assert false;
		}

		assert op9.isValidCandidate(new int[]{2, 3}, 2, 3);
		assert op9.isValidCandidate(new int[]{2}, 2, 3);
		assert op9.isValidCandidate(new int[]{3}, 2, 3);
		assert !op9.isValidCandidate(new int[]{1}, 2, 3);
	}
}
