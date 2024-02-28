package models.operations;

public class OperationGCD extends Operation {
	public OperationGCD(int target) {
		super("gcd", target);
	}

	@Override
	public int calculate(int[] operands) {
		return GCD(operands);
	}

	protected static int GCD(int a, int b) {
		if (b == 0)
			return a;
		return GCD(b, a % b);
	}

	private static int GCD(int[] numbers) {
		int gcd = numbers[0];
		for (int i = 1; i < numbers.length; i++)
			gcd = GCD(gcd, numbers[i]);
		return gcd;
	}
}
