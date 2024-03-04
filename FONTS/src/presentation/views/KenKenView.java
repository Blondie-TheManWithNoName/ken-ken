package presentation.views;

import models.Group;
import models.KenKen;

import javax.swing.*;
import java.awt.*;

public class KenKenView extends JFrame {
	protected final static int MARGIN = 5;

	protected final KenKen kenKen;
	protected final JPanel kenKenPanel = new JPanel();
	protected final CellView[][] cellViews;

	public KenKenView(KenKen kenKen) {
		this.kenKen = kenKen;
		this.cellViews = new CellView[kenKen.getSize()][kenKen.getSize()];
	}

	public void start() {
		configureWindow();
		configureLayout();
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	private void configureWindow() {
		setTitle("KenKen");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
	}

	protected void configureLayout() {
		setLayout(new BorderLayout());

		kenKenPanel.setLayout(new GridLayout(kenKen.getSize(), kenKen.getSize()));
		kenKenPanel.setBorder(BorderFactory.createEmptyBorder(MARGIN, MARGIN, MARGIN, MARGIN));
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
				kenKenPanel.add(cellViews[i][j]);
			}
		}
		setGroupLabels();
		add(kenKenPanel, BorderLayout.CENTER);
	}

	private void setGroupLabels() {
		for (Group group : kenKen.getGroups())
			cellViews[group.getCell(0).getRow()][group.getCell(0).getCol()].addGroupLabel(group.getNotation());
	}
}
