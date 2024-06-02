package presentation.views;

import exceptions.CannotCreateOperationException;
import exceptions.OperandsDoNotMatchException;
import exceptions.ShapesAndOperationsDoNotMatchException;
import models.kenken.KenKen;
import models.operations.Operation;
import models.topologies.Topology;
import presentation.PresentationController;
import presentation.custom.JKenKenCell;
import presentation.custom.JKenKenPanel;
import presentation.controllers.*;

import java.util.List;


public class GenerateView3 extends MainView {

	private final Generate3Controller controller;
	private final PresentationController pController;
	private  JKenKenPanel kenKenPanel;
	private  KenKen kenKen;

	public GenerateView3(PresentationController controller) throws CannotCreateOperationException, OperandsDoNotMatchException, ShapesAndOperationsDoNotMatchException {
		this.pController = controller;
		this.controller = new Generate3Controller(pController, this);
		generateKenKen();
		start();

		}

	public void generateKenKen() throws CannotCreateOperationException, OperandsDoNotMatchException, ShapesAndOperationsDoNotMatchException {
		this.kenKen = pController.generateKenKen();
		JKenKenCell.CELL_SIZE = 25;
		this.kenKenPanel = new JKenKenPanel(kenKen);
		swapKenKen(kenKenPanel);
		revalidate();
	}

	public void start() {

		c.gridy = 0;
		c.gridx = 0;
		makeSquare("");
		c.gridwidth = 2;
		c.gridx = 1;
		makeSquare("CHECK");
		c.gridwidth = 1;
		c.gridx = 3;
		makeSquare("");

		c.gridy = 1;
		c.gridx = 0;
		makeBackButton(controller, Generate3Controller.BACK_AC);
		c.gridx = 1;
		c.gridwidth = 2;
		c.gridheight = 3;
		makeKenKenPanel(kenKenPanel);
		c.gridx = 3;
		c.gridwidth = 1;
		c.gridheight = 2;
		makeSquare("");

		c.gridheight = 1;
		c.gridy = 2;
		c.gridx = 0;
		makeSquare("");


		c.gridy = 3;
		c.gridx = 0;
		makeSquare("");
		c.gridx = 3;
		c.gridheight = 2;
		makeSquare("");

		c.gridheight = 1;
		c.gridy = 4;
		c.gridx = 0;
		makeSquare("");
		c.gridx = 1;
		makeButtonSecond("REGENERATE", controller, Generate3Controller.REGENERATE_AC);
		c.gridx = 2;
		makeButtonFirst("PLAY", controller, Generate3Controller.PLAY_AC);

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
		this.repaint();

	}
}
