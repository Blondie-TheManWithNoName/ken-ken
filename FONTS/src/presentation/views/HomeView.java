package presentation.views;

import presentation.PresentationController;
import presentation.controllers.HomeController;

import javax.swing.*;
import java.awt.*;

/**
 * The HomeView class represents the main view of the application.
 */
public class HomeView extends MainView {
	private final PresentationController pController;
	private final HomeController controller;

	/**
	 * Constructs a HomeView object.
	 *
	 * @param controller The PresentationController instance.
	 */
	public HomeView(PresentationController controller) {
		super();
		this.pController = controller;
		this.controller = new HomeController(pController, this);
		start();
	}

	/**
	 * Initializes and displays the components of the home view.
	 */
	public void start() {
		addEmptyRow(0);
		addSecondRow();
		addThirdRow();
		addEmptyRow(5);
	}

	/**
	 * Adds an empty row at the specified y-coordinate.
	 *
	 * @param y The y-coordinate to add the empty row.
	 */
	private void addEmptyRow(int y) {
		c.gridy = y;
		for (int x = 0; x < 4; x++) {
			c.gridx = x;
			makeSquare("");
		}
	}

	/**
	 * Adds components for the second row of the home view.
	 */
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

	/**
	 * Adds components for the third row of the home view.
	 */
	private void addThirdRow() {
		c.gridy = 3;
		c.gridx = 0;
		c.gridheight = 2;
		makeSquare("");
		c.gridx = 1;
		makeNumber("6");
		c.gridx = 2;
		createPanelWithButtons("START", true, "RANKING", "EXIT", controller, HomeController.START_KENKEN_AC, HomeController.SEE_RANKING_AC, HomeController.EXIT_AC);
		c.gridx = 3;
		c.gridheight = 1;
		makeSquare("");
		c.gridy = 4;
		makeSquare("");
	}
}
