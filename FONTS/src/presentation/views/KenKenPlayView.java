package presentation.views;

import exceptions.*;
import models.KenKen;
import presentation.controllers.KenKenPlayController;
import presentation.custom.JKenKenCell;
import presentation.custom.JCustomButton;
import presentation.custom.JSetCellValue;

import javax.swing.*;
import java.awt.*;

public class KenKenPlayView extends KenKenView {
	private final JSetCellValue setCellValue;
	private final JCustomButton checkButton = new JCustomButton("Check");

	private final KenKenPlayController controller = new KenKenPlayController(this);

	private JKenKenCell selected;

	public KenKenPlayView(KenKen kenKen) {
		super(kenKen);
		this.setCellValue = new JSetCellValue(kenKen.getSize(), controller);
	}

	public void selectCell(int row, int col) {
		if (selected != null)
			selected.deselect();
		selected = kenKenPanel.getCell(row, col);
		selected.select();
		setCellValue.enableAllBut(kenKen.getValue(row, col));
	}

	public void setValue(int value) {
		if (selected != null) {
			try {
				if (value == 0)
					kenKen.erasePosition(selected.getRow(), selected.getCol());
				else
					kenKen.setPosition(selected.getRow(), selected.getCol(), value);
				selected.setValue(value);
				setCellValue.enableAllBut(value);
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
			setCellValue.disableAll();
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

		for (int i = 0; i < kenKen.getSize(); i++)
			for (int j = 0; j < kenKen.getSize(); j++)
				if (!kenKen.isFixed(i, j))
					kenKenPanel.addController(i, j, controller, KenKenPlayController.SELECT_CELL_AC + i + "_" + j);
		add(setCellValue, BorderLayout.NORTH);

		checkButton.addActionListener(controller);
		checkButton.setActionCommand(KenKenPlayController.CHECK_KENKEN_AC);
		add(checkButton, BorderLayout.SOUTH);
	}
}
