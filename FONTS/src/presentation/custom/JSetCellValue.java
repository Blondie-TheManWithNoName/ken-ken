package presentation.custom;

import presentation.controllers.KenKenPlayController;

import javax.swing.*;
import java.awt.*;

public class JSetCellValue extends JPanel {
	private final int size;
	private final JCustomButton[] setCellToButtons;

	private final KenKenPlayController controller;

	public JSetCellValue(int size, KenKenPlayController controller) {
		this.size = size;
		this.setCellToButtons = new JCustomButton[size + 1];
		this.controller = controller;
		configureLayout();
		disableAll();
	}

	public void disableAll() {
		for (JCustomButton button : setCellToButtons)
			button.setEnabled(false);
	}

	public void enableAllBut(int value) {
		for (int i = 0; i <= size; i++)
			setCellToButtons[i].setEnabled(i != value);
	}

	private void configureLayout() {
		setPreferredSize(new Dimension(size * JKenKenCell.CELL_SIZE, JKenKenCell.CELL_SIZE));
		GridBagLayout gridBag = new GridBagLayout();
		setLayout(gridBag);
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1;
		for (int i = 0; i <= size; i++) {
			setCellToButtons[i] = new JCustomButton(i == 0 ? "∅" : String.valueOf(i));
			setCellToButtons[i].addActionListener(controller);
			setCellToButtons[i].setActionCommand(KenKenPlayController.SET_CELL_TO_AC + i);
			gridBag.setConstraints(setCellToButtons[i], c);
			add(setCellToButtons[i]);
		}
	}
}
