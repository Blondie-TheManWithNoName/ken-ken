package presentation.controllers;

import presentation.PresentationController;
import presentation.views.GenerateView1;
import presentation.views.GenerateView2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Generate2Controller implements ActionListener {
    public final static String NEXT_AC = "NEXT";
    public final static String GENERATE_AC = "GENERATE";
    public final static String PLAY_AC = "PLAY";
    public final static String BACK_AC = "BACK";

    private final PresentationController controller;
    private final GenerateView2 view;

    public Generate2Controller(PresentationController controller, GenerateView2 view) {
        this.controller = controller;
        this.view = view;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(NEXT_AC)) {
            System.out.println("click next");
            //view.setVisible(false);
            //controller.showGenerateView3();
        } else if (e.getActionCommand().equals(BACK_AC)) {
            System.out.println("click back");
            view.setVisible(false);
            controller.showGenerateView1();
        }
    }
}
