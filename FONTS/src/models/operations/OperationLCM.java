package models.operations;

public class OperationLCM extends Operation {
	public OperationLCM(int target) {
		super("lcm", target);
	}

	@Override
	public int calculate(int[] operands) {
		return LCM(operands);
	}

	protected static int LCM(int a, int b) {
		return a * b / OperationGCD.GCD(a, b);
	}

	private static int LCM(int[] numbers) {
		int lcm = numbers[0];
		for (int i = 1; i < numbers.length; i++)
			lcm = LCM(lcm, numbers[i]);
		return lcm;
	}
}
