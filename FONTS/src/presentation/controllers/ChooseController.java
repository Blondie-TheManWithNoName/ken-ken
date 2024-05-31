package presentation.controllers;

import exceptions.OperandsDoNotMatchException;
import presentation.PresentationController;
import presentation.views.ChooseView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChooseController implements ActionListener {
	public final static String PLAY_AC = "PLAY_";
	public final static String BACK_AC = "BACK";

	private final PresentationController controller;
	private final ChooseView view;

	public ChooseController(PresentationController controller, ChooseView view) {
		this.controller = controller;
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().startsWith(PLAY_AC)) {
//			System.out.println("click play");
//			String[] parts = e.getActionCommand().split("_");
//			int size = Integer.parseInt(parts[1]);
//			System.out.println("SIZE: " + size);
			//view.setVisible(false);
            try {
                controller.showPlayView(4);
            } catch (OperandsDoNotMatchException ex) {
                throw new RuntimeException(ex);
            }
        } else if (e.getActionCommand().equals(BACK_AC)) {
			System.out.println("click back");
			view.setVisible(false);
			controller.showMenuView();
		}
	}
}
