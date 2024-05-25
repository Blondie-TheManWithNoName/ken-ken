package presentation.controllers;

import java.awt.event.ActionEvent;

public class HomeController {
    public final static String START_KENKEN_AC = "START_KENKEN";
    public final static String SEE_RANKING_AC = "SEE_RANKING";
    public final static String EXIT_AC = "EXIT";

    //private final HomeView view;

    //public HomeController(HomeView view) {this.view = view;}

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(START_KENKEN_AC)) {
//			change view to menu
        } else if (e.getActionCommand().equals(SEE_RANKING_AC)) {
            // change view to ranking
        } else if (e.getActionCommand().equals(EXIT_AC)) {
            System.exit(0);
        }
    }
}
