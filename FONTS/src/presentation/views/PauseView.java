package presentation.views;

import presentation.PresentationController;
import presentation.controllers.PauseController;

/**
 * PauseView represents the GUI window for the pause menu.
 */
public class PauseView extends MainView {

	private final PauseController controller;
	private final PresentationController pController;
	private final int minutes;
	private final int seconds;

	/**
	 * Constructor for PauseView.
	 * @param controller Presentation controller
	 * @param minutes Minutes remaining
	 * @param seconds Seconds remaining
	 */
	public PauseView(PresentationController controller, int minutes, int seconds) {
		super();
		this.pController = controller;
		this.controller = new PauseController(controller, this);
		this.minutes = minutes;
		this.seconds = seconds;
		start();
	}

	/**
	 * Initializes the pause menu.
	 */
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
		makeSquare(String.format("%02d:%02d", minutes, seconds));
		makeSquare("");

		c.gridy = 2;
		c.gridx = 1;
		c.gridheight = 1;
		makeButtonSecond("SAVE", controller, PauseController.SAVE_AC);

		c.gridy = 3;
		c.gridx = 0;
		c.gridheight = 2;
		makeSquare("");
		c.gridx = 1;
		makeSquare("<html><p style='margin-bottom: -7;'>KEN</p><p style='margin-top: -7;'>KEN</p></html>");
		c.gridx = 2;
		c.gridheight = 1;
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
