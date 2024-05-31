package presentation.custom;

import javax.swing.*;
import java.awt.*;

public class J2ButtonPanel extends JPanel {
	public J2ButtonPanel(String firstButton, String secondButton) {
		GridLayout grid = new GridLayout(3, 1, 0, 15);
		setLayout(grid);

		add(new JMainButton(firstButton, new ButtonColorsSecond(), 50, 30));
		add(new JMainButton(secondButton, new ButtonColorsSecond(), 50, 30));
	}

}