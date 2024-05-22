import models.ModelController;
import models.topologies.Shape;
import presentation.custom.JShapeButton;
import presentation.views.MainMenuView;


import javax.swing.*;
import javax.swing.SwingUtilities;
import java.awt.*;

public class Main {
	public static void main(String[] args) {
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

		ModelController controller= new ModelController();
		MainMenuView view = new MainMenuView();
		SwingUtilities.invokeLater(view::start);
	}


}
