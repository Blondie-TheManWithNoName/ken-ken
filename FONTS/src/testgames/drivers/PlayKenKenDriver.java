package testgames.drivers;

import exceptions.EraseFixedValueException;
import exceptions.InvalidUsernameException;
import exceptions.RewriteFixedPositionException;
import exceptions.ValueOutOfBoundsException;
import models.ModelController;
import models.kenken.KenKen;

import java.io.IOException;
import java.util.Scanner;

public class PlayKenKenDriver {
	private final ModelController controller = new ModelController();
	private final Scanner scanner = new Scanner(System.in);
	private int size;

	public void play(KenKen kenKen) {
		this.size = kenKen.getSize();
		controller.setActiveKenKen(kenKen);
		play();
	}

	public void play() {
		String username;
		int choice;

		System.out.println("=== PlayKenKenDriver ===\n");

		while (true) {
			System.out.println("What would you like to do?");
			System.out.println("\t1. Make a move");
			System.out.println("\t2. Check");
			System.out.println("\t3. Save and exit");
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
					askForMove();
					break;
				case 2:
					if (controller.check()) {
						System.out.println("Congratulations! You solved the KenKen!");
						while (true) {
							System.out.print("Enter your username (no spaces): ");
							username = scanner.next();
							try {
								controller.saveScore(username);
								exit();
							} catch (InvalidUsernameException e) {
								System.out.println(e.getMessage());
							} catch (IOException e) {
								exit();
							}
						}
					} else {
						System.out.println("Not yet! Keep going!");
					}
					break;
				case 3:
					try {
						controller.saveGame();
					} catch (IOException e) {
						System.out.println(e.getMessage());
					}
				case 4:
					exit();
			}
		}
	}

	private void askForMove() {
		int row, col, value;

		do {
			System.out.print("Row: ");
			try {
				row = scanner.nextInt();
			} catch (Exception e) {
				row = 0;
			}
		} while (row < 1 || row > size);

		do {
			System.out.print("Column: ");
			try {
				col = scanner.nextInt();
			} catch (Exception e) {
				col = 0;
			}
		} while (col < 1 || col > size);

		do {
			System.out.print("Value (0 to erase): ");
			try {
				value = scanner.nextInt();
			} catch (Exception e) {
				value = -1;
			}
		} while (value < 0 || value > size);

		try {
			controller.makeMove(row - 1, col - 1, value);
		} catch (EraseFixedValueException | RewriteFixedPositionException | ValueOutOfBoundsException e) {
			System.out.println(e.getMessage());
		}
	}

	private void exit() {
		System.out.println("Exiting...");
		System.exit(0);
	}
}
