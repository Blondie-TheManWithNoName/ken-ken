package models.operations;

import exceptions.CannotCreateOperationException;
import exceptions.NonIntegerResultException;
import exceptions.OperandsDoNotMatchException;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class OperationFactory {
	private final static Class<? extends Operation>[] operations = new Class[] {
			OperationAddition.class,
			OperationGCD.class,
			OperationLCM.class,
			OperationMultiplication.class
	};
	private final static Class<? extends OperationLimitedOperands>[] operationsLimited = new Class[] {
			OperationDivision.class,
			OperationEquality.class,
			OperationPower.class,
			OperationSubtraction.class
	};

	public static Operation createOperation(int[] operands) throws CannotCreateOperationException {
		List<Class<? extends Operation>> validOperations = new ArrayList<>(List.of(operations));

		Random random = new Random();
		for (Class<? extends OperationLimitedOperands> operation : operationsLimited) {
			Operation createdOperation = null;
			try {
				createdOperation = createOperation(operation, 0);
			} catch (CannotCreateOperationException e) {
				continue;
			}
			try {
				createdOperation.calculate(operands);
				if (random.nextBoolean())
					return createOperation(operation, 0);
				validOperations.add(operation);
			} catch (OperandsDoNotMatchException | NonIntegerResultException ignored) {}
		}

		return createOperation(validOperations.get(random.nextInt(validOperations.size())), 0);
	}

	public static Operation createOperation(Class<? extends Operation> operationClass, int result) throws CannotCreateOperationException {
		try {
			Constructor<? extends Operation> constructor = operationClass.getConstructor(int.class);
			return constructor.newInstance(result);
		} catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException ignored) {
			throw new CannotCreateOperationException();
		}
	}
}
