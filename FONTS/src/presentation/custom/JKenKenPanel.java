package presentation.custom;

import models.kenken.Group;
import models.kenken.KenKen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class JKenKenPanel extends JPanel {
	private final static int MARGIN = 4;

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

	public void setFixedValue(int row, int col, int value) {
		cells[row][col].setValue(value);
		cells[row][col].setFixed();
	}

	public void clearValue(int row, int col) {
		cells[row][col].setValue(0);
	}

	private void configureLayout() {
		setLayout(new GridLayout(kenKen.getSize(), kenKen.getSize()));
		setBorder(BorderFactory.createMatteBorder(MARGIN, MARGIN, MARGIN, MARGIN, Color.decode("#375281")));
		for (int i = 0; i < kenKen.getSize(); i++) {
			for (int j = 0; j < kenKen.getSize() ; j++) {
				if (cells[i][j] == null) cells[i][j] = new JKenKenCell(i, j, kenKen.getValue(i, j));
				if (j + 1 < kenKen.getSize() && cells[i ][j + 1] == null) cells[i][j + 1] = new JKenKenCell(i, j + 1, kenKen.getValue(i, j + 1));
				if (i + 1 < kenKen.getSize() && cells[i + 1][j] == null) cells[i + 1][j] = new JKenKenCell(i + 1, j, kenKen.getValue(i + 1, j));

				if (j + 1 < kenKen.getSize() && !kenKen.sameGroup(i, j, i, j + 1))
				{
					cells[i][j].setRightBorder();
					cells[i][j + 1].setLeftBorder();
				}
				if (i + 1< kenKen.getSize() && !kenKen.sameGroup(i, j, i + 1, j))
				{
					cells[i][j].setBottomBorder();
					cells[i + 1][j].setTopBorder();
				}


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
