import exceptions.OperandsDoNotMatchException;
import models.ModelController;
import models.kenken.KenKenSolver;
import models.operations.*;
import models.topologies.Shape;
import models.topologies.Topology;
import presentation.views.KenKenSolverView;
import presentation.views.KenKenView;

import javax.swing.*;
import java.util.List;

public class Main {
	public static void main(String[] args) {
		ModelController controller = new ModelController();
		try {
			if (!controller.generateKenKen(4, 2, new Topology(Shape.T), List.of(
					OperationAddition.class
			))) {
				System.out.println("Failed to generate KenKen");
				return;
			}
		} catch (OperandsDoNotMatchException e) {
			System.out.println(e.getMessage());
			return;
		}

		controller.generatorPlay();
		KenKenView view = new KenKenSolverView(new KenKenSolver(controller.getActiveKenKen()));
		SwingUtilities.invokeLater(view::start);
	}
}
