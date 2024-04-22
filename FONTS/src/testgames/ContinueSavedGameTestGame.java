package testgames;

import exceptions.CannotLoadKenKenException;
import models.kenken.KenKen;
import testgames.drivers.LoadSavedGameDriver;
import testgames.drivers.PlayKenKenDriver;

import java.util.Scanner;

public class ContinueSavedGameTestGame {
	private final static LoadSavedGameDriver loadSavedGameDriver = new LoadSavedGameDriver();
	private final static PlayKenKenDriver playKenKenDriver = new PlayKenKenDriver();
	private static final Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		String file;
		KenKen kenKen;

		System.out.println("=== ContinueSavedGameTestGame ===\n");

		while (true) {
			System.out.print("Enter the path of the saved game (the 'data/' folder and '.kenken_game' extension are assumed, just specify the filename): ");
			file = scanner.nextLine();
			try {
				kenKen = loadSavedGameDriver.loadSavedGame("data/" + file + ".kenken_game");
				break;
			} catch (CannotLoadKenKenException e) {
				System.out.println(e.getMessage());
			}
		}

		playKenKenDriver.play(kenKen);
	}
}
