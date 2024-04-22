package testgames;

import exceptions.CellAlreadyInGroupException;
import exceptions.CellHasNoGroupException;
import exceptions.GroupCellsNotContiguousException;
import exceptions.TooManyOperandsException;
import models.kenken.KenKen;
import testgames.drivers.*;

import java.io.IOException;
import java.util.Scanner;

public class GenerateKenKenTestGame {
    private static final KenKenProposerDriver proposer = new KenKenProposerDriver();
    private static final GenerateKenKenDriver generateKenKenDriver = new GenerateKenKenDriver();
    private static final PlayKenKenDriver playKenKenDriver = new PlayKenKenDriver();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        KenKen kenken = null;
        int choice;

        System.out.println("=== GenerateKenKenTestGame ===\n");

        System.out.println("What would you like to do with the KenKen?");
        System.out.println("\t1. Generate KenKen");
        System.out.println("\t2. Play");
        System.out.println("\t3. Exit\n");
        do {
            System.out.print("Choice: ");
            try {
                choice = scanner.nextInt();
            } catch (Exception e) {
                choice = 0;
            }
        } while (choice < 1 || choice > 3);

        switch (choice) {
            case 1:
                kenken = generateKenKenDriver.generate();
                break;
            case 2:
                playKenKenDriver.play(kenken);
                break;
            case 3:
                break;
        }

        System.out.println("Exiting...");
    }
}
