package presentation.custom;

import models.kenken.Group;

import javax.swing.*;
import java.awt.*;

public class JKenKenCell extends JButton {
	public static final int CELL_SIZE = 50;

	private final int row;
	private final int col;
	private final boolean[] hasBorders = new boolean[4];

	private final JLabel notationLabel = new JLabel();

	public JKenKenCell(int row, int col, int value) {
		this.row = row;
		this.col = col;
		setValue(value);
		setForeground(Color.GRAY);
		setHorizontalAlignment(SwingConstants.CENTER);
		setPreferredSize(new Dimension(CELL_SIZE, CELL_SIZE));
		setOpaque(true);
		notationLabel.setBorder(BorderFactory.createEmptyBorder(0, 2, 0, 0));
		notationLabel.setFont(new Font("Arial", Font.PLAIN, 10));
		setLayout(new BorderLayout());
		add(notationLabel, BorderLayout.NORTH);
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
		setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createMatteBorder(
						hasBorders[0] ? 1 : 0,
						hasBorders[1] ? 1 : 0,
						hasBorders[2] ? 1 : 0,
						hasBorders[3] ? 1 : 0,
						Color.BLACK
				),
				BorderFactory.createMatteBorder(
						!hasBorders[0] ? 1 : 0,
						!hasBorders[1] ? 1 : 0,
						0,
						0,
						Color.LIGHT_GRAY
				)
		));
	}

	public void addGroupLabel(String notation) {
		notationLabel.setText(notation);
	}

	public void select() {
		setBackground(Color.LIGHT_GRAY);
	}

	public void deselect() {
		setBackground(null);
	}

	public void addToGroup(Group group, Color color) {
		setBackground(color);
		addGroupLabel(group.getNotation());
	}

	public void removeFromGroup() {
		setBackground(null);
		notationLabel.setText("");
	}
}
