package tests;

import exceptions.CannotCreateOperationException;
import models.operations.*;
import org.junit.Test;

public class OperationFactoryTest {
	@Test
	public void check() {
		Operation operation;

		try {
			operation = OperationFactory.createOperation(OperationAddition.class, 3);
			assert operation.getNotation().contains("+3");
		} catch (CannotCreateOperationException e) {
			assert false;
		}

		try {
			operation = OperationFactory.createOperation(OperationSubtraction.class, 20);
			assert operation.getNotation().contains("-20");
		} catch (CannotCreateOperationException e) {
			assert false;
		}

		try {
			operation = OperationFactory.createOperation("gcd", 9);
			assert operation.getNotation().contains("gcd9");
		} catch (CannotCreateOperationException e) {
			assert false;
		}

		try {
			operation = OperationFactory.createOperation("lcm", 20);
			assert operation.getNotation().contains("lcm20");
		} catch (CannotCreateOperationException e) {
			assert false;
		}

		try {
			operation = OperationFactory.createOperation("pow", 2);
			assert false;
		} catch (CannotCreateOperationException e) {
			assert true;
		}

		try {
			operation = OperationFactory.createOperation("^", 9);
			assert operation.getNotation().contains("^9");
		} catch (CannotCreateOperationException e) {
			assert false;
		}
	}
}
