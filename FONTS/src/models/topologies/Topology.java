package models.topologies;

import java.util.ArrayList;

/**
 * Enum class that represents the different shapes that the blocks can have.
 * Each shape is represented by a matrix of 1s and 0s, where 1 represents a block and 0 represents an empty space.
 */
public class Topology {
	//ArrayList of shapes
	private final ArrayList<Shape> shapes;

	/**
	 * Constructor that receives a matrix of 1s and 0s that represents the shape of the block.
	 * @param shape Matrix of 1s and 0s that represents the shape of the block.
	 */
//	public Topology(int[][] shape) {
//		this.shape = shape;
//	}
	public Topology() {
		shapes = new ArrayList<>();
	}
	/**
	 * Constructor that receives a Shape object and extracts the matrix of 1s and 0s that represents the shape of the block.
	 * @param shape Shape object that represents the shape of the block.
	 */
	public Topology(Shape shape) {
		shapes = new ArrayList<>();
		this.shapes.add(shape);
	}

	public void addShape(Shape newShape) {
		this.shapes.add(newShape);
	}

	public ArrayList<Shape> getShapes() {
		return shapes;
	}

	public Shape getShape(int index) {
		return shapes.get(index);
	}


	public int getSize()
	{
		return shapes.size();
	}

	/**
	 * Getter for the shape of the block.
	 * @return Matrix of 1s and 0s that represents the shape of the block.
	 */
//	public int size(int index) {
//		int size = 0;
//		int[][] shape = shapes.get(index).getShape();
//		for (int[] row :shape)
//			for (int cell : row)
//				if (cell != 0)
//					size++;
//		return size;
//	}

	/**
	 * Method that rotates the shape of the block a certain number of quarters.
	 * @param quarters Number of quarters to rotate the shape of the block.
	 * @return Matrix of 1s and 0s that represents the shape of the block.
	 */
	public int[][] rotateQuarters(int index, int quarters) {
        int[][] newShape = shapes.get(index).getShape();
		for (int i = 0; i < quarters % 4; i++)
			newShape = rotate90deg(newShape);
		return newShape;
	}

	/**
	 * Method that rotates the shape of the block 90 degrees.
	 * @param current Matrix of 1s and 0s that represents the shape of the block.
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
