package presentation.views;

import presentation.PresentationController;
import presentation.controllers.*;

public class RankingView extends MainView{
    private final PresentationController pController;
    private final RankingController controller;


    public RankingView(PresentationController controller) {
        super();
        this.pController = controller;
        this.controller = new RankingController(pController,this);
        start();
    }

    public void start() {
        addEmptyRow(0);
        addSecondRow();
        addThirdRow();
        addEmptyRow(5);

        //this.revalidate();

    }


    private void addEmptyRow(int y) {
        c.gridy = y;
        for (int x = 0; x < 4; x++) {
            c.gridx = x;
            makeSquare("");
        }
    }

    private void addSecondRow() {
        c.gridy = 1;

        c.gridx = 0;
        makeBackButton(controller, RankingController.BACK_AC);

        c.gridx = 1;
        c.gridheight = 2;
        makeSquare("<html><p>RANKING</p></html>");

        c.gridx = 2;
        makeNumber("9");

        c.gridx = 3;
        c.gridheight = 2;
        makeSquare("");

        c.gridy = 2;

        c.gridx = 0;
        c.gridheight = 1;
        makeSquare("");
    }

    private void addThirdRow() {
        c.gridy = 3;

        c.gridx = 0;
        c.gridheight = 2;
        makeSquare("");

        c.gridx = 1;
        makeNumber("6");

        c.gridx = 2;
        createPanelWithButtons("START", false,"RANKING", "EXIT", controller, HomeController.START_KENKEN_AC, HomeController.SEE_RANKING_AC, HomeController.EXIT_AC);

        c.gridx = 3;
        c.gridheight = 1;
        makeSquare("");

        c.gridy = 4;
        makeSquare("");
    }
}
