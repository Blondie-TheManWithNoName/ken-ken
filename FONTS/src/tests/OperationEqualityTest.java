package tests;

import models.operations.OperationEquality;
import org.junit.Test;

public class OperationEqualityTest {
	private final OperationEquality op3 = new OperationEquality(3);

	@Test
	public void check() {
		try {
			assert op3.calculate(new int[]{3}) == 3;
		} catch (Exception e) {
			assert false;
		}
	}
}
