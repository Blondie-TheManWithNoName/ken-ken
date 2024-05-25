package presentation.controllers;

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

	private final MainMenuView view;

	public MainMenuController(MainMenuView view) {
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals(NEW_KENKEN_AC)) {
			System.out.println("click new");
		} else if (e.getActionCommand().equals(LOAD_KENKEN_AC)) {
			System.out.println("click load");
		} else if (e.getActionCommand().equals(PROPOSE_KENKEN_AC)) {
			//change to propose view
		} else if (e.getActionCommand().equals(GENERATE_KENKEN_AC)) {
			//change to generate view
		} else if (e.getActionCommand().equals(IMPORT_KENKEN_AC)) {
			//import kenken from file
		} else if (e.getActionCommand().equals(BACK_AC)) {
			System.out.println("click back");
		}
	}
}
