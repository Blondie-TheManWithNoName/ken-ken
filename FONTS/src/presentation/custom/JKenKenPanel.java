package presentation.custom;

import models.Group;
import models.KenKen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class JKenKenPanel extends JPanel {
	private final static int MARGIN = 5;

	private final KenKen kenKen;
	private final JKenKenCell[][] cells;

	public JKenKenPanel(KenKen kenKen) {
		this.kenKen = kenKen;
		this.cells = new JKenKenCell[kenKen.getSize()][kenKen.getSize()];
		configureLayout();
	}

	public JKenKenCell getCell(int row, int col) {
		return cells[row][col];
	}

	public void addController(int row, int col, ActionListener controller, String actionCommand) {
		cells[row][col].addActionListener(controller);
		cells[row][col].setActionCommand(actionCommand);
	}

	public void setValue(int row, int col, int value) {
		cells[row][col].setValue(value);
	}

	private void configureLayout() {
		setLayout(new GridLayout(kenKen.getSize(), kenKen.getSize()));
		setBorder(BorderFactory.createEmptyBorder(MARGIN, MARGIN, MARGIN, MARGIN));
		for (int i = 0; i < kenKen.getSize(); i++) {
			for (int j = 0; j < kenKen.getSize(); j++) {
				cells[i][j] = new JKenKenCell(i, j, kenKen.getValue(i, j));
				if (kenKen.hasTopBorder(i, j))
					cells[i][j].hasTopBorder();
				if (kenKen.hasLeftBorder(i, j))
					cells[i][j].hasLeftBorder();
				if (kenKen.hasBottomBorder(i, j))
					cells[i][j].hasBottomBorder();
				if (kenKen.hasRightBorder(i, j))
					cells[i][j].hasRightBorder();
				cells[i][j].paintBorders();
				if (kenKen.isFixed(i, j))
					cells[i][j].setFixed();
				add(cells[i][j]);
			}
		}
		setGroupLabels();
	}

	private void setGroupLabels() {
		for (Group group : kenKen.getGroups())
			cells[group.getCell(0).getRow()][group.getCell(0).getCol()].addGroupLabel(group.getNotation());
	}
}
