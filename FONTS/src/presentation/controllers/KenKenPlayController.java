package presentation.controllers;

import presentation.views.KenKenPlayView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class KenKenPlayController implements ActionListener {
	public final static String CHECK_KENKEN_AC = "CHECK_KENKEN";
	public final static String SELECT_CELL_AC = "SELECT_CELL_";
	public final static String SET_CELL_TO_AC = "SET_CELL_TO_";

	private final KenKenPlayView view;

	public KenKenPlayController(KenKenPlayView view) {
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().startsWith(SELECT_CELL_AC)) {
			String[] parts = e.getActionCommand().split("_");
			int row = Integer.parseInt(parts[2]);
			int col = Integer.parseInt(parts[3]);
			view.selectCell(row, col);
		} else if (e.getActionCommand().startsWith(SET_CELL_TO_AC)) {
			String[] parts = e.getActionCommand().split("_");
			int value = Integer.parseInt(parts[3]);
			view.setValue(value);
		} else if (e.getActionCommand().equals(CHECK_KENKEN_AC)) {
			view.check();
		}
	}
}
