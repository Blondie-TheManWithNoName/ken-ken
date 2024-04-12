package models.operations;

public class OperationLCM extends Operation {
	public OperationLCM(int target) {
		super("lcm", target);
	}


	/**
	 * check if the operands that the solver write are valid to solve the LCM
	 */
	@Override
	public boolean isValidCandidate(int[] operands, int groupSize, int max) {
		if (operands.length == groupSize)
			return calculate(operands) == target;
		return calculate(operands) <= target;
	}


	/**
	 * calculate the LCM of the operands the solver write
	 */
	@Override
	public int calculate(int[] operands) {
		return LCM(operands);
	}

	/**
	 * return the LCM of two operands
	 * @param a first number
	 * @param b second number
	 * @return the LCM of a and b
	 */
	protected static int LCM(int a, int b) {
		return a * b / OperationGCD.GCD(a, b);
	}

	/**
	 * return the LCM of an array of numbers
	 * @param numbers array of numbers
	 * @return the lcm of the array
	 *
	 */
	private static int LCM(int[] numbers) {
		int lcm = numbers[0];
		for (int i = 1; i < numbers.length; i++)
			lcm = LCM(lcm, numbers[i]);
		return lcm;
	}
}
