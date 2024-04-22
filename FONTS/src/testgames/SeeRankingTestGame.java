package testgames;

import exceptions.CannotLoadScoresException;
import testgames.drivers.SeeRankingDriver;

public class SeeRankingTestGame {
	private static final SeeRankingDriver seeRankingDriver = new SeeRankingDriver();

	public static void main(String[] args) {
		System.out.println("=== SeeRankingTestGame ===\n");

		try {
			seeRankingDriver.seeRanking();
		} catch (CannotLoadScoresException e) {
			System.out.println(e.getMessage());
		}
	}
}
