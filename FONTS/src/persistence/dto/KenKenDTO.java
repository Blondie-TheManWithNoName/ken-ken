package persistence.dto;

import java.util.ArrayList;
import java.util.List;

public class KenKenDTO {
	private final int size;
	private final GroupDTO[] groups;

	public KenKenDTO(int size, int groups) {
		this.size = size;
		this.groups = new GroupDTO[groups];
	}

	public int getSize() {
		return size;
	}

	public GroupDTO[] getGroups() {
		return groups;
	}

	public void addGroup(GroupDTO group) {
		for (int i = 0; i < groups.length; i++) {
			if (groups[i] == null) {
				groups[i] = group;
				return;
			}
		}
	}

	public String[] toLines() {
		List<String> lines = new ArrayList<>();
		lines.add(size + " " + groups.length);
		for (GroupDTO group : groups)
			lines.add(group.toString());
		lines.add("=====");
		for (GroupDTO group : groups)
			for (CellDTO cell : group.getCells())
				if (cell.getValue() != 0)
					lines.add(cell.toFullString());
		return lines.toArray(new String[0]);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(size).append(" ").append(groups.length).append("\n");
		for (GroupDTO group : groups)
			sb.append(group).append("\n");
		return sb.toString();
	}

	public static KenKenDTO fromLines(String[] lines) {
		int i;

		String[] parts = lines[0].split(" ");
		KenKenDTO kenKen = new KenKenDTO(
				Integer.parseInt(parts[0]),
				Integer.parseInt(parts[1])
		);

		for (i = 1; i < lines.length; i++) {
			if (lines[i].equals("====="))
				break;
			parts = lines[i].split(" ");
			OperationDTO operation = new OperationDTO(
					Integer.parseInt(parts[0]),
					Integer.parseInt(parts[1])
			);
			int cellsNumber = Integer.parseInt(parts[2]);
			CellDTO[] cells = new CellDTO[cellsNumber];
			for (int j = 3, k = 0; j < parts.length; j += 2, k++) {
				cells[k] = new CellDTO(
						Integer.parseInt(parts[j]),
						Integer.parseInt(parts[j + 1])
				);
			}
			kenKen.groups[i - 1] = new GroupDTO(operation, cells);
		}

		for (i++; i < lines.length; i++) {
			parts = lines[i].split(" ");
			for (GroupDTO group : kenKen.groups) {
				for (CellDTO cell : group.getCells()) {
					if (cell.getRow() == Integer.parseInt(parts[0]) && cell.getCol() == Integer.parseInt(parts[1])) {
						cell.setValue(Integer.parseInt(parts[2]));
						if (parts.length == 4)
							cell.fixed();
					}
				}
			}
		}

		return kenKen;
	}
}
