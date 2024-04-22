import exceptions.CannotLoadKenKenException;
import exceptions.CannotLoadScoresException;
import exceptions.InvalidUsernameException;
import exceptions.OperandsDoNotMatchException;
import models.ModelController;
import models.Score;
import models.kenken.KenKen;
import models.kenken.KenKenSolver;
import models.operations.*;
import models.topologies.Shape;
import models.topologies.Topology;
import presentation.views.KenKenSolverView;
import presentation.views.KenKenView;

import javax.swing.*;
import java.io.IOException;
import java.util.List;

public class Main {
	public static void main(String[] args) {
		ModelController controller = new ModelController();
		KenKen kenKen;

//		try {
//			controller.saveScore("uriiisegura");
//		} catch (InvalidUsernameException | IOException e) {
//			System.out.println(e.getMessage());
//			return;
//		}

//		try {
//			List<Score> scores = controller.checkRanking();
//			for (int i = 0; i < scores.size(); i++)
//				System.out.println((i + 1) + ". " + scores.get(i).getUser() + " - " + scores.get(i).getScore());
//		} catch (CannotLoadScoresException e) {
//			System.out.println(e.getMessage());
//			return;
//		}

//		try {
//			if (!controller.generateKenKen(4, 2, new Topology(Shape.T), List.of(
//					OperationAddition.class
//			))) {
//				System.out.println("Failed to generate KenKen");
//				return;
//			}
//		} catch (OperandsDoNotMatchException e) {
//			System.out.println(e.getMessage());
//			return;
//		}
//
//		controller.generatorPlay();
//		if (!controller.saveGame())
//			System.out.println("Failed to save game");

//		try {
//			controller.loadSavedGame("data/example_path.kenken_game");
//		} catch (CannotLoadKenKenException e) {
//			System.out.println(e.getMessage());
//			return;
//		}

		try {
			kenKen = controller.loadKenKen("data/test9.kenken");
		} catch (CannotLoadKenKenException e) {
			System.out.println(e.getMessage());
			return;
		}

		KenKenView view = new KenKenSolverView(new KenKenSolver(kenKen));
		SwingUtilities.invokeLater(view::start);
	}
}
