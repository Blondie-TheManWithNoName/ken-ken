package models.operations;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import exceptions.CannotCreateOperationException;
import exceptions.NonIntegerResultException;
import exceptions.OperandsDoNotMatchException;

/**
 * Factory class for creating operations for KenKen puzzles.
 */
public class OperationFactory {
	private final static Class<? extends Operation>[] OPERATIONS = new Class[] {
			OperationAddition.class,
			OperationGCD.class,
			OperationLCM.class,
			OperationMultiplication.class
	};
	private final static Class<? extends OperationLimitedOperands>[] OPERATIONS_LIMITED = new Class[] {
			OperationDivision.class,
			OperationEquality.class,
			OperationPower.class,
			OperationSubtraction.class
	};2

	/**
     * Creates an operation with the given operands.
     *
     * @param operands The list of operands for the operation.
     * @return The created operation.
     * @throws CannotCreateOperationException If the operation cannot be created.
     */
	public static Operation createOperation(int[] operands) throws CannotCreateOperationException {
		List<Class<? extends Operation>> validOperations = new ArrayList<>(List.of(OPERATIONS));

		Random random = new Random();
		for (Class<? extends OperationLimitedOperands> operation : OPERATIONS_LIMITED) {
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

	/**
     * Creates an operation with the given operands and included operations.
     *
     * @param operands           The list of operands for the operation.
     * @param includedOperations The set of operation types to include.
     * @return The created operation.
     * @throws CannotCreateOperationException If the operation cannot be created.
     */
	public static Operation createOperation(List<Integer> operands, Set<OperationType> includedOperations) throws CannotCreateOperationException {
        List<Class<? extends Operation>> validOperations = new ArrayList<>();

        // Add allowed operations to the list of valid operations
        for (OperationType type : includedOperations) {
            switch (type) {
                case ADDITION:
                    validOperations.add(OperationAddition.class);
                    break;
                case SUBTRACTION:
                    validOperations.add(OperationSubtraction.class);
                    break;
                case MULTIPLICATION:
                    validOperations.add(OperationMultiplication.class);
                    break;
                case DIVISION:
                    validOperations.add(OperationDivision.class);
                    break;
                case EQUALITY:
                    validOperations.add(OperationEquality.class);
                    break;
                case POWER:
                    validOperations.add(OperationPower.class);
                    break;
                default:
                    break;
            }
        }

        Random random = new Random();
        for (Class<? extends OperationLimitedOperands> operation : OPERATIONS_LIMITED) {
            if (!validOperations.contains(operation)) {
                continue;
            }
            Operation createdOperation;
            try {
                createdOperation = createOperation(operation, 0);
            } catch (CannotCreateOperationException e) {
                continue;
            }
            try {
                if (createdOperation.isValidCandidate(operands, operands.size(), 0)) {
                    createdOperation.setOperands(operands);
                    return createdOperation;
                }
            } catch (OperandsDoNotMatchException | NonIntegerResultException ignored) {
            }
        }

        Class<? extends Operation> selectedOperationClass = validOperations.get(random.nextInt(validOperations.size()));
        return createOperation(selectedOperationClass, operands);
    }

	/**
     * Creates an operation with the given operation class and result.
     *
     * @param operationClass The class of the operation.
     * @param result         The result of the operation.
     * @return The created operation.
     * @throws CannotCreateOperationException If the operation cannot be created.
     */
	public static Operation createOperation(Class<? extends Operation> operationClass, int result) throws CannotCreateOperationException {
		try {
			Constructor<? extends Operation> constructor = operationClass.getConstructor(int.class);
			return constructor.newInstance(result);
		} catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException ignored) {
			throw new CannotCreateOperationException();
		}
	}

	/**
     * Creates an operation with the given symbol and result.
     *
     * @param symbol The symbol representing the operation.
     * @param result The result of the operation.
     * @return The created operation.
     * @throws CannotCreateOperationException If the operation cannot be created.
     */
	public static Operation createOperation(String symbol, int result) throws CannotCreateOperationException {
		Operation operation;
		for (Class<? extends Operation> op : OPERATIONS) {
			operation = createOperation(op, result);
			if (operation.getSymbol().equals(symbol))
				return operation;
		}
		for (Class<? extends OperationLimitedOperands> op : OPERATIONS_LIMITED) {
			operation = createOperation(op, result);
			if (operation.getSymbol().equals(symbol))
				return operation;
		}
		throw new CannotCreateOperationException();
	}
}
