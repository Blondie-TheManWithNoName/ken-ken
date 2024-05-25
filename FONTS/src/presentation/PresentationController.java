package Presentation;

import presentation.views.*;


/**
 * The PresentationController class manages the presentation layer of the application,
 * interacting with the user interface and delegating tasks to the DomainController.
 */
public class PresentationController {
    // Atributos privados para las diferentes vistas
    private HomeView homeView;
    private MainMenuView mainMenuView;
    //private RankingView rankingView;
    private ChooseView chooseView;
    //private ProposeView proposeView;
    private GenerateView1 generateView;
    //private PlayView playView;
    //private PauseView pauseView;
    //private SolvedView solvedView;
    //private ErrorView errorView;

    /**
     * Constructs a PresentationController and initializes various views.
     */
    public PresentationController() {
        // Inicialización de las vistas
        homeView = new HomeView();
        mainMenuView = new MainMenuView();
        //rankingView = new RankingView();
        chooseView = new ChooseView();
        //proposeView = new ProposeView();
        generateView = new GenerateView1();
        //playView = new PlayView();
        //pauseView = new PauseView();
        //solvedView = new SolvedView();
        //errorView = new ErrorView();
    }

    /**
     * Initializes the presentation and makes the main view visible.
     */
    public void initializePresentation() {
        //homeView.makeVisible();
    }

    /**
     * Displays the main menu view of the application.
     */
    public void showMenuView() {
        //mainMenuView.makeVisible();
    }

    /**
     * Displays the ranking view of the application.
     */
    public void showRankingView() {
        //rankingView.makeVisible();
    }

    /**
     * Displays the choose view of the application.
     */
    public void showChooseView() {
        //chooseView.makeVisible();
    }

    /**
     * Displays the propose view of the application.
     */
    public void showProposeView() {
        //proposeView.makeVisible();
    }

    /**
     * Displays the generate view of the application.
     */
    public void showGenerateView() {
        //generateView.makeVisible();
    }

    /**
     * Displays the play view of the application.
     */
    public void showPlayView() {
        //playView.makeVisible();
    }

    /**
     * Displays the pause view of the application.
     */
    public void showPauseView() {
        //pauseView.makeVisible();
    }

    /**
     * Displays the solved view of the application.
     */
    public void showSolvedView() {
        //solvedView.makeVisible();
    }

    /**
     * Displays an error message in the error view.
     *
     * @param eMessage The error message to be displayed.
     */
    public void showErrorView(String eMessage) {
        //errorView.update(eMessage);
        //errorView.makeVisible();
    }
}
