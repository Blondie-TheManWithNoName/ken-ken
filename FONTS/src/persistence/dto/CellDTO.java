package persistence.dto;

public class CellDTO {
	private final int row;
	private final int col;
	private boolean fixed;
	private int value;

	public CellDTO(int row, int col) {
		this.row = row;
		this.col = col;
	}

	public CellDTO(int row, int col, boolean fixed, int value) {
		this.row = row;
		this.col = col;
		this.fixed = fixed;
		this.value = value;
	}

	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}

	public boolean isFixed() {
		return fixed;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public void fixed() {
		this.fixed = true;
	}

	@Override
	public String toString() {
		return row + " " + col;
	}

	public String toFullString() {
		return row + " " + col + " " + value + " " + (fixed ? "!" : "");
	}
}
