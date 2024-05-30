package presentation.controllers;

import presentation.PresentationController;
import presentation.views.GenerateView1;
import presentation.views.RankingView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Generate1Controller implements ActionListener {
    public final static String NEXT_AC = "NEXT";
    public final static String BACK_AC = "BACK";

    private final PresentationController controller;
    private final GenerateView1 view;

    public Generate1Controller(PresentationController controller, GenerateView1 view) {
        this.controller = controller;
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(NEXT_AC)) {
            System.out.println("click next");
            view.setVisible(false);
            controller.showGenerateView2();
        } else if (e.getActionCommand().equals(BACK_AC)) {
            System.out.println("click back");
            view.setVisible(false);
            controller.showMenuView();
        }
    }
}
