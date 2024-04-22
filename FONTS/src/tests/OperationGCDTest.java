package tests;

import exceptions.NonIntegerResultException;
import exceptions.OperandsDoNotMatchException;
import models.operations.OperationGCD;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class OperationGCDTest {
	private final static OperationGCD op2 = new OperationGCD(2);
	private final static OperationGCD op3 = new OperationGCD(3);

	@Test
	public void check() throws OperandsDoNotMatchException, NonIntegerResultException {
		assert op2.check(new int[]{2, 4});
		assert !op2.check(new int[]{2, 3});
		assert op2.check(new int[]{2, 6});
		assert op2.check(new int[]{2, 8});

		assert !op3.check(new int[]{1, 2, 3});
		assert op3.check(new int[]{3, 6, 9});
		assert op3.check(new int[]{3, 3, 3});
		assert !op3.check(new int[]{3, 3, 4});
	}
}
