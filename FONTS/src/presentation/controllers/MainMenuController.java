package presentation.controllers;

import presentation.views.MainMenuView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuController implements ActionListener {
	public final static String PROPOSE_KENKEN_AC = "PROPOSE_KENKEN";
	public final static String GENERATE_KENKEN_AC = "GENERATE_KENKEN";
	public final static String LOAD_KENKEN_AC = "LOAD_KENKEN";
	public final static String LOAD_SAVED_GAME_AC = "LOAD_SAVED_GAME";
	public final static String SEE_RANKING_AC = "SEE_RANKING";
	public final static String EXIT_AC = "EXIT";

	private final MainMenuView view;

	public MainMenuController(MainMenuView view) {
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals(PROPOSE_KENKEN_AC)) {
//			view.proposeKenKen();
		} else if (e.getActionCommand().equals(GENERATE_KENKEN_AC)) {
			// TODO:
		} else if (e.getActionCommand().equals(LOAD_KENKEN_AC)) {
			// TODO
		} else if (e.getActionCommand().equals(LOAD_SAVED_GAME_AC)) {
			// TODO
		} else if (e.getActionCommand().equals(SEE_RANKING_AC)) {
			// TODO
		} else if (e.getActionCommand().equals(EXIT_AC)) {
			System.exit(0);
		}
	}
}
