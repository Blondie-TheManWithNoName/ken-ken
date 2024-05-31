import exceptions.CannotLoadKenKenException;
import models.ModelController;
import models.kenken.KenKenSolver;
import models.topologies.Shape;
import presentation.custom.JShapeButton;
import presentation.views.*;


import javax.swing.*;
import javax.swing.SwingUtilities;
import java.awt.*;

public class Main {
	public static void main(String[] args) throws CannotLoadKenKenException {
//		JFrame frame = new JFrame();
//		frame.setLayout(new FlowLayout());
//
//		for (Shape shape : Shape.values())
//			frame.add(new JShapeButton(shape));
//
//		frame.pack();
//		frame.setLocationRelativeTo(null);
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//		frame.setVisible(true);
//		MainMenuView mainmenu = new MainMenuView();
		ModelController controller= new ModelController();
//		KenKenView view = new KenKenView(controller.loadKenKen("data/test2.kenken"));
		KenKenPlayView view = new KenKenPlayView(controller.loadKenKen("data/test9.kenken"));

//		KenKenSolverView view = new KenKenSolverView(new KenKenSolver(controller.loadKenKen("data/test2.kenken")));
		SwingUtilities.invokeLater(view::start);
	}


}
