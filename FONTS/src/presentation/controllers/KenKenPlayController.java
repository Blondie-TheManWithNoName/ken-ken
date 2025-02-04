package presentation.controllers;

import exceptions.RewriteFixedPositionException;
import exceptions.ValueOutOfBoundsException;
import presentation.PresentationController;
import presentation.views.KenKenPlayView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KenKenPlayController implements ActionListener, KeyListener {
	public final static String SELECT_CELL_AC = "SELECT_CELL_";
	public final static String SET_CELL_TO_AC = "SET_CELL_TO_";
	public final static String SOLVE_KENKEN_AC = "SOLVE_KENKEN_AC";
	public final static String HINT_KENKEN_AC = "HINT_KENKEN_AC";
	public final static String PAUSE_AC = "PAUSE_AC";

	private final KenKenPlayView view;
	private final PresentationController controller;


	public KenKenPlayController(PresentationController controller, KenKenPlayView view) {
		this.view = view;
		this.controller = controller;
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
		}
		else if (e.getActionCommand().startsWith(SOLVE_KENKEN_AC))
		{
            try {
                view.solve();
            } catch (RewriteFixedPositionException | ValueOutOfBoundsException ex) {
                throw new RuntimeException(ex);
            }
        }
		else if (e.getActionCommand().startsWith(HINT_KENKEN_AC))
		{
			controller.add1hint();
            try {
                view.hint();
            } catch (RewriteFixedPositionException | ValueOutOfBoundsException ex) {
                throw new RuntimeException(ex);
            }
        }
		else if (e.getActionCommand().startsWith(PAUSE_AC))
		{
			view.stopTime();
			controller.setTime(view.getMinutes(), view.getSeconds());
			view.setVisible(false);
			controller.showPauseView();
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		char keyChar = e.getKeyChar();
		if (Character.isDigit(keyChar)) {
			int value = Character.getNumericValue(keyChar);
			view.setValue(value);
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {

	}

	@Override
	public void keyReleased(KeyEvent e) {

	}
}
