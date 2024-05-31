package presentation.custom;

import javax.swing.*;
import java.awt.*;

public class J3ButtonPanel extends JPanel {
	public J3ButtonPanel(String firstButton,  boolean first, String secondButton, String thirdButton) {
		GridLayout grid = new GridLayout(3, 1, 0, 15);
		setLayout(grid);
		if (first)
			add(new JMainButton(firstButton, new ButtonColorsFirst(), 50, 30));
		else
			add(new JMainButton(firstButton, new ButtonColorsSecond(), 50, 30));
		add(new JMainButton(secondButton, new ButtonColorsSecond(), 50, 30));
		add(new JMainButton(thirdButton, new ButtonColorsSecond(), 50, 30));
	}


}
