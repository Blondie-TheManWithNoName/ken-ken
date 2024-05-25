package presentation.controllers;

import java.awt.event.ActionEvent;

public class RankingController {

    public final static String BACK_AC = "BACK";

    //private final RankingView view;

    //public RankingController(RankingView view) {this.view = view;}

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(BACK_AC)) {
            // return to home view
        }
    }
}
