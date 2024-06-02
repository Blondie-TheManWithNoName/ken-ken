package presentation.controllers;

import exceptions.CannotCreateOperationException;
import exceptions.OperandsDoNotMatchException;
import exceptions.ShapesAndOperationsDoNotMatchException;
import models.operations.*;
import models.topologies.Shape;
import models.topologies.Topology;
import presentation.PresentationController;
import presentation.custom.JMainOptionPane;
import presentation.views.GenerateView2;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Generate2Controller implements ActionListener {
    public final static String NEXT_AC = "NEXT";
    public final static String GENERATE_AC = "GENERATE";
    public final static String PLAY_AC = "PLAY";
    public final static String BACK_AC = "BACK";
    public final static String CHECK_IMG_ = "CHECK_IMG_";
    public final static String CHECK_OP_ = "CHECK_OP_";

    private final PresentationController controller;
    private final GenerateView2 view;

    private boolean[] shapes = new  boolean[9];
    private boolean[] operations = new  boolean[8];

    private OperationMapping[] operationMappings = new OperationMapping[]{
            new OperationMapping(false, OperationAddition.class),
            new OperationMapping(false, OperationSubtraction.class),
            new OperationMapping(false, OperationMultiplication.class),
            new OperationMapping(false, OperationDivision.class),
            new OperationMapping(false, OperationEquality.class),
            new OperationMapping(false, OperationGCD.class),
            new OperationMapping(false, OperationLCM.class),
            new OperationMapping(false, OperationPower.class)
    };

    private ShapeMapping[] shapeMappings = new ShapeMapping[]{
            new ShapeMapping(false, Shape.BLOCK),
            new ShapeMapping(false, Shape.POINT),
            new ShapeMapping(false, Shape.T),
            new ShapeMapping(false, Shape.L),
            new ShapeMapping(false,Shape.CORNER),
            new ShapeMapping(false, Shape.J),
            new ShapeMapping(false, Shape.I),
            new ShapeMapping(false, Shape.DASH),
            new ShapeMapping(false, Shape.ZIGZAG)
    };

    public Generate2Controller(PresentationController controller, GenerateView2 view) {
        this.controller = controller;
        this.view = view;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(NEXT_AC)) {
            System.out.println("click next");

            List<Class<? extends Operation>> allowedOperations = new ArrayList<>();

            for (OperationMapping mapping : operationMappings) {
                if (mapping.include) {
                    allowedOperations.add(mapping.operation);
                }
            }
            Topology topology = new Topology();
            for (ShapeMapping mapping : shapeMappings) {
                if (mapping.include) {
                    topology.addShape(mapping.shape);
                }
            }
            try {
                controller.showGenerateView3(allowedOperations, topology);
                view.setVisible(false);

            } catch (CannotCreateOperationException | OperandsDoNotMatchException |
                     ShapesAndOperationsDoNotMatchException ex) {
//                System.err.println("Warning: " + );
                JMainOptionPane.showMessageDialog(view, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);

//                throw new RuntimeException(ex);
            }
        } else if (e.getActionCommand().equals(BACK_AC)) {
            System.out.println("click back");
            view.setVisible(false);
            controller.showGenerateView1();
        }
        else if (e.getActionCommand().startsWith(CHECK_IMG_)) {
            String[] parts = e.getActionCommand().split("_");
            int index = Integer.parseInt(parts[2]);
            shapeMappings[index].include = !shapeMappings[index].include;
        }
        else if (e.getActionCommand().startsWith(CHECK_OP_)) {
            String[] parts = e.getActionCommand().split("_");
            int index = Integer.parseInt(parts[2]);
            operationMappings[index].include = !operationMappings[index].include;
        }
    }
}
