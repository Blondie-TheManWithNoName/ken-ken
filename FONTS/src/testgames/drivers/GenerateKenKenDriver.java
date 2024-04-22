package testgames.drivers;

import exceptions.*;
import models.ModelController;
import models.kenken.KenKen;
import models.operations.*;
import models.topologies.Shape;
import models.topologies.Topology;
import models.kenken.KenKenSolver;
import presentation.views.KenKenSolverView;
import presentation.views.KenKenView;

import javax.swing.*;
import java.util.List;
import java.util.Scanner;

public class GenerateKenKenDriver {
    private static final Shape[] shapes = {};
    private final ModelController controller = new ModelController();
    private final Scanner scanner = new Scanner(System.in);

    private int choice;
    private int size;
    private int fixedValues;
    private Topology topology;
    private List<Class<? extends Operation>> allowedOperations;

    public KenKen generate() {

        parametersKenKen();

        do {
            choice = 0;
            System.out.println("What would you like to do?");
            System.out.println("\t1. Generate KenKen");
            System.out.println("\t2. Change parameters");
            System.out.println("\t3. View KenKen");
            System.out.println("\t3. Use KenKen");
            System.out.println("\t4. Exit\n");

            do {
                System.out.print("Choice: ");
                try {
                    choice = scanner.nextInt();
                } catch (Exception e) {
                    choice = 0;
                }
            } while (choice < 1 || choice > 4);

            switch (choice) {
                case 1:
                    try {
                        if (controller.generateKenKen(size, fixedValues, topology, allowedOperations)) {
                            System.out.println("KenKen generated successfully.");
                        } else {
                            System.out.println("Failed to generate KenKen.");
                        }
                    } catch (OperandsDoNotMatchException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 2:
                    parametersKenKen();
                    break;
                case 3:
                    //controller.generatorPlay();
                    //KenKenView view = new KenKenSolverView(new KenKenSolver(controller.getActiveKenKen()));
                    //SwingUtilities.invokeLater(view::start);
                    break;
                case 4:
                    return controller.getActiveKenKen();
                    //break;
            }
        } while (choice != 4);

        System.out.println("Exiting KenKen generator...");
        System.exit(0);

        return null;
    }

    public void parametersKenKen() {
        do {
            System.out.printf("Enter the size of the KenKen (between 2 and %d): ", ModelController.MAX_SIZE);
            try {
                size = scanner.nextInt();
            } catch (Exception e) {
                size = 0;
            }
        } while (size < 2 || size > ModelController.MAX_SIZE);

        do {
            System.out.printf("Enter the number of fixed values of the KenKen (between 2 and %d): ", (size * size - 1));
            try {
                fixedValues = scanner.nextInt();
            } catch (Exception e) {
                fixedValues = 0;
            }
        } while (fixedValues < 0 || fixedValues > (size * size - 1));

        do {
            choice = 0;
            System.out.print("Enter the topology of the KenKen: ");
            System.out.println("\t1. BLOCK");
            System.out.println("\t2. J");
            System.out.println("\t3. L");
            System.out.println("\t4. T");
            System.out.println("\t5. I");
            System.out.println("\t6. DASH");
            System.out.println("\t7. POINT");


            System.out.print("Choice: ");
            try {
                choice = scanner.nextInt();
            } catch (Exception e) {
                choice = 0;
            }

            topology = new Topology(Shape, Shape.values[choice]);

        } while (choice < 1 || choice > 7);

        do {
            choice = 0;
            System.out.print("Enter the allowed operations of the KenKen: ");
            System.out.println("\t1. Addition (+)");
            System.out.println("\t2. Subtraction (-)");
            System.out.println("\t3. Multiplication (*)");
            System.out.println("\t4. Division(/)");
            System.out.println("\t5. Greatest Common Divisor (GCD)");
            System.out.println("\t6. Power (^)");
            System.out.println("\t7. Finish");

            do {
                System.out.print("Choice: ");
                try {
                    choice = scanner.nextInt();
                } catch (Exception e) {
                    choice = 0;
                }
            } while (choice < 1 || choice > 7);

            switch (choice) {
                case 1:
                    allowedOperations.add(OperationAddition.class);
                    break;
                case 2:
                    allowedOperations.add(OperationSubtraction.class);
                    break;
                case 3:
                    allowedOperations.add(OperationMultiplication.class);
                    break;
                case 4:
                    allowedOperations.add(OperationDivision.class);
                    break;
                case 5:
                    allowedOperations.add(OperationGCD.class);
                    break;
                case 6:
                    allowedOperations.add(OperationPower.class);
                    break;
            }
        } while (choice != 7);
    }
}
