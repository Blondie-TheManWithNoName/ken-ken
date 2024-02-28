package tests;

import exceptions.OperandsDoNotMatchException;
import models.operations.OperationLCM;
import org.junit.Test;

public class OperationLCMTest {
	private final static OperationLCM op3 = new OperationLCM(3);
	private final static OperationLCM op6 = new OperationLCM(6);

	@Test
	public void check() throws OperandsDoNotMatchException {
		assert !op3.check(new int[] {2, 3});
		assert op3.check(new int[] {3, 1});
		assert op3.check(new int[] {3, 3});
		assert op3.check(new int[] {3});
		assert !op3.check(new int[] {3, 6});

		assert op6.check(new int[] {2, 3});
		assert !op6.check(new int[] {3, 1});
		assert !op6.check(new int[] {3, 3});
	}
}
