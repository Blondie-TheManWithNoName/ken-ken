package testgames.drivers;

import models.ModelController;
import models.kenken.KenKen;

import java.io.IOException;

public class ExportKenKenDriver {
	private final ModelController controller = new ModelController();

	public void export(KenKen kenKen) throws IOException {
		controller.setActiveKenKen(kenKen);
		controller.exportKenKen("data/exported_tg.kenken");
	}
}
