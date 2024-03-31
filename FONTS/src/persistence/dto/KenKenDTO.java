package persistence.dto;

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

	public static KenKenDTO fromLines(String[] lines) {
		String[] parts = lines[0].split(" ");
		KenKenDTO kenKen = new KenKenDTO(
				Integer.parseInt(parts[0]),
				Integer.parseInt(parts[1])
		);

		for (int i = 1; i < lines.length; i++) {
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

		return kenKen;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(size).append(" ").append(groups.length).append("\n");
		for (GroupDTO group : groups)
			sb.append(group).append("\n");
		return sb.toString();
	}
}
