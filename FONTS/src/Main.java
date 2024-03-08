import exceptions.CannotCreateOperationException;
import exceptions.CellHasNoGroupException;
import exceptions.GroupCellsNotContiguousException;
import models.KenKen;
import models.KenKenGenerator;
import models.KenKenSolver;
import models.Topology;
import presentation.views.KenKenSolverView;
import presentation.views.KenKenView;

import javax.swing.*;

public class Main {
	public static void main(String[] args) {
		KenKenGenerator generator = new KenKenGenerator(4, new Topology(Topology.T_SHAPE));
		KenKen kenKen;
		try {
			kenKen = generator.generate();
		} catch (CellHasNoGroupException | GroupCellsNotContiguousException | CannotCreateOperationException e) {
			throw new RuntimeException(e);
		}

		KenKenView view = new KenKenSolverView(new KenKenSolver(kenKen));
		SwingUtilities.invokeLater(view::start);
	}
}
