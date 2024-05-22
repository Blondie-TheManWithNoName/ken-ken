package presentation.custom;

import models.kenken.Group;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class JKenKenCell extends JButton {
	public static final int CELL_SIZE = 75;

	private final int row;
	private final int col;
	private final boolean[] borders = new boolean[4];

	private final JLabel notationLabel = new JLabel();

	public JKenKenCell(int row, int col, int value) {
		this.row = row;
		this.col = col;
		setValue(value);
		setForeground(Color.decode("#FAFAFA"));
		setBackground(Color.WHITE);
		setHorizontalAlignment(SwingConstants.CENTER);
		setPreferredSize(new Dimension(CELL_SIZE, CELL_SIZE));
		setOpaque(true);
		notationLabel.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
		notationLabel.setFont(new Font("Arial", Font.PLAIN, 20));
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

	public void setTopBorder() {
		borders[0] = true;
	}

	public void setLeftBorder() {
		borders[1] = true;
	}

	public void setBottomBorder() {
		borders[2] = true;
	}

	public void setRightBorder() {
		borders[3] = true;
	}

	public void paintBorders() {
		setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createMatteBorder(
						borders[0] ? 2 : 0,
						borders[1] ? 2 : 0,
						borders[2] ? 2 : 0,
						borders[3] ? 2 : 0,
						Color.decode("#375281")
				),
				BorderFactory.createMatteBorder(
						borders[0] ? 0 : 2,
						borders[1] ? 0 : 2,
						borders[2] ? 0 : 2,
						borders[3] ? 0 : 2,
						Color.decode("#D3D8E2")
				)
		));
	}

	public void addGroupLabel(String notation) {
		notationLabel.setText(notation);
	}

	public void select() {
		setBackground(Color.decode("#FAFAFA"));
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
