package presentation.custom;

import javax.swing.*;
import java.awt.*;

public class J3ButtonPanel extends JPanel {
	public J3ButtonPanel(String firstButton, String secondButton, String thirdButton) {
		GridLayout grid = new GridLayout(3, 1, 0, 15);
		setLayout(grid);

		add(new JMainButton(firstButton, new ButtonColorsSecond(), 50, 25));
		add(new JMainButton(secondButton, new ButtonColorsSecond(), 50, 25));
		add(new JMainButton(thirdButton, new ButtonColorsSecond(), 50, 25));
	}

}
