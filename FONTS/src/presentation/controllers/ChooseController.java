package presentation.controllers;

import presentation.views.ChooseView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChooseController implements ActionListener {
	public final static String NEXT_AC = "NEXT";
	public final static String GENERATE_AC = "GENERATE";
	public final static String PLAY_AC = "PLAY";
	public final static String BACK_AC = "BACK";

	private final ChooseView view;

	public ChooseController(ChooseView view) {
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals(NEXT_AC)) {
			System.out.println("click next");
		} else if (e.getActionCommand().equals(GENERATE_AC)) {
			// next
		} else if (e.getActionCommand().equals(PLAY_AC)) {
			// next
		} else if (e.getActionCommand().equals(BACK_AC)) {
			System.out.println("click back");
		}
	}
}
