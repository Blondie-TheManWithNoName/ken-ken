package models;

public enum Shape {
	BLOCK(new int[][]{{1, 1}, {1, 1}}),
	J(new int[][]{{0, 1}, {0, 1}, {1, 1}}),
	L(new int[][]{{1, 0}, {1, 0}, {1, 1}}),
	T(new int[][]{{1, 1, 1}, {0, 1, 0}}),
	I(new int[][]{{1}, {1}, {1}, {1}}),
	DASH(new int[][]{{1, 1}});

	private final int[][] shape;

	Shape(int[][] shape) {
		this.shape = shape;
	}

	public int[][] getShape() {
		return shape;
	}
}
