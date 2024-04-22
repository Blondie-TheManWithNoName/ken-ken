package models.topologies;

/**
 * Enum class that represents the different shapes that the blocks can have.
 * Each shape is represented by a matrix of 1s and 0s, where 1 represents a block and 0 represents an empty space.
 */
public class Topology {
	private final int[][] shape;

	/**
	 * Constructor that receives a matrix of 1s and 0s that represents the shape of the block.
	 * @param shape Matrix of 1s and 0s that represents the shape of the block.
	 */
	public Topology(int[][] shape) {
		this.shape = shape;
	}

	/**
	 * Constructor that receives a Shape object and extracts the matrix of 1s and 0s that represents the shape of the block.
	 * @param shape Shape object that represents the shape of the block.
	 */
	public Topology(Shape shape) {
		this.shape = shape.getShape();
	}

	/**
	 * Getter for the shape of the block.
	 * @return Matrix of 1s and 0s that represents the shape of the block.
	 */
	public int size() {
		int size = 0;
		for (int[] row : shape)
			for (int cell : row)
				if (cell != 0)
					size++;
		return size;
	}

	/**
	 * Getter for the shape of the block.
	 * @return Matrix of 1s and 0s that represents the shape of the block.
	 */
	public int[][] rotateQuarters(int quarters) {
		int[][] newShape = shape;
		for (int i = 0; i < quarters % 4; i++)
			newShape = rotate90deg(newShape);
		return newShape;
	}

	/**
	 * Getter for the shape of the block.
	 * @return Matrix of 1s and 0s that represents the shape of the block.
	 */
	private int[][] rotate90deg(int[][] current) {
		int[][] newShape = new int[current[0].length][current.length];
		for (int i = 0; i < current.length; i++)
			for (int j = 0; j < current[i].length; j++)
				newShape[j][current.length - i - 1] = current[i][j];
		return newShape;
	}
}
