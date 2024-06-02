package presentation.views;


import presentation.PresentationController;
import presentation.controllers.KenKenPlayController;
import presentation.controllers.PauseController;

public class PauseView extends MainView {

	private final PauseController controller;
	private final PresentationController pController;

	public PauseView(PresentationController controller) {
		super();
		this.pController = controller;
		this.controller = new PauseController(controller, this);
		start();
	}

	public void start() {

		makeSquare("");
		makeSquare("");
		makeSquare("");
		makeSquare("");

		c.gridy = 1;
		c.gridheight = 2;
		makeSquare("");
		c.gridheight = 1;
		makeButtonSecond("RESUME", controller, PauseController.RESUME_AC);
		c.gridheight = 2;
		makeSquare("1:23");
		makeSquare("");

		c.gridy = 2;
		c.gridx = 1;
		c.gridheight = 1;		;
		makeButtonSecond("SAVE", controller, PauseController.SAVE_AC);

		c.gridy = 3;
		c.gridx = 0;
		c.gridheight = 2;
		makeSquare("");
		c.gridx = 1;
		makeSquare("<html><p style='margin-bottom: -7;'>KEN</p><p style='margin-top: -7;'>KEN</p></html>");
		c.gridx = 2;
		c.gridheight = 1;
//		createPanelWithButtons("");
		makeButtonSecond("EXPORT", controller, PauseController.EXPORT_AC);
		c.gridx = 3;
		c.gridheight = 2;
		makeSquare("");

		c.gridy = 4;
		c.gridx = 2;
		c.gridheight = 1;
		makeButtonSecond("EXIT", controller, PauseController.EXIT_AC);


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
