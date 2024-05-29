import models.ModelController;
import models.kenken.KenKen;
import models.topologies.Shape;
import presentation.custom.JShapeButton;
import presentation.*;


import javax.swing.*;
import javax.swing.SwingUtilities;

import presentation.PresentationController;

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

//		PresentationController controller= new PresentationController();
//		controller.initializePresentation();
//		SwingUtilities.invokeLater(view::start);

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				PresentationController ctrlPresentacion = new PresentationController();
				ctrlPresentacion.showHomeView();
			}
		  });
	}


}
