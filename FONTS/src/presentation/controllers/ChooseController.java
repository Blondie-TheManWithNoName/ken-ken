package presentation.controllers;

import presentation.PresentationController;
import presentation.views.ChooseView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChooseController implements ActionListener {
	public final static String PLAY_AC = "PLAY";
	public final static String BACK_AC = "BACK";

	private final PresentationController controller;
	private final ChooseView view;

	public ChooseController(PresentationController controller, ChooseView view) {
		this.controller = controller;
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals(PLAY_AC)) {
			System.out.println("click play");
			//view.setVisible(false);
			//controller.showProposeView();
		} else if (e.getActionCommand().equals(BACK_AC)) {
			System.out.println("click back");
			view.setVisible(false);
			controller.showMenuView();
		}
	}
}
