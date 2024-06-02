package presentation.controllers;

import exceptions.CannotCreateOperationException;
import exceptions.OperandsDoNotMatchException;
import exceptions.ShapesAndOperationsDoNotMatchException;
import presentation.PresentationController;
import presentation.custom.JMainSpinner;
import presentation.views.ChoosePlayView;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class ChoosePlayController implements ActionListener, ChangeListener {
	public final static String PLAY_AC = "PLAY_";
	public final static String BACK_AC = "BACK";
	public static final String SIZE_AC = "SIZE";

	private final PresentationController controller;
	private final ChoosePlayView view;
	private int size = 3;

	public ChoosePlayController(PresentationController controller, ChoosePlayView view) {
		this.controller = controller;
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().startsWith(PLAY_AC)) {
            try {
				view.setVisible(false);
				controller.initializeKenKenShapesAndOperations();
				controller.setSizeAndFixed(size, new Random().nextInt(size));
				controller.generateKenKen();
				controller.showPlayView();
            } catch (ShapesAndOperationsDoNotMatchException ex) {
                System.out.println("Will not use it");
            } catch (CannotCreateOperationException | OperandsDoNotMatchException ex) {
                throw new RuntimeException(ex);
            }
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
