package persistence.dto;

public class GroupDTO {
	private final OperationDTO operation;
	private final CellDTO[] cells;

	public GroupDTO(OperationDTO operation, CellDTO[] cells) {
		this.operation = operation;
		this.cells = cells;
	}

	public OperationDTO getOperation() {
		return operation;
	}

	public CellDTO[] getCells() {
		return cells;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(operation).append(" ").append(cells.length).append(" ");
		for (CellDTO cell : cells)
			sb.append(cell).append(" ");
		return sb.toString();
	}
}
