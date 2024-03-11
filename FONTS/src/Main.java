import presentation.views.MainMenuView;

import javax.swing.*;

public class Main {
	public static void main(String[] args) {
		MainMenuView view = new MainMenuView();
		SwingUtilities.invokeLater(view::start);
	}
}
