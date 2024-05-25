package presentation.views;

import presentation.controllers.MainMenuController;

public class MainMenuView extends MainView {

	private final MainMenuController controller = new MainMenuController(this);

	public MainMenuView() {
		super();
	}

	public void start() {

		makeSquare("");
		makeSquare("");
		makeSquare("");
		makeSquare("");
		c.gridy = 1;
		makeBackButton(controller, MainMenuController.BACK_AC);
		makeButtonFirst("NEW", controller, MainMenuController.NEW_KENKEN_AC);
		c.gridheight = 2;
		makeSquare("<html><p style='margin-bottom: -7;'>GA</p><p style='margin-top: -7;'>ME</p></html>");
		makeSquare("");

		c.gridy = 2;
		c.gridx = 0;
		c.gridheight = 3;
		makeSquare("");;
		c.gridx = 1;
		c.gridheight = 1;		;
		makeButtonSecond("LOAD", controller, MainMenuController.LOAD_KENKEN_AC);

		c.gridy = 3;
		c.gridx = 1;
		c.gridheight = 2;
		makeSquare("<html><p style='margin-bottom: -7;'>KEN</p><p style='margin-top: -7;'>KEN</p></html>");
		c.gridx = 2;
		createPanelWithButtons("");
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
