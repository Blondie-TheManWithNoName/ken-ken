package presentation;

import models.ModelController;
import presentation.views.*;


/**
 * The PresentationController class manages the presentation layer of the application,
 * interacting with the user interface and delegating tasks to the DomainController.
 */
public class PresentationController {
    //private ModelController mController;
    private final HomeView homeView;
    private final MainMenuView mainMenuView;
    private final RankingView rankingView;
    private ChooseView chooseView;
    //private ProposeView proposeView;
    private final GenerateView1 generateView1;
    private final GenerateView2 generateView2;
    //private PlayView playView;
    //private PauseView pauseView;
    //private SolvedView solvedView;
    //private ErrorView errorView;

    /**
     * Constructs a PresentationController and initializes various views.
     */
    public PresentationController() {
        //mController = new ModelController();
        homeView = new HomeView(this);
        mainMenuView = new MainMenuView(this);
        rankingView = new RankingView(this);
        chooseView = new ChooseView(this);
        //playView = new PlayView();
        //loadView = new LoadView();
        //proposeView = new ProposeView();
        generateView1 = new GenerateView1(this);
        generateView2 = new GenerateView2(this);
        //importView = new importView(this);
        //pauseView = new PauseView();
        //solvedView = new SolvedView();
        //errorView = new ErrorView();
    }

    /**
     * Initializes the presentation and makes the main view visible.
     */
    public void showHomeView() {
        homeView.makeVisible();
    }

    /**
     * Displays the main menu view of the application.
     */
    public void showMenuView() {
        mainMenuView.makeVisible();
    }

    /**
     * Displays the ranking view of the application.
     */
    public void showRankingView() {
        rankingView.makeVisible();
    }

    /**
     * Displays the choose view of the application.
     */
    public void showChooseView() {
        chooseView.makeVisible();
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
    public void showGenerateView1() {
        generateView1.makeVisible();
    }

    /**
     * Displays the generate view of the application.
     */
    public void showGenerateView2() {
        generateView2.makeVisible();
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
