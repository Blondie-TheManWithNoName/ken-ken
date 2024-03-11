package presentation.views;

import models.KenKenSolver;
import presentation.controllers.KenKenSolverController;
import presentation.custom.JCustomButton;

import javax.swing.*;
import java.awt.*;

public class KenKenSolverView extends KenKenView {
	private final KenKenSolver solver;

	private final JCustomButton solveButton = new JCustomButton("Solve");

	private final KenKenSolverController controller = new KenKenSolverController(this);

	public KenKenSolverView(KenKenSolver solver) {
		super(solver.getKenKen());
		this.solver = solver;
	}

	public void solve() {
		if (!solver.solve())
			JOptionPane.showMessageDialog(this, "No solution found");
		else {
			repaint();
			solveButton.setEnabled(false);
		}
	}

	@Override
	public void repaint() {
		super.repaint();
		for (int row = 0; row < kenKen.getSize(); row++)
			for (int col = 0; col < kenKen.getSize(); col++)
				kenKenPanel.setValue(row, col, kenKen.getValue(row, col));
	}

	@Override
	protected void configureLayout() {
		super.configureLayout();

		solveButton.addActionListener(controller);
		solveButton.setActionCommand(KenKenSolverController.SOLVE_KENKEN_AC);
		add(solveButton, BorderLayout.SOUTH);
	}
}
