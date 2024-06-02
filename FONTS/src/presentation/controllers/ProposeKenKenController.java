package presentation.controllers;

import presentation.PresentationController;
import presentation.custom.JToolBarItem;
import presentation.views.ProposeKenKenView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProposeKenKenController implements ActionListener {
	public final static String CELL_CLICKED_AC = "CELL_";
	public final static String CONTINUE_AC = "CONTINUE";
	public final static String BACK_AC = "BACK";

	private final PresentationController controller;
	private final ProposeKenKenView view;

	public ProposeKenKenController(PresentationController controller, ProposeKenKenView view) {
		this.controller = controller;
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() instanceof JToolBarItem) {
			view.setTool((JToolBarItem) e.getSource());
		} else if (e.getActionCommand().startsWith(CELL_CLICKED_AC)) {
			String[] parts = e.getActionCommand().split("_");
			int row = Integer.parseInt(parts[1]);
			int col = Integer.parseInt(parts[2]);
			view.useToolWithCell(row, col);
		} else if (e.getActionCommand().equals(CONTINUE_AC)) {
			view.generateGroups();
		} else if (e.getActionCommand().equals(BACK_AC)) {
			view.setVisible(false);
			controller.showChooseProposeView();
		}
	}
}
