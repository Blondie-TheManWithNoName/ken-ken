package presentation.controllers;

import presentation.views.*;

import presentation.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import presentation.PresentationController;

public class HomeController implements ActionListener {
    public final static String START_KENKEN_AC = "START_KENKEN";
    public final static String SEE_RANKING_AC = "SEE_RANKING";
    public final static String EXIT_AC = "EXIT";

    private final PresentationController controller;
    private final HomeView view;
    
    public HomeController(PresentationController controller, HomeView view) {
        this.controller = controller;
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(START_KENKEN_AC)) {
            System.out.println("click start");
            view.setVisible(false);
            controller.showMenuView();
        } else if (e.getActionCommand().equals(SEE_RANKING_AC)) {
            System.out.println("click ranking");
        } else if (e.getActionCommand().equals(EXIT_AC)) {
            System.out.println("click exit");
            System.exit(0);
        }
    }
}
