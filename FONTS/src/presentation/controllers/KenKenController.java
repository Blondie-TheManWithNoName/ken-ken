package presentation.controllers;

import presentation.views.KenKenView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class KenKenController implements ActionListener {
	public final static String CHECK_BUTTON_AC = "CHECK_KENKEN";
	public final static String SELECT_CELL_AC = "SELECT_CELL_";
	public final static String SET_CELL_TO_AC = "SET_CELL_TO_";

	private final KenKenView view;

	public KenKenController(KenKenView view) {
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().startsWith(SELECT_CELL_AC)) {
			String[] parts = e.getActionCommand().split("_");
			int row = Integer.parseInt(parts[2]);
			int col = Integer.parseInt(parts[3]);
			view.selectCell(row, col);
		}
		if (e.getActionCommand().startsWith(SET_CELL_TO_AC)) {
			String[] parts = e.getActionCommand().split("_");
			int value = Integer.parseInt(parts[3]);
			view.setValue(value);
		}
		if (e.getActionCommand().equals(CHECK_BUTTON_AC)) {
			view.check();
		}
	}
}
