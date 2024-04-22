package testgames.drivers;

import models.ModelController;
import models.kenken.KenKen;

public class ExportKenKenDriver {
	private final ModelController controller = new ModelController();

	public void export(KenKen kenKen) {
		controller.setActiveKenKen(kenKen);
		controller.exportKenKen("data/exported_tg.kenken");
	}
}
