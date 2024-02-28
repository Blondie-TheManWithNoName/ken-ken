package tests;

import exceptions.OperandsDoNotMatchException;
import models.operations.OperationDivision;
import org.junit.Test;

public class OperationDivisionTest {
	private final static OperationDivision op3 = new OperationDivision(3);

	@Test
	public void check() {
		try {
			op3.check(new int[]{3, 1, 2});
			assert false;
		} catch (OperandsDoNotMatchException e) {
			assert true;
		}

		try {
			op3.check(new int[]{3, 1});
			assert true;
		} catch (OperandsDoNotMatchException e) {
			assert false;
		}

		try {
			op3.check(new int[]{9, 3});
			assert true;
		} catch (OperandsDoNotMatchException e) {
			assert false;
		}

		try {
			op3.check(new int[]{3, 9});
			assert true;
		} catch (OperandsDoNotMatchException e) {
			assert false;
		}

		try {
			op3.check(new int[]{3});
			assert false;
		} catch (OperandsDoNotMatchException e) {
			assert true;
		}
	}
}
