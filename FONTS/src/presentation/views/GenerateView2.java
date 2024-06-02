package presentation.views;

import presentation.PresentationController;
import presentation.controllers.Generate1Controller;
import presentation.controllers.Generate2Controller;

public class GenerateView2 extends MainView {
	private final PresentationController pController;
	private final Generate2Controller controller;
	private final String[] shapeImgs = {"sprites/BLOCK_U.png",			"sprites/BLOCK_S.png",
										"sprites/POINT_U.png",			"sprites/POINT_S.png",
										"sprites/T_U.png", 				"sprites/T_S.png",
										"sprites/L_U.png",				"sprites/L_S.png",
										"sprites/CORNER_U.png", 		"sprites/CORNER_S.png",
										"sprites/J_U.png",				"sprites/J_S.png",
										"sprites/I_U.png",				"sprites/I_S.png",
										"sprites/DASH_U.png",			"sprites/DASH_S.png",
										"sprites/ZIGZAG_U.png",			"sprites/ZIGZAG_S.png"};

	private final String[] operationImgs = {"sprites/ADDITION_U.png",			"sprites/ADDITION_S.png",
											"sprites/SUBTRACTION_U.png",		"sprites/SUBTRACTION_S.png",
											"sprites/MULTIPLICATION_U.png", 	"sprites/MULTIPLICATION_S.png",
											"sprites/DIVISION_U.png",			"sprites/DIVISION_S.png",
											"sprites/EQUAL_U.png", 				"sprites/EQUAL_S.png",
											"sprites/POWER_U.png",				"sprites/POWER_S.png",
											"sprites/GCD_U.png",				"sprites/GCD_S.png",
											"sprites/LCM_U.png",				"sprites/LCM_S.png"};


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
		makeCheckBoxContainer(shapeImgs, controller, Generate2Controller.CHECK_IMG_);

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
		makeCheckBoxContainer(operationImgs, controller, Generate2Controller.CHECK_OP_);

		c.gridx = 3;
		c.gridheight = 1;
		makeSquare("");

		c.gridy = 4;
		makeNextButton(controller, Generate2Controller.NEXT_AC);
	}

}
