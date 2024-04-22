package testgames.drivers;

import models.ModelController;
import models.kenken.KenKen;

public class AiSolveDriver {
	private final ModelController controller = new ModelController();

	public void solve(KenKen kenKen) {
		controller.setActiveKenKen(kenKen);
		controller.setAiSolve();

		if (controller.aiSolve())
			System.out.println("AI has solved the KenKen!");
		else
			System.out.println("AI could not solve the KenKen...");
	}
}
