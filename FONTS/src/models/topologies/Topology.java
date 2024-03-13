package models.topologies;

public class Topology {
	private final int[][] shape;

	public Topology(int[][] shape) {
		this.shape = shape;
	}

	public Topology(Shape shape) {
		this.shape = shape.getShape();
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
