package testgames;

import exceptions.CannotLoadKenKenException;
import models.kenken.KenKen;
import testgames.drivers.LoadKenKenDriver;
import testgames.drivers.PlayKenKenDriver;

public class LoadKenKenTestGame {
	private static final LoadKenKenDriver loadKenKenDriver = new LoadKenKenDriver("data/load_tg.kenken");
	private static final PlayKenKenDriver playKenKenDriver = new PlayKenKenDriver();

	public static void main(String[] args) {
		KenKen kenken;

		System.out.println("=== LoadKenKenTestGame ===\n");

		System.out.println("Loading KenKen from 'data/load_tg.kenken'...");
		try {
			kenken = loadKenKenDriver.loadKenKen();
		} catch (CannotLoadKenKenException e) {
			System.out.println(e.getMessage());
			return;
		}
		System.out.println("KenKen loaded successfully!\n");

		System.out.println("Playing KenKen...\n");
		playKenKenDriver.play(kenken);
	}
}
