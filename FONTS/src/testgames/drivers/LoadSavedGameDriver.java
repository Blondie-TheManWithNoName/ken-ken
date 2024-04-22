package testgames.drivers;

import exceptions.CannotLoadKenKenException;
import models.ModelController;
import models.kenken.KenKen;

public class LoadSavedGameDriver {
	private final ModelController controller = new ModelController();

	public KenKen loadSavedGame(String path) throws CannotLoadKenKenException {
		return controller.loadSavedGame(path);
	}
}
