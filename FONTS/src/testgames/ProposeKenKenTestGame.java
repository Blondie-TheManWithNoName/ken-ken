package testgames;

import models.ModelController;
import presentation.views.KenKenPlayView;

import javax.swing.*;
import java.util.Scanner;

public class ProposeKenKenTestGame {
	private static final ModelController controller = new ModelController();
	private static final Scanner scanner = new Scanner(System.in);
	private static final String[] symbols = {"+", "-", "*", "/", "lcm", "gcd", "^", "="};

	public static void main(String[] args) {
		int size;
		do {
			System.out.printf("Size of the KenKen (between 2 and %d): ", ModelController.MAX_SIZE);
			try {
				size = Integer.parseInt(scanner.nextLine());
			} catch (NumberFormatException e) {
				size = 0;
			}
		} while (size < 2 || size > ModelController.MAX_SIZE);

		controller.proposeKenKen(size);

		String[] groups;
		int row, col;
		int option, groupIndex;
		do {
			System.out.println("1. Create group\n" +
					"2. Delete group\n" +
					"3. Add cell to group\n" +
					"4. Remove cell from group\n" +
					"5. Generate\n" +
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
					String symbol;
					boolean valid = false;
					do {
						System.out.print("Symbol: ");
						symbol = scanner.nextLine();
						for (String s : symbols)
							if (s.equals(symbol)) {
								valid = true;
								break;
							}
					} while (!valid);

					int target;
					do {
						System.out.print("Target: ");
						try {
							target = Integer.parseInt(scanner.nextLine());
						} catch (NumberFormatException e) {
							target = -1;
						}
					} while (target < 0);

					controller.proposerCreateGroup(symbol, target);
					break;
				case 2:
					groups = controller.proposerGetGroupNotationsEnum();
					if (groups.length == 0) {
						System.out.println("No groups to delete");
						break;
					}

					for (String s : groups) System.out.printf("%s\n", s);
					do {
						System.out.print("Group to delete: ");
						try {
							groupIndex = Integer.parseInt(scanner.nextLine()) - 1;
						} catch (NumberFormatException e) {
							groupIndex = -1;
						}
					} while (groupIndex < 0 || groupIndex >= groups.length);

					controller.proposerDeleteGroup(groupIndex);
					break;
				case 3:
					groups = controller.proposerGetGroupNotationsEnum();
					if (groups.length == 0) {
						System.out.println("No groups to add cell to");
						break;
					}

					do {
						System.out.print("Row: ");
						try {
							row = Integer.parseInt(scanner.nextLine()) - 1;
						} catch (NumberFormatException e) {
							row = -1;
						}
					} while (row < 0 || row >= size);

					do {
						System.out.print("Column: ");
						try {
							col = Integer.parseInt(scanner.nextLine()) - 1;
						} catch (NumberFormatException e) {
							col = -1;
						}
					} while (col < 0 || col >= size);

					for (String group : groups) System.out.printf("%s\n", group);
					do {
						System.out.print("Group to add cell to: ");
						try {
							groupIndex = Integer.parseInt(scanner.nextLine()) - 1;
						} catch (NumberFormatException e) {
							groupIndex = -1;
						}
					} while (groupIndex < 0 || groupIndex >= groups.length);

					controller.proposerAddCellToGroup(row, col, groupIndex);
					break;
				case 4:
					do {
						System.out.print("Row: ");
						try {
							row = Integer.parseInt(scanner.nextLine()) - 1;
						} catch (NumberFormatException e) {
							row = -1;
						}
					} while (row < 0 || row >= size);

					do {
						System.out.print("Column: ");
						try {
							col = Integer.parseInt(scanner.nextLine()) - 1;
						} catch (NumberFormatException e) {
							col = -1;
						}
					} while (col < 0 || col >= size);

					controller.proposerRemoveCellGroup(row, col);
					break;
				case 5:
					if (controller.proposerGenerate()) {
						System.out.println("KenKen generated");
						controller.proposerPlay();
						KenKenPlayView playKenKenView = new KenKenPlayView(controller.getActiveKenKen());
						SwingUtilities.invokeLater(playKenKenView::start);
						option = 0;
					} else
						System.out.println("Error generating KenKen");
					break;
			}
		} while (option != 0);
	}
}
