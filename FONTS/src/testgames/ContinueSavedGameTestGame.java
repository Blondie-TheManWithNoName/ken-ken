package testgames;

import exceptions.CannotLoadKenKenException;
import models.kenken.KenKen;
import testgames.drivers.LoadSavedGameDriver;
import testgames.drivers.PlayKenKenDriver;

public class ContinueSavedGameTestGame {
	private final static LoadSavedGameDriver loadSavedGameDriver = new LoadSavedGameDriver();
	private final static PlayKenKenDriver playKenKenDriver = new PlayKenKenDriver();

	public static void main(String[] args) {
		KenKen kenKen;

		System.out.println("=== ContinueSavedGameTestGame ===\n");

		try {
			kenKen = loadSavedGameDriver.loadSavedGame("data/example_path.kenken_game");
			System.out.println("Loaded game from file: data/example_path.kenken_game\n");
			playKenKenDriver.play(kenKen);
		} catch (CannotLoadKenKenException e) {
			System.out.println(e.getMessage());
		}
	}
}
