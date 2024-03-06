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

		List<int[]> calculated = op2.inverse(6, 2);
		List<int[]> expected = List.of(new int[]{2, 4}, new int[]{2, 6}, new int[]{4, 6});
		assert calculated.size() == expected.size();
		for (int i = 0; i < calculated.size(); i++)
			assert Arrays.equals(calculated.get(i), expected.get(i));
	}
}
