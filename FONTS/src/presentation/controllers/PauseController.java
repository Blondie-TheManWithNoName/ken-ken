package presentation.controllers;

import exceptions.CannotCreateOperationException;
import exceptions.OperandsDoNotMatchException;
import exceptions.ShapesAndOperationsDoNotMatchException;
import presentation.PresentationController;
import presentation.views.MainMenuView;
import presentation.views.PauseView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PauseController implements ActionListener {
	public final static String RESUME_AC = "RESUME_AC";
	public final static String SAVE_AC = "SAVE_AC";
	public final static String EXPORT_AC = "EXPORT_AC";
	public final static String EXIT_AC = "EXIT_AC";

	private final PresentationController controller;
	private final PauseView view;
	private String path = "/data";

	public PauseController(PresentationController controller, PauseView view) {
        this.controller = controller;
        this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals(RESUME_AC)) {
			System.out.println("click resume");
			view.setVisible(false);
            try {
                controller.resumePlayView();
            } catch (OperandsDoNotMatchException | ShapesAndOperationsDoNotMatchException |
                     CannotCreateOperationException ex) {
                throw new RuntimeException(ex);
            }
        } else if (e.getActionCommand().equals(SAVE_AC)) {
			System.out.println("click save");
			path = controller.showSaveDialog();
			view.setVisible(false);
			controller.saveGame();
		} else if (e.getActionCommand().equals(EXPORT_AC)) {
			System.out.println("click export");
			view.setVisible(false);
			controller.exportKenKen(path);
		} else if (e.getActionCommand().equals(EXIT_AC)) {
			System.out.println("click exit");
			view.setVisible(false);
			controller.showMenuView();
		}
	}
}
