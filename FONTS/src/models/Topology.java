package models;

public class Topology {
	private int[][] shape;

	public Topology(int[][] shape) {
		this.shape = shape;
	}

	public int[][] getShape() {
		return shape;
	}

	public void rotateClockwise() {
		int[][] newShape = new int[shape[0].length][shape.length];
		for (int i = 0; i < shape.length; i++)
			for (int j = 0; j < shape[i].length; j++)
				newShape[j][shape.length - i - 1] = shape[i][j];
		shape = newShape;
	}

	public int getHeight() {
		return shape.length;
	}

	public int getWidth() {
		return shape[0].length;
	}
}
