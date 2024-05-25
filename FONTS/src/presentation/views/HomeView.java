package presentation.views;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class HomeView extends MainView {
	public HomeView() {
		super();
	}

	public void start() {

		makeSquare("");
		makeSquare("");
		makeSquare("");
		makeSquare("");

		c.gridy = 1;
		c.gridheight = 2;
		makeSquare("");
		makeTitle();
		makeNumber("9");
		c.gridheight = 1;
		makeSquare("");

		c.gridy = 2;
		c.gridx = 3;
		c.gridheight = 1;
		makeSquare("");


		c.gridy = 3;
		c.gridx = 0;
		c.gridheight = 2;
		makeSquare("");
		c.gridx = 1;
		makeNumber("6");
		c.gridx = 2;
		createPanelWithButtons("START", "RANKING", "EXIT");
		c.gridx = 3;
		c.gridheight = 1;
		makeSquare("");


		c.gridy = 4;
		c.gridx = 3;
		c.gridheight = 1;
		makeSquare("");

		c.gridheight = 1;
		c.gridy = 5;
		c.gridx = 0;
		makeSquare("");
		c.gridx = 1;
		makeSquare("");
		c.gridx = 2;
		makeSquare("");
		c.gridx = 3;
		makeSquare("");

		this.revalidate();

	}


}
