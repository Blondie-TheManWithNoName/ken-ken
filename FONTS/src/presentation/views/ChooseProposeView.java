package presentation.views;

import presentation.PresentationController;
import presentation.controllers.ChoosePlayController;
import presentation.controllers.ChooseProposeController;

/**
 * View for choosing to propose a KenKen puzzle.
 */
public class ChooseProposeView extends MainView {
	private final PresentationController pController;
	private final ChooseProposeController controller;

	/**
	 * Constructs a ChooseProposeView with the given PresentationController.
	 *
	 * @param controller The PresentationController instance.
	 */
	public ChooseProposeView(PresentationController controller) {
		super();
		this.pController = controller;
		this.controller = new ChooseProposeController(pController, this);
		start();
	}

	/**
	 * Initializes the view components.
	 */
	public void start() {
		addEmptyRow(0);
		addSecondRow();
		addThirdRow();
		addEmptyRow(5);
	}

	/**
	 * Adds an empty row to the layout.
	 *
	 * @param y The y-coordinate of the row.
	 */
	private void addEmptyRow(int y) {
		c.gridy = y;
		for (int x = 0; x < 4; x++) {
			c.gridx = x;
			makeSquare("");
		}
	}

	/**
	 * Adds the second row components to the layout.
	 */
	private void addSecondRow() {
		c.gridy = 1;

		c.gridx = 0;
		makeBackButton(controller, ChoosePlayController.BACK_AC);

		c.gridx = 1;
		c.gridheight = 2;
		makeSquare("<html><p style='margin-bottom: -7;'>S I</p><p style='margin-top: -7;'>ZE</p></html>");

		c.gridx = 2;
		c.gridheight = 1;
		makeSpinnerSize(controller);

		c.gridx = 3;
		c.gridheight = 2;
		makeSquare("");

		c.gridy = 2;
		c.gridx = 0;
		c.gridheight = 1;
		makeSquare("");

		c.gridx = 2;
		c.gridheight = 1;
		makeButtonFirst("PROPOSE", controller, ChooseProposeController.PROPOSE_AC);
	}

	/**
	 * Adds the third row components to the layout.
	 */
	private void addThirdRow() {
		c.gridy = 3;

		c.gridx = 0;
		c.gridheight = 2;
		makeSquare("");
		c.gridx = 1;
		makeSquare("");
		c.gridx = 2;
		makeSquare("");

		c.gridx = 3;
		c.gridheight = 1;
		makeSquare("");

		c.gridy = 4;
		makeSquare("");
	}
}
