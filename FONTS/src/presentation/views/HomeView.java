package presentation.views;

public class HomeView extends MainView{
    public HomeView() {
        super();
    }

    public void start() {

        //FILA VACIA
        makeSquare("");
        makeSquare("");
        makeSquare("");
        makeSquare("");


        c.gridy = 1;
        makeBackButton();
        makeButtonFirst("NEW");
        c.gridheight = 2;
        makeSquare("<html><p style='margin-bottom: -7;'>GA</p><p style='margin-top: -7;'>ME</p></html>");
        makeSquare("");


        c.gridy = 2;
        c.gridx = 0;
        c.gridheight = 3;
        makeSquare("");;
        c.gridx = 1;
        c.gridheight = 1;		;
        makeButtonSecond("LOAD");

        c.gridy = 3;
        c.gridx = 1;
        c.gridheight = 2;
        makeSquare("<html><p style='margin-bottom: -7;'>KEN</p><p style='margin-top: -7;'>KEN</p></html>");
        c.gridx = 2;
        createPanelWithButtons("");
        c.gridx = 3;
        c.gridheight = 1;
        makeSquare("");


        c.gridy = 4;
        c.gridx = 3;
        c.gridheight = 1;
        makeSquare("");

        //FILA VACIA
        c.gridheight = 1;
        c.gridy = 5;
        c.gridx = 0;
        makeSquare("");
        c.gridx = 1;
        makeSquare("");
        c.gridx = 2;
        makeSquare("");
        c.gridx = 3;
        makeSquare("");

        this.revalidate();

    }
}
