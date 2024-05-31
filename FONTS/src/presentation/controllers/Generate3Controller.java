package presentation.controllers;

import presentation.views.Generate3View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Generate3Controller implements ActionListener {
    public final static String REGENERATE_AC = "REGENERATE";
    public final static String PLAY_AC = "PLAY";
    public final static String BACK_AC = "BACK";

    private final Generate3View view;

    public Generate3Controller(Generate3View view) {this.view = view;}

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(REGENERATE_AC)) {
            System.out.println("click regenerate");
        } else if (e.getActionCommand().equals(PLAY_AC)) {
            System.out.println("click play");
        } else if (e.getActionCommand().equals(BACK_AC)) {
            System.out.println("click back");
        }
    }
}
