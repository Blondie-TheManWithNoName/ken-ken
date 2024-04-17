package testgames;

import exceptions.CannotLoadKenKenException;
import models.kenken.KenKen;
import presentation.views.KenKenView;

import javax.swing.*;
import java.util.Scanner;

public class ProposeKenKenTestGame {
	private static final Driver driver = new Driver("data/proposed_tg.kenken");
	private static final Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		System.out.println("=== ProposeKenKenTestGame ===\n");

		System.out.println("Loading KenKen from data/proposed_tg.kenken...");
		KenKen kenken;
		try {
			kenken = driver.loadKenKen();
		} catch (CannotLoadKenKenException e) {
			System.out.println(e.getMessage());
			return;
		}
		System.out.println("KenKen loaded successfully!\n");

		String response;
		do {
			System.out.print("Do you want to see the KenKen in a Swing view? (y/n): ");
			response = scanner.nextLine();
		} while (!response.equals("y") && !response.equals("n"));
		System.out.println();

		if (response.equals("n")) {
			System.out.println("Exiting...");
			return;
		}

		System.out.println("Showing KenKenView...");

		KenKenView view = new KenKenView(kenken);
		SwingUtilities.invokeLater(view::start);
	}
}
