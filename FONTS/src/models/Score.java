package models;

public class Score {
	private final String user;
	private final int score;

	public Score(String user, int score) {
		this.user = user;
		this.score = score;
	}

	public String getUser() {
		return user;
	}

	public int getScore() {
		return score;
	}
}
