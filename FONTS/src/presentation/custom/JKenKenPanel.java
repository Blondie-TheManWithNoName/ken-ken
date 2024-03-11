package presentation.custom;

import models.Group;
import models.KenKen;
import presentation.views.CellView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class JKenKenPanel extends JPanel {
	private final static int MARGIN = 5;

	private final KenKen kenKen;
	private final CellView[][] cellViews;

	public JKenKenPanel(KenKen kenKen) {
		super();
		this.kenKen = kenKen;

		this.cellViews = new CellView[kenKen.getSize()][kenKen.getSize()];
		configureLayout();
	}

	public CellView getCellView(int row, int col) {
		return cellViews[row][col];
	}

	public void addController(int row, int col, ActionListener controller, String actionCommand) {
		cellViews[row][col].addActionListener(controller);
		cellViews[row][col].setActionCommand(actionCommand);
	}

	public void setValue(int row, int col, int value) {
		cellViews[row][col].setValue(value);
	}

	private void configureLayout() {
		setLayout(new GridLayout(kenKen.getSize(), kenKen.getSize()));
		setBorder(BorderFactory.createEmptyBorder(MARGIN, MARGIN, MARGIN, MARGIN));
		for (int i = 0; i < kenKen.getSize(); i++) {
			for (int j = 0; j < kenKen.getSize(); j++) {
				cellViews[i][j] = new CellView(i, j, kenKen.getValue(i, j));
				if (kenKen.hasTopBorder(i, j))
					cellViews[i][j].hasTopBorder();
				if (kenKen.hasLeftBorder(i, j))
					cellViews[i][j].hasLeftBorder();
				if (kenKen.hasBottomBorder(i, j))
					cellViews[i][j].hasBottomBorder();
				if (kenKen.hasRightBorder(i, j))
					cellViews[i][j].hasRightBorder();
				cellViews[i][j].paintBorders();
				if (kenKen.isFixed(i, j))
					cellViews[i][j].setFixed();
				add(cellViews[i][j]);
			}
		}
		setGroupLabels();
	}

	private void setGroupLabels() {
		for (Group group : kenKen.getGroups())
			cellViews[group.getCell(0).getRow()][group.getCell(0).getCol()].addGroupLabel(group.getNotation());
	}
}
