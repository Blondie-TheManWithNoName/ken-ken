package tests;

import exceptions.OperandsDoNotMatchException;
import models.operations.OperationSubtraction;
import org.junit.Test;

public class OperationSubtractionTest {
	private final static OperationSubtraction op3 = new OperationSubtraction(3);

	@Test
	public void check() {
		try {
			op3.check(new int[]{1, 2, 3});
			assert false;
		} catch (OperandsDoNotMatchException e) {
			assert true;
		}

		try {
			op3.check(new int[]{1, 4});
			assert true;
		} catch (OperandsDoNotMatchException e) {
			assert false;
		}

		try {
			op3.check(new int[]{4, 1});
			assert true;
		} catch (OperandsDoNotMatchException e) {
			assert false;
		}

		try {
			op3.check(new int[]{4});
			assert false;
		} catch (OperandsDoNotMatchException e) {
			assert true;
		}
	}
}
