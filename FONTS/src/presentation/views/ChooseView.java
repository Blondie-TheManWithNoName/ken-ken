package presentation.views;

import presentation.controllers.ChooseController;

import javax.swing.*;

public class ChooseView extends MainView {
	private final ChooseController controller = new ChooseController(this);

	public ChooseView() {
		super();
	}

	public void start() {

		makeSquare("");
		makeSquare("");
		makeSquare("");
		makeSquare("");
		c.gridy = 1;

		makeBackButton(controller, ChooseController.BACK_AC);
		c.gridheight = 2;
		makeSquare("<html><p style='margin-bottom: -7;'>S I</p><p style='margin-top: -7;'>ZE</p></html>");
		c.gridheight = 1;
		makeSpinnerSize();
		c.gridheight = 2;
		makeSquare("");

		c.gridy = 2;
		c.gridx = 0;
		c.gridheight = 3;
		makeSquare("");;
		c.gridx = 2;
		c.gridheight = 1;		;
		makeButtonFirst("PLAY",controller, ChooseController.PLAY_AC);

		c.gridy = 3;
		c.gridx = 1;
		c.gridheight = 2;
		makeSquare("");;
		c.gridx = 2;
		makeSquare("");
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
