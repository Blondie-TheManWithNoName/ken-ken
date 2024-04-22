package tests;

import exceptions.NonIntegerResultException;
import exceptions.OperandsDoNotMatchException;
import models.operations.OperationDivision;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class OperationDivisionTest {
	private final static OperationDivision op3 = new OperationDivision(3);

	@Test
	public void check() {
		try {
			op3.check(new int[]{3, 1, 2});
			assert false;
		} catch (OperandsDoNotMatchException e) {
			assert true;
		} catch (NonIntegerResultException e) {
			assert false;
		}

		try {
			op3.check(new int[]{3, 1});
			assert true;
		} catch (OperandsDoNotMatchException | NonIntegerResultException e) {
			assert false;
		}

		try {
			op3.check(new int[]{9, 3});
			assert true;
		} catch (OperandsDoNotMatchException | NonIntegerResultException e) {
			assert false;
		}

		try {
			op3.check(new int[]{3, 9});
			assert true;
		} catch (OperandsDoNotMatchException | NonIntegerResultException e) {
			assert false;
		}

		try {
			op3.check(new int[]{3});
			assert false;
		} catch (OperandsDoNotMatchException e) {
			assert true;
		} catch (NonIntegerResultException e) {
			assert false;
		}

		try {
			op3.check(new int[]{2, 7});
			assert false;
		} catch (OperandsDoNotMatchException e) {
			assert false;
		} catch (NonIntegerResultException e) {
			assert true;
		}
	}
}
