package presentation.views;

import models.kenken.KenKenSolver;
import presentation.controllers.KenKenSolverController;
import presentation.custom.JCustomButton;

import javax.swing.*;
import java.awt.*;

/**
 * The KenKenSolverView class represents the view for solving KenKen puzzles.
 */
public class KenKenSolverView extends KenKenView {
	private final KenKenSolver solver;
	private final KenKenSolverController controller = new KenKenSolverController(this);

	/**
	 * Constructs a KenKenSolverView object.
	 *
	 * @param solver The KenKenSolver instance.
	 */
	public KenKenSolverView(KenKenSolver solver) {
		super(solver.getKenKen());
		this.solver = solver;
	}

	/**
	 * Attempts to solve the KenKen puzzle and displays a message if no solution is found.
	 */
	public void solve() {
		if (!solver.solve())
			JOptionPane.showMessageDialog(this, "No solution found");
		else {
			repaint();
		}
	}

	/**
	 * Repaints the KenKen puzzle view.
	 */
	@Override
	public void repaint() {
		super.repaint();
		for (int row = 0; row < kenKen.getSize(); row++)
			for (int col = 0; col < kenKen.getSize(); col++)
				kenKenPanel.setValue(row, col, kenKen.getValue(row, col), false);
	}

	/**
	 * Configures the layout of the KenKenSolverView.
	 */
	@Override
	protected void configureLayout() {
		super.configureLayout();
	}
}
