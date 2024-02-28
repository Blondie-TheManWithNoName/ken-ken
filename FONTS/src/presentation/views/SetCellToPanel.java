package presentation.views;

import presentation.controllers.KenKenController;
import presentation.custom.CustomJButton;

import javax.swing.*;
import java.awt.*;

public class SetCellToPanel extends JPanel {
	private final int size;
	private final JButton[] setCellToButtons;
	private final KenKenController controller;

	public SetCellToPanel(int size, KenKenController controller) {
		this.size = size;
		this.setCellToButtons = new JButton[size + 1];
		this.controller = controller;
		configureLayout();
		disableAll();
	}

	public void disableAll() {
		for (JButton button : setCellToButtons)
			button.setEnabled(false);
	}

	public void enableAllBut(int value) {
		for (int i = 0; i <= size; i++)
			setCellToButtons[i].setEnabled(i != value);
	}

	private void configureLayout() {
		setPreferredSize(new Dimension(size * CellView.CELL_SIZE, CellView.CELL_SIZE));
		GridBagLayout gridBag = new GridBagLayout();
		setLayout(gridBag);
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1;
		for (int i = 0; i <= size; i++) {
			setCellToButtons[i] = new CustomJButton(i == 0 ? "∅" : String.valueOf(i));
			setCellToButtons[i].addActionListener(controller);
			setCellToButtons[i].setActionCommand(KenKenController.SET_CELL_TO_AC + i);
			gridBag.setConstraints(setCellToButtons[i], c);
			add(setCellToButtons[i]);
		}
	}
}
