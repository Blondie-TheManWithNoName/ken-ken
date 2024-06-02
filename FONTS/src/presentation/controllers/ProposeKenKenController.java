package presentation.controllers;

import exceptions.*;
import models.kenken.KenKenProposer;
import presentation.PresentationController;
import presentation.custom.JToolBarItem;
import presentation.views.ProposeKenKenView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProposeKenKenController implements ActionListener {
	public final static String CELL_CLICKED_AC = "CELL_";
	public final static String PLAY_AC = "CONTINUE";
	public final static String BACK_AC = "BACK";

	private final PresentationController controller;
	private final ProposeKenKenView view;
	private final KenKenProposer kenKenProposer;

	public ProposeKenKenController(PresentationController controller, ProposeKenKenView view, KenKenProposer kenKenProposer) {
		this.controller = controller;
		this.kenKenProposer = kenKenProposer;
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
		} else if (e.getActionCommand().equals(PLAY_AC)) {
			try {
				kenKenProposer.generateGroups();
			} catch (TooManyOperandsException | CellAlreadyInGroupException | CellHasNoGroupException |
					 GroupCellsNotContiguousException ex) {
				JOptionPane.showMessageDialog(view, ex.getMessage(), "Error generating the KenKen", JOptionPane.ERROR_MESSAGE);
				return;
			}

			if (controller.checkKenKen(kenKenProposer.getKenKen()))
			{
				view.setVisible(false);
                try {
                    controller.showPlayView();
                } catch (OperandsDoNotMatchException | ShapesAndOperationsDoNotMatchException |
                         CannotCreateOperationException ex) {
                    throw new RuntimeException(ex);
                }

            }
			else {
				JOptionPane.showMessageDialog(view, "KenKen cannot be solved.", "Fail", JOptionPane.ERROR_MESSAGE);
			}
		} else if (e.getActionCommand().equals(BACK_AC)) {
			view.setVisible(false);
			controller.showChooseProposeView();
		}
	}
}
