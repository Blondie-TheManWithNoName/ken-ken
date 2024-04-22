package testgames.drivers;

import exceptions.*;
import models.ModelController;
import models.kenken.Group;
import models.kenken.KenKen;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class KenKenProposerDriver {
	private static final String[] symbols = {"+", "-", "*", "/", "lcm", "gcd", "^", "="};

	private final ModelController controller = new ModelController();
	private final Scanner scanner = new Scanner(System.in);

	public KenKen propose() {
		int size, choice, target, row, col, groupIndex;
		String symbol;
		boolean valid;
		List<Group> groups = new ArrayList<>();

		do {
			System.out.printf("Enter the size of the KenKen (between 2 and %d): ", ModelController.MAX_SIZE);
			try {
				size = scanner.nextInt();
			} catch (Exception e) {
				size = 0;
			}
		} while (size < 2 || size > ModelController.MAX_SIZE);

		controller.proposeKenKen(size);

		do {
			System.out.println("What would you like to do?");
			System.out.println("\t1. Create group");
			System.out.println("\t2. Delete group");
			System.out.println("\t3. Add cell to group");
			System.out.println("\t4. Remove cell from group");
			System.out.println("\t5. Generate KenKen");
			System.out.println("\t6. Exit\n");

			do {
				System.out.print("Choice: ");
				try {
					choice = scanner.nextInt();
				} catch (Exception e) {
					choice = 0;
				}
			} while (choice < 1 || choice > 6);

			switch (choice) {
				case 1:
					valid = false;
					do {
						System.out.print("Symbol: ");
						symbol = scanner.nextLine();
						for (String s : symbols)
							if (s.equals(symbol)) {
								valid = true;
								break;
							}
					} while (!valid);

					do {
						System.out.print("Target: ");
						try {
							target = scanner.nextInt();
						} catch (Exception e) {
							target = -1;
						}
					} while (target < 0);

					groups.add(controller.proposerCreateGroup(symbol, target));
					break;
				case 2:
					if (groups.isEmpty()) {
						System.out.println("No groups to delete");
						break;
					}

					for (Group g : groups)
						System.out.printf("%s\n", g.getNotation());

					do {
						System.out.print("Group to delete: ");
						try {
							groupIndex = Integer.parseInt(scanner.nextLine()) - 1;
						} catch (NumberFormatException e) {
							groupIndex = -1;
						}
					} while (groupIndex < 0 || groupIndex >= groups.size());

					controller.proposerDeleteGroup(groups.get(groupIndex));
					groups.remove(groupIndex);
					break;
				case 3:
					if (groups.isEmpty()) {
						System.out.println("No groups to add any cell to");
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

					for (Group g : groups)
						System.out.printf("%s\n", g.getNotation());

					do {
						System.out.print("Group to add cell to: ");
						try {
							groupIndex = Integer.parseInt(scanner.nextLine()) - 1;
						} catch (NumberFormatException e) {
							groupIndex = -1;
						}
					} while (groupIndex < 0 || groupIndex >= groups.size());

					try {
						controller.proposerAddCellToGroup(row, col, groups.get(groupIndex));
					} catch (GroupDoesNotExistException e) {
						System.out.println(e.getMessage());
					}
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
					try {
						return controller.proposerGenerate();
					} catch (CellAlreadyInGroupException | TooManyOperandsException | CellHasNoGroupException |
							 GroupCellsNotContiguousException e) {
						System.out.println(e.getMessage());
					}
					break;
			}
		} while (choice != 6);

		System.out.println("Exiting KenKen proposer...");
		System.exit(0);

		return null;
	}
}
