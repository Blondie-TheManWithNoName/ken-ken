package persistence.dto;

public class ScoreDTO {
	private String user;
	private int score;

	public ScoreDTO(String user, int score) {
		this.user = user;
		this.score = score;
	}

	public String getUser() {
		return user;
	}

	public int getScore() {
		return score;
	}

	public static ScoreDTO fromLine(String line) {
		String[] parts = line.split(" ");
		return new ScoreDTO(parts[0], Integer.parseInt(parts[1]));
	}

	@Override
	public String toString() {
		return user + " " + score;
	}
}
