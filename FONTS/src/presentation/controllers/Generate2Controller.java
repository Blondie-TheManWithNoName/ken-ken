package presentation.controllers;

import presentation.views.GenerateView1;
import presentation.views.GenerateView2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Generate2Controller implements ActionListener {
    public final static String NEXT_AC = "NEXT";
    public final static String GENERATE_AC = "GENERATE";
    public final static String PLAY_AC = "PLAY";
    public final static String BACK_AC = "BACK";

    private final GenerateView2 view;

    public Generate2Controller(GenerateView2 view) {this.view = view;}

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
