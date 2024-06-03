package presentation.views;

import exceptions.*;
import models.kenken.*;
import presentation.PresentationController;
import presentation.controllers.Generate3Controller;
import presentation.custom.JKenKenCell;
import presentation.custom.JKenKenPanel;

/**
 * View for generating a KenKen puzzle.
 */
public class GenerateView3 extends MainView {
	private final Generate3Controller controller;
	private final PresentationController pController;
	private JKenKenPanel kenKenPanel;
	private KenKen kenKen;

	/**
	 * Constructs a GenerateView3 with the given PresentationController.
	 *
	 * @param controller The PresentationController instance.
	 * @throws CannotCreateOperationException     If the operation cannot be created.
	 * @throws OperandsDoNotMatchException         If operands do not match the operation.
	 * @throws ShapesAndOperationsDoNotMatchException If shapes and operations do not match.
	 */
	public GenerateView3(PresentationController controller) throws CannotCreateOperationException, OperandsDoNotMatchException, ShapesAndOperationsDoNotMatchException {
		super();
		this.pController = controller;
		this.controller = new Generate3Controller(pController, this);
		generateKenKen();
		start();
	}

	/**
	 * Generates a KenKen puzzle.
	 *
	 * @throws CannotCreateOperationException     If the operation cannot be created.
	 * @throws OperandsDoNotMatchException         If operands do not match the operation.
	 * @throws ShapesAndOperationsDoNotMatchException If shapes and operations do not match.
	 */
	public void generateKenKen() throws CannotCreateOperationException, OperandsDoNotMatchException, ShapesAndOperationsDoNotMatchException {
		this.kenKen = pController.generateKenKen();
		JKenKenCell.CELL_SIZE = 25;
		this.kenKenPanel = new JKenKenPanel(kenKen);
		swapKenKen(kenKenPanel);
		revalidate();
	}

	/**
	 * Initializes and displays the view components.
	 */
	public void start() {
		addFirstRow();
		addSecondRow();
		addThirdRow();
		//addEmptyRow(5);
		addFourthRow();
		revalidate();
		repaint();
	}

	/**
	 * Adds an empty row at the specified position.
	 *
	 * @param y The vertical position of the row.
	 */
	private void addEmptyRow(int y) {
		c.gridy = y;
		for (int x = 0; x < 4; x++) {
			c.gridx = x;
			makeSquare("");
		}
	}

	/**
	 * Adds components for the first row of the view.
	 */
	private void addFirstRow() {
		c.gridy = 0;
		c.gridx = 0;
		makeSquare("");
		c.gridwidth = 2;
		c.gridx = 1;
		makeSquare("CHECK");
		c.gridwidth = 1;
		c.gridx = 3;
		makeSquare("");
	}

	/**
	 * Adds components for the second row of the view.
	 */
	private void addSecondRow() {
		c.gridy = 1;
		c.gridx = 0;
		makeBackButton(controller, Generate3Controller.BACK_AC);
		c.gridx = 1;
		c.gridwidth = 2;
		c.gridheight = 4;
		makeKenKenPanel(kenKenPanel);
		c.gridx = 3;
		c.gridwidth = 1;
		c.gridheight = 2;
		makeSquare("");
		c.gridy = 2;
		c.gridx = 0;
		c.gridheight = 1;
		makeSquare("");
	}

	/**
	 * Adds components for the third row of the view.
	 */
	private void addThirdRow() {
		c.gridheight = 2;
		c.gridy = 3;
		c.gridx = 0;
		makeSquare("");
		c.gridheight = 1;
		c.gridy = 3;
		c.gridx = 1;
		//makeButtonSecond("REGENERATE", controller, Generate3Controller.REGENERATE_AC);
		c.gridx = 2;
		//makeButtonFirst("PLAY", controller, Generate3Controller.PLAY_AC);
		c.gridx = 3;
		makeSquare("");
		c.gridy = 4;
		c.gridx = 3;
		makeSquare("");

	}

	/**
	 * Adds components for the Fourth row of the view.
	 */
	private void addFourthRow() {
		c.gridheight = 1;
		c.gridy = 5;
		c.gridx = 0;
		makeSquare("");
		c.gridx = 1;
		makeButtonSecond("REGENERATE", controller, Generate3Controller.REGENERATE_AC);
		c.gridx = 2;
		makeButtonFirst("PLAY", controller, Generate3Controller.PLAY_AC);
		c.gridx = 3;
		makeSquare("");
	}
}
