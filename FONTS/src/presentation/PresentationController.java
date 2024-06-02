package presentation;

import exceptions.CannotCreateOperationException;
import exceptions.OperandsDoNotMatchException;
import exceptions.ShapesAndOperationsDoNotMatchException;
import models.ModelController;
import models.kenken.KenKen;
import models.kenken.KenKenGenerator;
import models.operations.*;
        import models.topologies.Shape;
import models.topologies.Topology;
import presentation.custom.JKenKenCell;
import presentation.views.*;

        import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * The PresentationController class manages the presentation layer of the application,
 * interacting with the user interface and delegating tasks to the DomainController.
 */
public class PresentationController {
    private ModelController mController;
    private final HomeView homeView;
    private final MainMenuView mainMenuView;
    private final RankingView rankingView;
    private ChooseView chooseView;
    //private ProposeView proposeView;
    private final GenerateView1 generateView1;
    private final GenerateView2 generateView2;
    private GenerateView3 generateView3;
    private KenKenPlayView playView;
    //private PauseView pauseView;
    //private SolvedView solvedView;
    //private ErrorView errorView;

    private int size;
    private int fixed;
    private Topology topology;
    private List<Class<? extends Operation>> allowedOperations;

    /**
     * Constructs a PresentationController and initializes various views.
     */
    public PresentationController() {


        mController = new ModelController();
        homeView = new HomeView(this);
        mainMenuView = new MainMenuView(this);
        rankingView = new RankingView(this);
        chooseView = new ChooseView(this);
//        playView = new KenKenPlayView();
        //loadView = new LoadView();
        //proposeView = new ProposeView();
        generateView1 = new GenerateView1(this);
        generateView2 = new GenerateView2(this);
//        generateView3 = new GenerateView3(this);
        //importView = new importView(this);
        //pauseView = new PauseView();
        //solvedView = new SolvedView();
        //errorView = new ErrorView();
        size = 3;
        fixed = 0;
        initializeKenKenShapesAndOperations();

    }

    public void initializeKenKenShapesAndOperations()
    {
        topology = new Topology();
        topology.addShape(Shape.ZIGZAG);
        topology.addShape(Shape.DASH);
        topology.addShape(Shape.POINT);
        topology.addShape(Shape.L);
        topology.addShape(Shape.I);
        topology.addShape(Shape.BLOCK);
        topology.addShape(Shape.J);
        topology.addShape(Shape.T);

        allowedOperations = new ArrayList<>(
                Arrays.asList(
                        OperationAddition.class,
                        OperationSubtraction.class,
                        OperationMultiplication.class,
                        OperationDivision.class,
                        OperationEquality.class,
                        OperationGCD.class,
                        OperationLCM.class,
                        OperationPower.class
                )
        );
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
     * Displays the generate view of the application.
     */
    public void showGenerateView3(List<Class<? extends Operation>> allowedOperations, Topology topology) throws CannotCreateOperationException, OperandsDoNotMatchException, ShapesAndOperationsDoNotMatchException {

        this.topology = topology;
        this.allowedOperations = allowedOperations;
        if (mController.generateKenKen(9, 0, topology, allowedOperations)) {

            generateView3 = new GenerateView3(this);
            generateView3.makeVisible();
        }
    }


    public KenKen generateKenKen() throws CannotCreateOperationException, OperandsDoNotMatchException, ShapesAndOperationsDoNotMatchException {
        mController.generateKenKen(this.size, this.fixed, topology, allowedOperations);
        return mController.getActiveKenKen();
    }

    /**
     * Displays the play view of the application.
     */
    public void showPlayView () throws OperandsDoNotMatchException, ShapesAndOperationsDoNotMatchException, CannotCreateOperationException {
        JKenKenCell.CELL_SIZE = 75;
        playView = new KenKenPlayView(mController.getActiveKenKen());
        playView.makeVisible();


    }

    /**
     * Displays the play view of the application from generated kenken.
     */
    public void showPlayViewGenerate () throws
    OperandsDoNotMatchException, ShapesAndOperationsDoNotMatchException, CannotCreateOperationException {
        //Puedes utilizar esta funcion para guardar el kenken generado en el presentation controller y hacer un show play view ya que cuando hagamos el play
        //random se tendra que utilizar una topologia random y operaciones random y utilizaremos el kenken
    }

    /**
     * Displays the pause view of the application.
     */
    public void showPauseView () {
        //pauseView.makeVisible();
    }

    /**
     * Displays the solved view of the application.
     */
    public void showSolvedView () {
        //solvedView.makeVisible();
    }

    /**
     * Displays an error message in the error view.
     *
     * @param eMessage The error message to be displayed.
     */
    public void showErrorView (String eMessage){
        //errorView.update(eMessage);
        //errorView.makeVisible();
    }

    public void setSizeAndFixed ( int size, int fixed){
        this.size = size;
        this.fixed = fixed;
    }

    public void setTopologyAndOperations (List<Class<? extends Operation>> allowedOperations, Topology topology) {
        this.topology = topology;
        this.allowedOperations = allowedOperations;
    }
}