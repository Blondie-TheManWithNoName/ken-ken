package presentation.views;

import presentation.controllers.Generate1Controller;
import presentation.controllers.Generate2Controller;

public class GenerateView2 extends MainView {
	private final Generate2Controller controller = new Generate2Controller(this);
	private final String[] shapeImgs = {"sprites/select_1.png", "sprites/select_1c.png",
										"sprites/select_1.png", "sprites/select_1c.png",
										"sprites/select_1.png", "sprites/select_1c.png",
										"sprites/select_1.png", "sprites/select_1c.png",
										"sprites/select_1.png", "sprites/select_1c.png",
										"sprites/select_1.png", "sprites/select_1c.png",
										"sprites/select_1.png", "sprites/select_1c.png",
										"sprites/select_1.png", "sprites/select_1c.png",
										"sprites/select_1.png", "sprites/select_1c.png"};


	public GenerateView2() {
		super();
	}

	public void start() {

		makeSquare("");
		c.gridwidth = 2;
		makeSquare("CHOOSE");
		c.gridwidth = 1;
		makeSquare("");
		c.gridy = 1;

		makeBackButton(controller, Generate1Controller.BACK_AC);
		c.gridheight = 2;
		makeSquare("<html><p style='margin-bottom: -7;'>SHA</p><p style='margin-top: -7;'>PES</p></html>");
		makeCheckBoxContainer(shapeImgs);
		c.gridheight = 1;
		makeSquare("");

		c.gridy = 2;
		c.gridx = 0;
		c.gridheight = 1;
		makeSquare("");
		c.gridheight = 1;
		c.gridx = 3;
		makeSquare("");

		c.gridy = 3;
		c.gridx = 0;
		makeSquare("");
		c.gridx = 1;
		c.gridheight = 2;
		makeSquare("<html><p style='margin-bottom: -7;'>OPERA</p><p style='margin-top: -7;'>TIONS</p></html>");;
		c.gridx = 2;
		makeCheckBoxContainer(shapeImgs);

		c.gridy = 4;
		c.gridheight = 1;
		c.gridx = 3;
		makeNextButton(controller, Generate2Controller.NEXT_AC);

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
