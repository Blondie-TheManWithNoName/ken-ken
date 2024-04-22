package testgames;

import models.kenken.KenKen;
import testgames.drivers.AiSolveDriver;
import testgames.drivers.ExportKenKenDriver;
import testgames.drivers.KenKenProposerDriver;
import testgames.drivers.PlayKenKenDriver;

import java.io.IOException;
import java.util.Scanner;

public class ProposeKenKenTestGame {
	private static final KenKenProposerDriver proposer = new KenKenProposerDriver();
	private static final AiSolveDriver aiSolveDriver = new AiSolveDriver();
	private static final ExportKenKenDriver exportKenKenDriver = new ExportKenKenDriver();
	private static final PlayKenKenDriver playKenKenDriver = new PlayKenKenDriver();
	private static final Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		KenKen kenken;
		int choice;

		System.out.println("=== ProposeKenKenTestGame ===\n");

		kenken = proposer.propose();

		System.out.println("What would you like to do with the KenKen?");
		System.out.println("\t1. AI Solve");
		System.out.println("\t2. Export");
		System.out.println("\t3. Play");
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
				aiSolveDriver.solve(kenken);
				break;
			case 2:
				try {
					exportKenKenDriver.export(kenken);
				} catch (IOException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 3:
				playKenKenDriver.play(kenken);
				break;
			case 4:
				break;
		}

		System.out.println("Exiting...");
	}
}
