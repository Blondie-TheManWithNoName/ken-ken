package presentation.controllers;

import java.awt.event.ActionEvent;

public class GenerateController {
    public final static String NEXT_AC = "NEXT";
    public final static String GENERATE_AC = "GENERATE";
    public final static String PLAY_AC = "PLAY";
    public final static String BACK_AC = "BACK";

    //private final GenerateView view;

    //public GenerateController(GenerateView view) {this.view = view;}

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(NEXT_AC)) {
            // next
        } else if (e.getActionCommand().equals(GENERATE_AC)) {
            // next
        } else if (e.getActionCommand().equals(PLAY_AC)) {
            // next
        } else if (e.getActionCommand().equals(BACK_AC)) {
            // return to main menu view
        }
    }
}
