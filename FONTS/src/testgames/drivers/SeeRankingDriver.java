package testgames.drivers;

import exceptions.CannotLoadScoresException;
import models.ModelController;
import models.Score;

import java.util.List;

public class SeeRankingDriver {
	private final ModelController controller = new ModelController();

	public void seeRanking() throws CannotLoadScoresException {
		List<Score> scores = controller.checkRanking();

		if (scores.isEmpty())
			System.out.println("There are no scores to show.");
		else {
			System.out.println("Ranking:");
			for (int i = 0; i < scores.size(); i++)
				System.out.println((i + 1) + ". " + scores.get(i).getUser() + " - " + scores.get(i).getScore() + " points");
		}
	}
}
