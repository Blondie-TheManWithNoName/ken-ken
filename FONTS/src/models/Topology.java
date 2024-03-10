package models;

public class Topology {
	public final static int[][] BLOCK_SHAPE = {{1, 1}, {1, 1}};
	public final static int[][] J_SHAPE = {{0, 1}, {0, 1}, {1, 1}};
	public final static int[][] L_SHAPE = {{1, 0}, {1, 0}, {1, 1}};
	public final static int[][] T_SHAPE = {{1, 1, 1}, {0, 1, 0}};
	public final static int[][] I_SHAPE = {{1}, {1}, {1}, {1}};
	public final static int[][] DASH_SHAPE = {{1, 1}};

	private final int[][] shape;

	public Topology(int[][] shape) {
		this.shape = shape;
	}

	public int[][] getShape() {
		return shape;
	}

	public int[][] rotateQuarters(int quarters) {
		int[][] newShape = shape;
		for (int i = 0; i < quarters % 4; i++)
			newShape = rotate90deg(newShape);
		return newShape;
	}

	private int[][] rotate90deg(int[][] current) {
		int[][] newShape = new int[current[0].length][current.length];
		for (int i = 0; i < current.length; i++)
			for (int j = 0; j < current[i].length; j++)
				newShape[j][current.length - i - 1] = current[i][j];
		return newShape;
	}
}
