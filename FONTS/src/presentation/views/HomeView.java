package presentation.views;

import javax.swing.*;
import javax.swing.border.Border;

import presentation.PresentationController;
import presentation.controllers.*;


import java.awt.*;

public class HomeView extends MainView {
	private final PresentationController pController;
	private final HomeController controller;


	public HomeView(PresentationController controller) {
		super();
		this.pController = controller;
		this.controller = new HomeController(pController,this);
		start();
	}

	public void start() {
		addEmptyRow(0);
		addSecondRow();
		addThirdRow();
		addEmptyRow(5);

		//this.revalidate();

	}


	private void addEmptyRow(int y) {
		c.gridy = y;
		for (int x = 0; x < 4; x++) {
			c.gridx = x;
			makeSquare("");
		}
	}

	private void addSecondRow() {
		c.gridy = 1;

		c.gridx = 0;
		makeSquare("");

		c.gridx = 1;
		c.gridheight = 2;
		makeTitle();

		c.gridx = 2;
		makeNumber("9");

		c.gridx = 3;
		c.gridheight = 2;
		makeSquare("");

		c.gridy = 2;

		c.gridx = 0;
		c.gridheight = 1;
		makeSquare("");
	}

	private void addThirdRow() {
		c.gridy = 3;

		c.gridx = 0;
		c.gridheight = 2;
		makeSquare("");

		c.gridx = 1;
		makeNumber("6");

		c.gridx = 2;
		createPanelWithButtons("START", "RANKING", "EXIT", controller, HomeController.START_KENKEN_AC, HomeController.SEE_RANKING_AC, HomeController.EXIT_AC);
		
		c.gridx = 3;
		c.gridheight = 1;
		makeSquare("");

		c.gridy = 4;
		makeSquare("");
	}


}
