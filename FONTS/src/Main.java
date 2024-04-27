import models.topologies.Shape;
import presentation.custom.JShapeButton;

import javax.swing.*;
import java.awt.*;

public class Main {
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setLayout(new FlowLayout());

		for (Shape shape : Shape.values())
			frame.add(new JShapeButton(shape));

		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setVisible(true);
	}
}
