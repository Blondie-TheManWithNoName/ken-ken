package presentation.views;

import javax.swing.*;
import java.awt.*;

public class CellView extends JButton {
	public static final int CELL_SIZE = 50;

	private final int row;
	private final int col;
	private final boolean[] hasBorders = new boolean[4];

	CellView(int row, int col, int value) {
		this.row = row;
		this.col = col;
		setValue(value);
		setForeground(Color.GRAY);
		setHorizontalAlignment(SwingConstants.CENTER);
		setPreferredSize(new Dimension(CELL_SIZE, CELL_SIZE));
		setOpaque(true);
	}

	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}

	public void setValue(int value) {
		if (value == 0)
			setText("");
		else
			setText(String.valueOf(value));
	}

	public void setFixed() {
		setForeground(Color.BLACK);
	}

	public void hasTopBorder() {
		hasBorders[0] = true;
	}

	public void hasLeftBorder() {
		hasBorders[1] = true;
	}

	public void hasBottomBorder() {
		hasBorders[2] = true;
	}

	public void hasRightBorder() {
		hasBorders[3] = true;
	}

	public void paintBorders() {
		setBorder(BorderFactory.createMatteBorder(
				hasBorders[0] ? 1 : 0,
				hasBorders[1] ? 1 : 0,
				hasBorders[2] ? 1 : 0,
				hasBorders[3] ? 1 : 0,
				Color.BLACK
		));
	}

	public void addGroupLabel(String notation) {
		JLabel label = new JLabel(notation);
		label.setBorder(BorderFactory.createEmptyBorder(0, 2, 0, 0));
		label.setFont(new Font("Arial", Font.PLAIN, 10));

		setLayout(new BorderLayout());
		add(label, BorderLayout.NORTH);
	}

	public void select() {
		setBackground(Color.LIGHT_GRAY);
	}

	public void deselect() {
		setBackground(null);
	}
}
