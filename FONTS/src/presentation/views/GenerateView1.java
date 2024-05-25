package presentation.views;

import presentation.controllers.GenerateController;
import presentation.controllers.MainMenuController;

import java.awt.event.ActionListener;

public class GenerateView1 extends MainView {
	private final GenerateController controller = new GenerateController(this);

	public GenerateView1() {
		super();
	}

	public void start() {

		makeSquare("");
		c.gridwidth = 2;
		makeSquare("SET");
		c.gridwidth = 1;
		makeSquare("");
		c.gridy = 1;

		makeBackButton(controller, GenerateController.BACK_AC);
		c.gridheight = 2;
		makeSquare("<html><p style='margin-bottom: -7;'>S I</p><p style='margin-top: -7;'>ZE</p></html>");
		c.gridheight = 1;
		makeSpinnerSize();
		c.gridheight = 3;
		makeSquare("");

		c.gridy = 2;
		c.gridx = 0;
		c.gridheight = 3;
		makeSquare("");
		c.gridx = 2;
		c.gridheight = 1;
		makeSquare("");

		c.gridy = 3;
		c.gridx = 1;
		c.gridheight = 2;
		makeSquare("FIXED");;
		c.gridx = 2;
		c.gridheight = 1;
		makeSpinnerFixed();

		c.gridy = 4;
		c.gridx = 2;
		c.gridheight = 1;
		makeSquare("");
		c.gridx = 3;
		makeNextButton(controller, GenerateController.NEXT_AC);

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
