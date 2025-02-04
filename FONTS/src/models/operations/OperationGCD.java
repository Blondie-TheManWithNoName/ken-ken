package models.operations;

/**
 * OperationGCD class is a class that extends Operation class and implements the GCD operation
 */
public class OperationGCD extends Operation {
	/**
	 * constructor of the OperationGCD class
	 * @param target the target number that the solver should reach
	 */
	public OperationGCD(int target) {
		super("gcd", target);
	}

	/**
	 * Check if the operands that the solver write are valid to solve the GCD
	 */
	@Override
	public boolean isValidCandidate(int[] operands, int groupSize, int max) {
		if (operands.length == groupSize)
			return calculate(operands) == target;
		return calculate(operands) >= target;
	}


	/**
	 * Calculate the result of the GCD the solver write
	 */
	@Override
	public int calculate(int[] operands) {
		return GCD(operands);
	}

	/**
	 * Return the GCD of two numbers
	 * @param a first number
	 * @param b second number
	 * @return the GCD of a and b
	 */
	protected static int GCD(int a, int b) {
		if (b == 0)
			return a;
		return GCD(b, a % b);
	}

	/**
	 * Return the GCD of an array of numbers
	 * @param numbers array of numbers
	 * @return the gcd of the array
	 */
	private static int GCD(int[] numbers) {
		int gcd = numbers[0];
		for (int i = 1; i < numbers.length; i++)
			gcd = GCD(gcd, numbers[i]);
		return gcd;
	}
}
