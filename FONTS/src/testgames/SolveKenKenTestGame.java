package testgames;

import exceptions.*;
import models.ModelController;
import models.kenken.KenKen;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class SolveKenKenTestGame {
	private static final ModelController controller = new ModelController();
	private static final Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		try {System.out.println(args[0]);
		KenKen kenken = controller.loadKenKen(args[0]);
		int row, col;
		int option;
		boolean completed = false;
		boolean valid = false;
		do {
			System.out.println(
					"\n1. Put value\n" +
					"2. Delete value\n" +
					"3. Load values from file\n" +
					"4. View values\n" +
					"5. Finish\n" +
					"0. Exit"
			);

			do {
				System.out.print("Option: ");
				try {
					option = Integer.parseInt(scanner.nextLine());
				} catch (NumberFormatException e) {
					option = -1;
				}
			} while (option < 0 || option > 5);

			switch (option) {
				case 1:
					do {
						System.out.print("Row: ");
						try {
							row = Integer.parseInt(scanner.nextLine()) - 1;
						} catch (NumberFormatException e) {
							row = -1;
						}
					} while (row < 0 || row >= kenken.getSize());
					do {
						System.out.print("Column: ");
						try {
							col = Integer.parseInt(scanner.nextLine()) - 1;
						} catch (NumberFormatException e) {
							col = -1;
						}
					} while (col < 0  || col >= kenken.getSize());
					int value;
					do {
						System.out.print("Value: ");
						try {
							value = Integer.parseInt(scanner.nextLine());
						} catch (NumberFormatException e) {
                            value = -1;
                        }
                    } while (value < 0);

					try
					{
						kenken.setPosition(row, col, value);
						System.out.println("Inserted " + value + " on (" +  (row + 1) + ", " + (col + 1) + ")");
					}
					catch (RewriteFixedPositionException | ValueOutOfBoundsException e) {
                       	System.out.println(e.getMessage());
                    }
                    break;
				case 2:
					do {
						System.out.print("Row: ");
						try {
							row = Integer.parseInt(scanner.nextLine()) - 1;
						} catch (NumberFormatException e) {
							row = -1;
						}
					} while (row < 0 || row >= kenken.getSize());
					do {
						System.out.print("Column: ");
						try {
							col = Integer.parseInt(scanner.nextLine()) - 1;
						} catch (NumberFormatException e) {
							col = -1;
						}
					} while (col < 0  || col >= kenken.getSize());
					try {
						kenken.erasePosition(row, col);
						System.out.println("Erased value on (" +  (row + 1) + ", " + (col + 1) + ")");
					} catch (EraseFixedValueException e) {
                        System.out.println(e.getMessage());
                    }
                    break;


				case 3:
					System.out.println("insert file (.values) name within data folder:");
					final String path = scanner.nextLine();
					try {
						BufferedReader reader = new BufferedReader(new FileReader("data/" + path +  ".values"));
						int r = 0, c = 0;
						String sValues;
						while ((sValues = reader.readLine()) != null) {
							String[] values = sValues.split(" ");
							for (String val : values) {
								int v = Integer.parseInt(val);
								System.out.println("Inserting " + v  + " on (" +  (r + 1) + ", " + (c + 1) + ")");
								try
								{
									kenken.setPosition(r, c, v);
								}
								catch (RewriteFixedPositionException | ValueOutOfBoundsException e) {
									System.out.println(e.getMessage());
								}
                                ++c;
								if (c >= kenken.getSize()){
									++r;
									c = 0;
								}
							}

						}
					} catch (IOException e) {
						System.out.println(e.getMessage());
					}
                    break;

				case 4:
					for (int r = 0; r < kenken.getSize(); r++) {
						for (int c = 0; c < kenken.getSize(); c++) {
							System.out.print(kenken.getValue(r, c) + " ");
						}
						System.out.println();
					}
					break;

				case 5:
					try{
						if (kenken.check())
							completed = true;
						else
							System.out.println("KenKen is not yet solved. Keep trying.");

					}
					catch (OperandsDoNotMatchException | NonIntegerResultException e)
					{
						System.out.println(e.getMessage());
					}
					break;

			}
		} while (option != 0 && !completed);

		if (completed)
		{
			System.out.println("Congrats you solved the KenKen!");
		}
		}
		catch (CannotLoadKenKenException e)
		{
			System.out.println(e.getMessage());
		}
	}
}
