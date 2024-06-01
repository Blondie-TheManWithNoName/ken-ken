package models.topologies;

/**
 * Enum class that represents the different shapes that the blocks can have.
 * Each shape is represented by a matrix of 1s and 0s, where 1 represents a block and 0 represents an empty space.
 */
public enum Shape {
	BLOCK(new int[][]{{1, 1}, {1, 1}}),
	J(new int[][]{{0, 1}, {0, 1}, {1, 1}}),
	L(new int[][]{{1, 0}, {1, 0}, {1, 1}}),
	T(new int[][]{{1, 1, 1}, {0, 1, 0}}),
	I(new int[][]{{1}, {1}, {1}, {1}}),
	DASH(new int[][]{{1, 1}}),
	CORNER(new int[][]{{1, 1}, {0, 1}}),
	ZIGZAG(new int[][]{{1, 0}, {1, 1}, {0, 1}}),
	POINT(new int[][]{{1}});

	private final int[][] shape;

	/**
	 * Constructor of the enum class.
	 * @param shape The shape of the block.
	 */
	Shape(int[][] shape) {
		this.shape = shape;
	}

	/**
	 * Getter for the shape of the block.
	 * @return The shape of the block.
	 */
	public int[][] getShape() {
		return shape;
	}

	public int getSize(){
		int size = 0;
		for (int[] row :shape)
			for (int cell : row)
				if (cell != 0)
					size++;
		return size;
	}
}
