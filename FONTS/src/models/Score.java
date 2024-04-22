package models;

/**
 * Score class
 */
public class Score {
	private final String user;
	private final int score;

	/**
	 * Score constructor
	 * @param user Username, cannot contain spaces
	 * @param score Score
	 */
	public Score(String user, int score) {
		this.user = user;
		this.score = score;
	}

	/**
	 * Get username
	 * @return Username
	 */
	public String getUser() {
		return user;
	}

	/**
	 * Get score
	 * @return Score
	 */
	public int getScore() {
		return score;
	}
}
