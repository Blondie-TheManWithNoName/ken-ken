package presentation.views;

import presentation.PresentationController;
import presentation.controllers.*;

public class GenerateView1 extends MainView {
	private final PresentationController pController;
	private final Generate1Controller controller;

	public GenerateView1(PresentationController controller) {
		super();
		this.pController = controller;
		this.controller = new Generate1Controller(pController,this);
		start();
	}

	public void start() {
		addFirstRow();
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

	private void addFirstRow() {
		c.gridy = 0;

		c.gridx = 0;
		makeSquare("");

		c.gridx = 1;
		c.gridwidth = 2;
		makeSquare("SET");

		c.gridx = 3;
		c.gridwidth = 1;
		makeSquare("");
	}

	private void addSecondRow() {
		c.gridy = 1;

		c.gridx = 0;
		makeBackButton(controller, Generate1Controller.BACK_AC);

		c.gridx = 1;
		c.gridheight = 2;
		makeSquare("<html><p style='margin-bottom: -7;'>S I</p><p style='margin-top: -7;'>ZE</p></html>");

		c.gridx = 2;
		makeSpinnerSize();

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
		makeSquare("FIXED");

		c.gridx = 2;
		makeSpinnerFixed();

		c.gridx = 3;
		c.gridheight = 1;
		makeSquare("");

		c.gridy = 4;
		makeNextButton(controller, Generate1Controller.NEXT_AC);
	}

}
