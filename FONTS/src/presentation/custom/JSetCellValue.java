package presentation.custom;

import presentation.controllers.KenKenPlayController;

import javax.swing.*;
import java.awt.*;

public class JSetCellValue extends JPanel {
	private final int size;
	private final JMainButton[] setCellToButtons;

	private final KenKenPlayController controller;

	public JSetCellValue(int size, KenKenPlayController controller) {
		this.size = size;
		this.setCellToButtons = new JMainButton[size + 1];
		this.controller = controller;
		setBackground(Color.decode("#FAFAFA"));
		configureLayout();
		disableAll();
	}

	public void disableAll() {
		for (JMainButton button : setCellToButtons)
			button.setSelected(false);
	}

	public void enableAllBut(int value) {
		for (int i = 0; i <= size; i++)
		{
			setCellToButtons[i].setSelected(i == value);
			setCellToButtons[i].init(new ButtonColorsSecond());
		}
	}

	private void configureLayout() {
		setPreferredSize(new Dimension(size * JKenKenCell.CELL_SIZE, JKenKenCell.CELL_SIZE));
		for (int i = 0; i <= size; i++) {
			setCellToButtons[i] = new JMainButton(i == 0 ? "⊘" : String.valueOf(i), new ButtonColorsSecond(), 50, 50);
			setCellToButtons[i].addActionListener(controller);

			setCellToButtons[i].setActionCommand(KenKenPlayController.SET_CELL_TO_AC + i);
			add(setCellToButtons[i]);
		}
	}
}
