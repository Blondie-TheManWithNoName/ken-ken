package testgames.drivers;

import exceptions.CannotLoadKenKenException;
import models.ModelController;
import models.kenken.KenKen;

public class LoadKenKenDriver {
	private final ModelController controller = new ModelController();
	private final String path;

	public LoadKenKenDriver(String path) {
		this.path = path;
	}

	public KenKen loadKenKen() throws CannotLoadKenKenException {
		return controller.loadKenKen(path);
	}
}
