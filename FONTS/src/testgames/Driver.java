package testgames;

import exceptions.CannotLoadKenKenException;
import models.ModelController;
import models.kenken.KenKen;

public class Driver {
	private final String path;
	private final ModelController controller = new ModelController();

	public Driver(String path) {
		this.path = path;
	}

	public KenKen loadKenKen() throws CannotLoadKenKenException {
		return controller.loadKenKen(path);
	}
}
