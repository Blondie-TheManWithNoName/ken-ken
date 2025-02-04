package presentation.controllers;

import presentation.PresentationController;
import presentation.views.MainMenuView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuController implements ActionListener {
	public final static String NEW_KENKEN_AC = "NEW_KENKEN";
	public final static String LOAD_KENKEN_AC = "LOAD_KENKEN";
	public final static String PROPOSE_KENKEN_AC = "PROPOSE_KENKEN";
	public final static String GENERATE_KENKEN_AC = "GENERATE_KENKEN";
	public final static String IMPORT_KENKEN_AC = "IMPORT_KENKEN";
	public final static String BACK_AC = "BACK";

	private final PresentationController controller;
	private final MainMenuView view;
	private String path;

	public MainMenuController(PresentationController controller, MainMenuView view) {
        this.controller = controller;
        this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals(NEW_KENKEN_AC)) {
			System.out.println("click new");
			view.setVisible(false);
			controller.showChoosePlayView();
		} else if (e.getActionCommand().equals(LOAD_KENKEN_AC)) {
			System.out.println("click load");
			path = controller.showLoadDialog();
			if (path != null) {
				view.setVisible(false);
				controller.loadGame(path);
			}
		} else if (e.getActionCommand().equals(PROPOSE_KENKEN_AC)) {
			view.setVisible(false);
			controller.showChooseProposeView();
			System.out.println("click propose");
		} else if (e.getActionCommand().equals(GENERATE_KENKEN_AC)) {
			System.out.println("click generate");
			view.setVisible(false);
			controller.showGenerateView1();
		} else if (e.getActionCommand().equals(IMPORT_KENKEN_AC)) {
			System.out.println("click import");
			path = controller.showImportDialog();
			if (path != null) {
				view.setVisible(false);
				controller.importKenKen(path);
			}
		} else if (e.getActionCommand().equals(BACK_AC)) {
			System.out.println("click back");
            view.setVisible(false);
            controller.showHomeView();
		}
	}
}
