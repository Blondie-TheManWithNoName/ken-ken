package presentation.controllers;

import presentation.PresentationController;
import presentation.views.GenerateView1;
import presentation.views.RankingView;
import presentation.custom.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;

public class Generate1Controller implements ActionListener, ChangeListener {
    public final static String NEXT_AC = "NEXT";
    public final static String BACK_AC = "BACK";
    public static final String SIZE_AC = "SIZE";
    public static final String FIXED_AC = "FIXED";

    private final PresentationController controller;
    private final GenerateView1 view;

    private int size = 3;
    private int fixed = 0;

    public Generate1Controller(PresentationController controller, GenerateView1 view) {
        this.controller = controller;
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(NEXT_AC)) {
            System.out.println("click next");
            view.setVisible(false);
            controller.setSizeAndFixed(size,fixed);
            controller.showGenerateView2();
        } else if (e.getActionCommand().equals(BACK_AC)) {
            System.out.println("click back");
            view.setVisible(false);
            controller.showMenuView();
        }
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        JMainSpinner spinner = (JMainSpinner) e.getSource();
        String spinnerName = spinner.getName();

        if (spinnerName.equals("sizeSpinner")) {
            size = (int) spinner.getValue();
            System.out.println("Nuevo valor del spinner de tamaño: " + size);
        } else if (spinnerName.equals("fixedSpinner")) {
            fixed = (int) spinner.getValue();
            System.out.println("Nuevo valor del spinner fijo: " + fixed);
        }
    }
}
