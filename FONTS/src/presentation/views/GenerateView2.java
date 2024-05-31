package presentation.views;

import presentation.PresentationController;
import presentation.controllers.Generate1Controller;
import presentation.controllers.Generate2Controller;

public class GenerateView2 extends MainView {
	private final PresentationController pController;
	private final Generate2Controller controller;
	private final String[] shapeImgs = {"sprites/select_1.png", "sprites/select_1c.png",
										"sprites/select_1.png", "sprites/select_1c.png",
										"sprites/select_1.png", "sprites/select_1c.png",
										"sprites/select_1.png", "sprites/select_1c.png",
										"sprites/select_1.png", "sprites/select_1c.png",
										"sprites/select_1.png", "sprites/select_1c.png",
										"sprites/select_1.png", "sprites/select_1c.png",
										"sprites/select_1.png", "sprites/select_1c.png",
										"sprites/select_1.png", "sprites/select_1c.png"};


	public GenerateView2(PresentationController controller) {
		super();
		this.pController = controller;
		this.controller = new Generate2Controller(pController,this);
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
		makeSquare("CHOOSE");

		c.gridx = 3;
		c.gridwidth = 1;
		makeSquare("");
	}

	private void addSecondRow() {
		c.gridy = 1;

		c.gridx = 0;
		makeBackButton(controller, Generate2Controller.BACK_AC);

		c.gridx = 1;
		c.gridheight = 2;
		makeSquare("<html><p style='margin-bottom: -7;'>SHA</p><p style='margin-top: -7;'>PES</p></html>");

		c.gridx = 2;
		makeCheckBoxContainer(shapeImgs);

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
		makeSquare("<html><p style='margin-bottom: -7;'>OPERA</p><p style='margin-top: -7;'>TIONS</p></html>");;

		c.gridx = 2;
		makeCheckBoxContainer(shapeImgs);

		c.gridx = 3;
		c.gridheight = 1;
		makeSquare("");

		c.gridy = 4;
		makeNextButton(controller, Generate2Controller.NEXT_AC);
	}

}
