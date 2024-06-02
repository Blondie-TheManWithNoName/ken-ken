package presentation.controllers;

import exceptions.CannotCreateOperationException;
import exceptions.OperandsDoNotMatchException;
import exceptions.ShapesAndOperationsDoNotMatchException;
import presentation.PresentationController;
import presentation.views.GenerateView3;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Generate3Controller implements ActionListener {
    public final static String REGENERATE_AC = "REGENERATE";
    public final static String PLAY_AC = "PLAY";
    public final static String BACK_AC = "BACK";

    private final GenerateView3 view;
    private final PresentationController controller;

    public Generate3Controller(PresentationController controller, GenerateView3 view) {
        this.controller = controller;
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(REGENERATE_AC)) {
            System.out.println("click regenerate");
            try {
                view.generateKenKen();
            } catch (CannotCreateOperationException | ShapesAndOperationsDoNotMatchException |
                     OperandsDoNotMatchException ex) {
                throw new RuntimeException(ex);
            }
        } else if (e.getActionCommand().equals(PLAY_AC)) {
            view.setVisible(false);
            try {
                controller.showPlayView();
            } catch (OperandsDoNotMatchException | ShapesAndOperationsDoNotMatchException |
                     CannotCreateOperationException ex) {
                throw new RuntimeException(ex);
            }
            System.out.println("click play");
        } else if (e.getActionCommand().equals(BACK_AC)) {
            System.out.println("click back");
            view.setVisible(false);
            controller.showGenerateView2();
        }
    }
}
