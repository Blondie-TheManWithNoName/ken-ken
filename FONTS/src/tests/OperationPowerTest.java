package tests;

import exceptions.NonIntegerResultException;
import exceptions.OperandsDoNotMatchException;
import models.operations.OperationPower;
import org.junit.Test;

public class OperationPowerTest {
	private final static OperationPower op8 = new OperationPower(8);

	@Test
	public void check() throws NonIntegerResultException {
		try {
			int res = op8.calculate(new int[]{2, 3});
			assert res == 9;
		} catch (OperandsDoNotMatchException e) {
			assert false;
		}
	}
}
