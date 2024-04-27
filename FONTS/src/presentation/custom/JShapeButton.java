package presentation.custom;

import models.topologies.Shape;

import java.awt.*;

public class JShapeButton extends JCustomButton {
	private final static int CELL_SIZE = 20;
	private final int[][] shape;

	public JShapeButton(int[][] shape) {
		this.shape = shape;
		setPreferredSize(new Dimension(CELL_SIZE * shape[0].length, CELL_SIZE * shape.length));
	}

	public JShapeButton(Shape shape) {
		this(shape.getShape());
	}

	@Override
	protected void paintComponent(Graphics g) {
		for (int i = 0; i < shape.length; i++) {
			for (int j = 0; j < shape[i].length; j++) {
				if (shape[i][j] != 0) {
					g.setColor(Color.BLACK);
					g.fillRect(j * CELL_SIZE, i * CELL_SIZE, CELL_SIZE, CELL_SIZE);
				}
			}
		}
	}
}
