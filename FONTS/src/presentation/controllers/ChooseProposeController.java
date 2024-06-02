package presentation.controllers;

import exceptions.CannotCreateOperationException;
import exceptions.OperandsDoNotMatchException;
import exceptions.ShapesAndOperationsDoNotMatchException;
import presentation.PresentationController;
import presentation.custom.JMainSpinner;
import presentation.views.ChoosePlayView;
import presentation.views.ChooseProposeView;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class ChooseProposeController implements ActionListener, ChangeListener {
	public final static String PROPOSE_AC = "PROPOSE_";
	public final static String BACK_AC = "BACK";

	private final PresentationController controller;
	private final ChooseProposeView view;
	private int size = 3;

	public ChooseProposeController(PresentationController controller, ChooseProposeView view) {
		this.controller = controller;
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().startsWith(PROPOSE_AC)) {
			view.setVisible(false);
            controller.setSizeAndFixed(size, 0);
            controller.showProposeView();
        } else if (e.getActionCommand().equals(BACK_AC)) {
			System.out.println("click back");
			view.setVisible(false);
			controller.showMenuView();
		}
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		JMainSpinner spinner = (JMainSpinner) e.getSource();
		String spinnerName = spinner.getName();

		if (spinnerName.equals("sizeSpinner")) {
			size = (int) spinner.getValue();
			System.out.println("Nuevo valor del spinner de tamaño: " + size);
		}
	}
}
