package presentation.views;

import models.kenken.KenKen;
import presentation.custom.JKenKenPanel;
import presentation.controllers.*;
import presentation.custom.*;

import javax.swing.*;
import java.awt.*;


public class Generate3View extends MainView {

	private final Generate3Controller controller = new Generate3Controller(this);
	private final JKenKenPanel kenKenPanel;
	private final KenKen kenKen;

	public Generate3View(KenKen kenken) {
		this.kenKen = kenken;
       	this.kenKenPanel = new JKenKenPanel(kenken);
	}

	public void start() {
		

		makeSquare("");
		c.gridwidth = 2;
		makeSquare("CHECK");
		c.gridwidth = 1;
		makeSquare("");
		c.gridy = 1;

		makeBackButton(controller, Generate3Controller.BACK_AC);
		c.gridy = 1;
		c.gridx = 1;
		c.gridwidth = 2;

		c.gridheight = 2;

		Generate3(kenKenPanel);
		
		c.gridwidth = 1;
		c.gridheight = 1;

		c.gridx = 3;
		c.gridy = 3;

		makeSquare("");
		makeSquare("");
		makeSquare("");
		makeSquare("");
		makeSquare("");

		this.revalidate();

	}
}
