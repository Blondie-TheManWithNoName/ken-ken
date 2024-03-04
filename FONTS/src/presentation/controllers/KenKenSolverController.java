package presentation.controllers;

import presentation.views.KenKenSolverView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class KenKenSolverController implements ActionListener {
	public final static String SOLVE_KENKEN_AC = "SOLVE_KENKEN";

	private final KenKenSolverView view;

	public KenKenSolverController(KenKenSolverView view) {
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals(SOLVE_KENKEN_AC)) {
			view.solve();
		}
	}
}
