package presentation;

import exceptions.*;
import models.ModelController;
import models.Score;
import models.kenken.KenKen;
import models.operations.*;
        import models.topologies.Shape;
import models.topologies.Topology;
import presentation.custom.JKenKenCell;
import presentation.views.*;

        import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import persistence.dto.KenKenDTO;

import javax.swing.*;
import java.io.IOException;

/**
 * The PresentationController class manages the presentation layer of the application,
 * interacting with the user interface and delegating tasks to the DomainController.
 */
public class PresentationController {
    private ModelController mController;
    private final HomeView homeView;
    private final MainMenuView mainMenuView;
    private final RankingView rankingView;
    private ChoosePlayView choosePlayView;
    private ChooseProposeView chooseProposeView;
    private ProposeKenKenView proposeView;
    private final GenerateView1 generateView1;
    private final GenerateView2 generateView2;
    private GenerateView3 generateView3;
    private KenKenPlayView playView;
    private PauseView pauseView;
    private ErrorView errorView;
    private int minutes;
    private int seconds;

    private int size;
    private int fixed;
    private Topology topology;
    private List<Class<? extends Operation>> allowedOperations;

    private static final String SAVE_DIRECTORY = "data/";
    private static final String FILE_EXTENSION = ".kenken_game";

    /**
     * Constructs a PresentationController and initializes various views.
     */
    public PresentationController() {


        mController = new ModelController();
        size = 3;
        fixed = 0;
        initializeKenKenShapesAndOperations();
        homeView = new HomeView(this);
        mainMenuView = new MainMenuView(this);
        rankingView = new RankingView(this);
        choosePlayView = new ChoosePlayView(this);
        chooseProposeView = new ChooseProposeView(this);
        //playView = new KenKenPlayView();
        //loadView = new LoadView();
        //proposeView = new ProposeView();
        generateView1 = new GenerateView1(this);
        generateView2 = new GenerateView2(this);
        //generateView3 = new GenerateView3(this);
        //importView = new importView(this);
        errorView = new ErrorView(this);


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
                        OperationEquality.class
                )
        );
    }

    public void setTime(int minutes, int seconds)
    {
        this.minutes = minutes;
        this.seconds = seconds;
    }

    public int getMinutes(){
        return this.minutes;}
    public int getSeconds(){return this.seconds;}

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
    public void showChoosePlayView() {
        choosePlayView.makeVisible();
    }

    /**
     * Displays the choose view of the application.
     */
    public void showChooseProposeView() {
        chooseProposeView.makeVisible();
    }

    /**
     * Displays the propose view of the application.
     */
    public void showProposeView() {
        JKenKenCell.CELL_SIZE = 25;
        proposeView = new ProposeKenKenView(this, this.size);
        proposeView.makeVisible();
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
        JKenKenCell.FONT_SIZE = (-10/3) * size + 40;
        playView = new KenKenPlayView(this, mController.getActiveKenKen());
        playView.makeVisible();
    }

    public void resumePlayView () throws OperandsDoNotMatchException, ShapesAndOperationsDoNotMatchException, CannotCreateOperationException {
        playView.makeVisible();
    }

    /**
     * Displays the pause view of the application.
     */
    public void showPauseView () {
        pauseView = new PauseView(this, minutes, seconds);
        pauseView.makeVisible();
    }

    /**
     * Displays an error message in the error view.
     *
     * @param eMessage The error message to be displayed.
     */
    public void showErrorView (String eMessage){
        errorView.update(eMessage);
        errorView.makeVisible();
    }

    public boolean checkKenKen(KenKen kenKen)
    {
        mController.setActiveKenKen(kenKen);
        mController.setAiSolve();
        return mController.aiSolve();
    }

    public void setSizeAndFixed ( int size, int fixed){
        this.size = size;
        this.fixed = fixed;
    }

    public void setTopologyAndOperations (List<Class<? extends Operation>> allowedOperations, Topology topology) {
        this.topology = topology;
        this.allowedOperations = allowedOperations;
    }

    /**
     * Method to save the game.
     */
    public void saveGame() {
        try {
            mController.saveGame();
            //nota: hay que modificar savegame del persistence para que reciba una ruta como el load
            showMenuView();
        } catch (IOException e) {
            showErrorView(e.getMessage());
        }
    }

    /**
     * Method to load a saved game.
     * @param path Path to load the saved game.
     */
    public void loadGame(String path) {
        try {
            mController.loadSavedGame(path);
            showPlayView();
        } catch (CannotLoadKenKenException | ShapesAndOperationsDoNotMatchException | CannotCreateOperationException |
                 OperandsDoNotMatchException e) {
            showErrorView(e.getMessage());
            //Nota: si el valor path es null salta excepción pero no hay mensaje.
        }

    }

    public void saveScore(String username) {
        try {
            mController.saveScore(username);
        } catch (InvalidUsernameException | IOException e) {
            showErrorView(e.getMessage());
        }

    }

    public List<Score> getRanking() {
        try {
             return mController.checkRanking();
        } catch (CannotLoadScoresException e) {
            showErrorView(e.getMessage());
        }
        return null;
    }

    /**
     * Method to export a KenKen.
     *
     * @param path Path to export the KenKen.
     */
    public void exportKenKen (String path) {
        try {
            mController.exportKenKen(path);
        } catch (IOException e) {
            showErrorView(e.getMessage());
        }
    }

    /**
     * Method to load a KenKen.
     *
     * @param path Path to load the KenKen.
     */
    public void importKenKen(String path) {
        try {
            mController.loadKenKen(path);
        } catch (CannotLoadKenKenException e) {
            showErrorView(e.getMessage());
        }
    }

    /**
     * Muestra un cuadro de diálogo para guardar un KenKen.
     *
     * @return La ruta completa del archivo donde se guardará el KenKen.
     */
    public String showSaveDialog() {
        String fileName = JOptionPane.showInputDialog(null, "Ingrese el nombre del archivo para guardar:", "Guardar KenKen", JOptionPane.QUESTION_MESSAGE);
        if (fileName != null && !fileName.trim().isEmpty()) {
            return SAVE_DIRECTORY + fileName.trim() + FILE_EXTENSION;
        }
        return null;
    }

    /**
     * Muestra un cuadro de diálogo para cargar un KenKen.
     *
     * @return La ruta completa del archivo que se va a cargar.
     */
    public String showLoadDialog() {
        String fileName = JOptionPane.showInputDialog(null, "Ingrese el nombre del archivo para cargar:", "Cargar KenKen", JOptionPane.QUESTION_MESSAGE);
        if (fileName != null && !fileName.trim().isEmpty()) {
            return SAVE_DIRECTORY + fileName.trim() + FILE_EXTENSION;
        }
        return null;
    }


}