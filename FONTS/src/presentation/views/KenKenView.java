package presentation.views;

import exceptions.CellHasNoGroupException;
import exceptions.EraseFixedValueException;
import exceptions.OperandsDoNotMatchException;
import exceptions.ValueOutOfBoundsException;
import models.Group;
import models.KenKen;
import presentation.controllers.KenKenController;
import presentation.custom.CustomJButton;

import javax.swing.*;
import java.awt.*;

public class KenKenView extends JFrame {
	private final static int MARGIN = 5;

	private final KenKen kenKen;
	private final JPanel kenKenPanel = new JPanel();
	private final CellView[][] cellViews;
	private final SetCellToPanel setCellToPanel;
	private final JButton checkButton = new CustomJButton("Check");

	private final KenKenController controller = new KenKenController(this);

	private CellView selected;

	public KenKenView(KenKen kenKen) throws CellHasNoGroupException {
		this.kenKen = kenKen;
		this.cellViews = new CellView[kenKen.getSize()][kenKen.getSize()];
		this.setCellToPanel = new SetCellToPanel(kenKen.getSize(), controller);
		configureWindow();
		configureLayout();
	}

	public void start() {
		setVisible(true);
	}

	public void selectCell(int row, int col) {
		if (selected != null)
			selected.deselect();
		selected = cellViews[row][col];
		selected.select();
		setCellToPanel.enableAllBut(kenKen.getValue(row, col));
	}

	public void setValue(int value) {
		if (selected != null) {
			try {
				if (value == 0)
					kenKen.erasePosition(selected.getRow(), selected.getCol());
				else
					kenKen.setPosition(selected.getRow(), selected.getCol(), value);
				selected.setValue(value);
				setCellToPanel.enableAllBut(value);
			} catch (EraseFixedValueException | ValueOutOfBoundsException ignored) {}
		}
	}

	public void check() {
		if (selected != null) {
			selected.deselect();
			selected = null;
			setCellToPanel.disableAll();
		}
		if (!kenKen.isFull()) {
			JOptionPane.showMessageDialog(this, "You must fill all the cells before checking the KenKen!", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		try {
			if (kenKen.check())
				JOptionPane.showMessageDialog(this, "Congratulations, you solved the KenKen!", "Success", JOptionPane.INFORMATION_MESSAGE);
			else
				JOptionPane.showMessageDialog(this, "Oops... continue trying!", "Failure", JOptionPane.ERROR_MESSAGE);
		} catch (OperandsDoNotMatchException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void configureWindow() {
		setTitle("KenKen");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
	}

	private void configureLayout() throws CellHasNoGroupException {
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
				if (kenKen.isFixed(i, j)) {
					cellViews[i][j].setFixed();
				} else {
					cellViews[i][j].addActionListener(controller);
					cellViews[i][j].setActionCommand(KenKenController.SELECT_CELL_AC + i + "_" + j);
				}
				kenKenPanel.add(cellViews[i][j]);
			}
		}
		setGroupLabels();

		checkButton.addActionListener(controller);
		checkButton.setActionCommand(KenKenController.CHECK_BUTTON_AC);

		add(setCellToPanel, BorderLayout.NORTH);
		add(kenKenPanel, BorderLayout.CENTER);
		add(checkButton, BorderLayout.SOUTH);

		pack();
		setLocationRelativeTo(null);
	}

	private void setGroupLabels() {
		for (Group group : kenKen.getGroups())
			cellViews[group.getCell(0).getRow()][group.getCell(0).getCol()].addGroupLabel(group.getNotation());
	}
}
