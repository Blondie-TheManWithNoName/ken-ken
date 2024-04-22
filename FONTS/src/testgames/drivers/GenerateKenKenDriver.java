package testgames.drivers;

import exceptions.*;
import models.ModelController;
import models.kenken.KenKen;
import models.operations.*;
import models.topologies.Shape;
import models.topologies.Topology;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class GenerateKenKenDriver {
    private static final Shape[] shapes = Shape.values();
    private static final Map<String, Class<? extends Operation>> operations = Map.of(
            "+", OperationAddition.class,
            "-", OperationSubtraction.class,
            "*", OperationMultiplication.class,
            "/", OperationDivision.class,
            "gcd", OperationGCD.class,
            "lcm", OperationLCM.class,
            "^", OperationPower.class,
            "=", OperationEquality.class
    );

    private final ModelController controller = new ModelController();
    private final Scanner scanner = new Scanner(System.in);

    public KenKen generate() throws OperandsDoNotMatchException {
        parametersKenKen();

        return controller.getActiveKenKen();
    }

    public void parametersKenKen() throws OperandsDoNotMatchException {
        int size, fixedValues, choice;
        Topology topology;
        List<String> operationSymbols = new ArrayList<>(operations.keySet());
        List<Class<? extends Operation>> allowedOperations = new ArrayList<>();

        do {
            System.out.printf("Enter the size of the KenKen (between 2 and %d): ", ModelController.MAX_SIZE);
            try {
                size = scanner.nextInt();
            } catch (Exception e) {
                size = 0;
            }
        } while (size < 2 || size > ModelController.MAX_SIZE);

        do {
            System.out.printf("Enter the number of fixed values of the KenKen (between 0 and %d): ", (size * size - 1));
            try {
                fixedValues = scanner.nextInt();
            } catch (Exception e) {
                fixedValues = -1;
            }
        } while (fixedValues < 0 || fixedValues > (size * size - 1));

        System.out.print("Possible topologies: ");
        for (int i = 0; i < shapes.length; i++) {
            System.out.printf("\n\t%d.", i + 1);
            for (int[] row : shapes[i].getShape()) {
                System.out.print("\n\t\t");
                for (int cell : row)
                    System.out.print(cell == 1 ? "X" : " ");
            }
        }
        System.out.println("\n");

        do {
            System.out.print("Choice: ");
            try {
                choice = scanner.nextInt();
            } catch (Exception e) {
                choice = 0;
            }
        } while (choice < 1 || choice > shapes.length);

        topology = new Topology(shapes[choice - 1]);

        System.out.print("Possible KenKen Operations: ");
        for (int i = 0; i < operationSymbols.size(); i++)
            System.out.printf("\n\t%d. %s", i + 1, operationSymbols.get(i));
        System.out.println("\n");

        do {
            System.out.print("Choice (0 to proceed): ");
            try {
                choice = scanner.nextInt();
                if (choice > 0 && choice <= operationSymbols.size())
                    allowedOperations.add(operations.get(operationSymbols.get(choice - 1)));
            } catch (Exception e) {
                choice = -1;
            }
        } while (choice != 0);

        controller.generateKenKen(size, fixedValues, topology, allowedOperations);
    }
}
