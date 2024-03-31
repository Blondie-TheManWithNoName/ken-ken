import exceptions.CannotLoadKenKenException;
import models.ModelController;
import models.kenken.KenKen;
import models.kenken.KenKenSolver;
import presentation.views.KenKenSolverView;

import javax.swing.*;

public class Main {
	public static void main(String[] args) {
		ModelController controller = new ModelController();
		KenKen kenKen = null;
		try {
			kenKen = controller.loadKenKen("data/test.kenken");
		} catch (CannotLoadKenKenException e) {
			System.out.println(e.getMessage());
			System.exit(1);
		}
		KenKenSolver solver = new KenKenSolver(kenKen);
		KenKenSolverView view = new KenKenSolverView(solver);
		SwingUtilities.invokeLater(view::start);
	}
}
