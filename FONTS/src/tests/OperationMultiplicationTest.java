package tests;

import exceptions.NonIntegerResultException;
import exceptions.OperandsDoNotMatchException;
import models.operations.OperationMultiplication;
import org.junit.Test;

public class OperationMultiplicationTest {
	private final static OperationMultiplication op3 = new OperationMultiplication(3);
	private final static OperationMultiplication op6 = new OperationMultiplication(6);
	private final static OperationMultiplication op9 = new OperationMultiplication(9);

	@Test
	public void check() throws OperandsDoNotMatchException, NonIntegerResultException {
		assert !op3.check(new int[]{1, 2, 3});
		assert op3.check(new int[]{1, 3});

		assert op6.check(new int[]{1, 2, 3});
		assert !op6.check(new int[]{1, 3});

		assert !op9.check(new int[]{1, 3});
		assert !op9.check(new int[]{1, 2, 3});
		assert op9.check(new int[]{1, 3, 1, 3});
	}
}
