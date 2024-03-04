package presentation.views;

import exceptions.*;
import models.KenKen;
import presentation.controllers.KenKenPlayController;
import presentation.custom.JCustomButton;

import javax.swing.*;
import java.awt.*;

public class KenKenPlayView extends KenKenView {
	private final SetCellToPanel setCellToPanel;
	private final JButton checkButton = new JCustomButton("Check");

	private final KenKenPlayController controller = new KenKenPlayController(this);

	private CellView selected;

	public KenKenPlayView(KenKen kenKen) {
		super(kenKen);
		this.setCellToPanel = new SetCellToPanel(kenKen.getSize(), controller);
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
			} catch (EraseFixedValueException | ValueOutOfBoundsException ignored) {
			} catch (RewriteFixedPositionException e) {
				JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
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
		} catch (OperandsDoNotMatchException | NonIntegerResultException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	@Override
	protected void configureLayout() {
		super.configureLayout();

		for (int i = 0; i < kenKen.getSize(); i++) {
			for (int j = 0; j < kenKen.getSize(); j++) {
				if (!kenKen.isFixed(i, j)) {
					cellViews[i][j].addActionListener(controller);
					cellViews[i][j].setActionCommand(KenKenPlayController.SELECT_CELL_AC + i + "_" + j);
				}
			}
		}
		add(setCellToPanel, BorderLayout.NORTH);

		checkButton.addActionListener(controller);
		checkButton.setActionCommand(KenKenPlayController.CHECK_KENKEN_AC);
		add(checkButton, BorderLayout.SOUTH);
	}
}
