package presentation.controllers;

import presentation.PresentationController;

import presentation.views.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class RankingController implements ActionListener {

    public final static String BACK_AC = "BACK";

    private final PresentationController controller;
    private final RankingView view;

    public RankingController(PresentationController controller, RankingView view) {
        this.controller = controller;
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(BACK_AC)) {
            System.out.println("click back");
            view.setVisible(false);
            controller.showHomeView();
        }
    }
}
