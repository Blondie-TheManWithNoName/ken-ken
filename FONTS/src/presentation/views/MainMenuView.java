package presentation.views;

import presentation.PresentationController;
import presentation.controllers.HomeController;
import presentation.controllers.MainMenuController;

public class MainMenuView extends MainView {
	private final PresentationController pController;
	private final MainMenuController controller;

	public MainMenuView(PresentationController controller) {
		super();
		this.pController = controller;
		this.controller = new MainMenuController(pController,this);
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
		makeBackButton(controller, MainMenuController.BACK_AC);

		c.gridx = 1;
		makeButtonFirst("NEW", controller, MainMenuController.NEW_KENKEN_AC);

		c.gridx = 2;
		c.gridheight = 2;
		makeSquare("<html><p style='margin-bottom: -7;'>GA</p><p style='margin-top: -7;'>ME</p></html>");

		c.gridx = 3;
		c.gridheight = 2;
		makeSquare("");

		c.gridy = 2;

		c.gridx = 0;
		c.gridheight = 1;
		makeSquare("");

		c.gridx = 1;
		c.gridheight = 1;
		makeButtonSecond("LOAD", controller, MainMenuController.LOAD_KENKEN_AC);
	}

	private void addThirdRow() {
		c.gridy = 3;

		c.gridx = 0;
		c.gridheight = 2;
		makeSquare("");

		c.gridx = 1;
		makeSquare("<html><p style='margin-bottom: -7;'>KEN</p><p style='margin-top: -7;'>KEN</p></html>");

		c.gridx = 2;
		createPanelWithButtons("PROPOSE", false, "GENERATE", "IMPORT", controller, MainMenuController.PROPOSE_KENKEN_AC, MainMenuController.GENERATE_KENKEN_AC, MainMenuController.IMPORT_KENKEN_AC);

		c.gridx = 3;
		c.gridheight = 1;
		makeSquare("");

		c.gridy = 4;
		makeSquare("");
	}

}
