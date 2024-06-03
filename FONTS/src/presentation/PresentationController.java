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

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.IOException;
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
    private int hints;

    private int size;
    private int fixed;
    private Topology topology;
    private List<Class<? extends Operation>> allowedOperations;
    private int hints;

    private static final String SAVE_DIRECTORY = "data/";
    private static final String GAME_EXTENSION = "kenken_game";
    private static final String KENKEN_EXTENSION = "kenken";

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
        generateView1 = new GenerateView1(this);
        generateView2 = new GenerateView2(this);
        errorView = new ErrorView(this);
    }

    public void initializeKenKenShapesAndOperations() {
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

    public void setTime(int minutes, int seconds) {
        this.minutes = minutes;
        this.seconds = seconds;
    }
    public void setHints(int hints) {
        this.hints = hints;
    }

    public int getMinutes() {
        return this.minutes;
    }

    public int getSeconds() {
        return this.seconds;
    }

    public int getTopologySize() {
        return this.topology.getSize();
    }

    public int getOperationsSize() {
        return this.allowedOperations.size();
    }

    public int getFixed() {
        return this.fixed;
    }

    public int getHints() {
        return this.hints;
    }

    /**
     * Initializes the presentation and makes the home view visible.
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
        rankingView.populateRanking();
        rankingView.makeVisible();
    }

    /**
     * Displays the choose play view of the application.
     */
    public void showChoosePlayView() {
        choosePlayView.makeVisible();
    }

    /**
     * Displays the choose propose view of the application.
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
     * Displays the first generate view of the application.
     */
    public void showGenerateView1() {
        generateView1.makeVisible();
    }

    /**
     * Displays the second generate view of the application.
     */
    public void showGenerateView2() {
        generateView2.makeVisible();
    }

    /**
     * Displays the third generate view of the application.
     */
    public void showGenerateView3(List<Class<? extends Operation>> allowedOperations, Topology topology) throws CannotCreateOperationException, OperandsDoNotMatchException, ShapesAndOperationsDoNotMatchException {
        this.topology = topology;
        this.allowedOperations = allowedOperations;
        if (mController.generateKenKen(9, 0, topology, allowedOperations)) {
            generateView3 = new GenerateView3(this);
            generateView3.makeVisible();
        }
    }

    /**
     * Generates a KenKen puzzle.
     *
     * @return The generated KenKen puzzle.
     * @throws CannotCreateOperationException       If the operation cannot be created.
     * @throws OperandsDoNotMatchException          If operands do not match.
     * @throws ShapesAndOperationsDoNotMatchException If shapes and operations do not match.
     */
    public KenKen generateKenKen() throws CannotCreateOperationException, OperandsDoNotMatchException, ShapesAndOperationsDoNotMatchException {
        boolean g = false;
        do {
            g = mController.generateKenKen(this.size, this.fixed, topology, allowedOperations);
        } while (!g);
        return mController.getActiveKenKen();
    }

    /**
     * Displays the play view of the application.
     *
     * @throws OperandsDoNotMatchException          If operands do not match.
     * @throws ShapesAndOperationsDoNotMatchException If shapes and operations do not match.
     * @throws CannotCreateOperationException       If the operation cannot be created.
     */
    public void showPlayView() throws OperandsDoNotMatchException, ShapesAndOperationsDoNotMatchException, CannotCreateOperationException {
        JKenKenCell.CELL_SIZE = 75;
        JKenKenCell.FONT_SIZE = (-10 / 3) * size + 40;
        playView = new KenKenPlayView(this, mController.getActiveKenKen());
        playView.makeVisible();
    }

    /**
     * Resumes the play view of the application.
     *
     * @throws OperandsDoNotMatchException          If operands do not match.
     * @throws ShapesAndOperationsDoNotMatchException If shapes and operations do not match.
     * @throws CannotCreateOperationException       If the operation cannot be created.
     */
    public void resumePlayView() throws OperandsDoNotMatchException, ShapesAndOperationsDoNotMatchException, CannotCreateOperationException {
        playView.makeVisible();
    }

    /**
     * Displays the pause view of the application.
     */
    public void showPauseView() {
        pauseView = new PauseView(this, minutes, seconds);
        pauseView.makeVisible();
    }

    /**
     * Displays an error message in the error view.
     *
     * @param eMessage The error message to be displayed.
     */
    public void showErrorView(String eMessage) {
        errorView.update(eMessage);
        errorView.makeVisible();
    }

    /**
     * Checks if the KenKen puzzle is valid.
     *
     * @param kenKen The KenKen puzzle to be checked.
     * @return True if the KenKen puzzle is valid, otherwise false.
     */
    public boolean checkKenKen(KenKen kenKen) {
        mController.setActiveKenKen(kenKen);
        mController.setAiSolve();
        return mController.aiSolve();
    }

    /**
     * Sets the size and fixed number of the KenKen puzzle.
     *
     * @param size  The size of the KenKen puzzle.
     * @param fixed The fixed number of the KenKen puzzle.
     */
    public void setSizeAndFixed(int size, int fixed) {
        this.size = size;
        this.fixed = fixed;
    }

    /**
     * Sets the topology and operations for the KenKen puzzle.
     *
     * @param allowedOperations The allowed operations for the KenKen puzzle.
     * @param topology          The topology for the KenKen puzzle.
     */
    public void setTopologyAndOperations(List<Class<? extends Operation>> allowedOperations, Topology topology) {
        this.topology = topology;
        this.allowedOperations = allowedOperations;
    }

    /**
     * Method to save the game.
     *
     * @param path Path to save the game.
     */
    public void saveGame(String path) {
        try {
            mController.saveGame(path);
            showMenuView();
        } catch (IOException e) {
            showErrorView(e.getMessage());
        }
    }

    /**
     * Method to load a saved game.
     *
     * @param path Path to load the saved game.
     */
    public void loadGame(String path
    ) {
        try {
            mController.loadSavedGame(path);
            showPlayView();
        } catch (CannotLoadKenKenException | ShapesAndOperationsDoNotMatchException | CannotCreateOperationException |
                 OperandsDoNotMatchException e) {
            showErrorView(e.getMessage());
            // Note: If the path value is null, it throws an exception but there's no message.
        }
    }

    /**
     * Saves the score for the current game.
     *
     * @param username The username associated with the score.
     */
    public void saveScore(String username) {
        try {

            mController.saveScore(username, getTopologySize(), getOperationsSize(), getMinutes(), getSeconds(), getFixed(), getHints());
            showMenuView();
        } catch (InvalidUsernameException | IOException e) {
            showErrorView(e.getMessage());
        }

    }

    /**
     * Retrieves the ranking of scores.
     *
     * @return The list of scores representing the ranking.
     */
    public List<Score> getRanking() {
        try {
            return mController.checkRanking();
        } catch (CannotLoadScoresException e) {
            showErrorView(e.getMessage());
        }
        return null;
    }

    /**
     * Method to export a KenKen puzzle.
     *
     * @param path Path to export the KenKen puzzle.
     */
    public void exportKenKen(String path) {
        try {
            mController.exportKenKen(path);
            showMenuView();
        } catch (IOException e) {
            showErrorView(e.getMessage());
        }
    }

    /**
     * Method to import a KenKen puzzle.
     *
     * @param path Path to import the KenKen puzzle.
     */
    public void importKenKen(String path) {
        try {
            mController.loadKenKen(path);
            showPlayView();
        } catch (CannotLoadKenKenException | ShapesAndOperationsDoNotMatchException | CannotCreateOperationException |
                 OperandsDoNotMatchException e) {
            showErrorView(e.getMessage());
        }
    }

    /**
     * Displays a dialog box to save a KenKen puzzle.
     *
     * @return The full path of the file where the KenKen puzzle will be saved.
     */
    public String showSaveDialog() {
        JFileChooser fileChooser = new JFileChooser(new File(SAVE_DIRECTORY));
        fileChooser.setDialogTitle("Save KenKen");

        FileNameExtensionFilter filter = new FileNameExtensionFilter("KenKen files (*." + GAME_EXTENSION + ")", GAME_EXTENSION);
        fileChooser.setFileFilter(filter);

        int result = fileChooser.showSaveDialog(pauseView);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            String fileName = selectedFile.getName();
            if (!fileName.trim().isEmpty()) {
                String filePath = selectedFile.getAbsolutePath();
                if (!filePath.endsWith("." + GAME_EXTENSION)) {
                    filePath += "." + GAME_EXTENSION;
                }
                return filePath;
            }
        }
        return null;
    }

    /**
     * Displays a dialog box to load a KenKen puzzle.
     *
     * @return The full path of the file to be loaded.
     */
    public String showLoadDialog() {
        JFileChooser fileChooser = new JFileChooser(new File(SAVE_DIRECTORY));
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setDialogTitle("Select the file to load");

        FileNameExtensionFilter filter = new FileNameExtensionFilter("KenKen files (*." + GAME_EXTENSION + ")", GAME_EXTENSION);
        fileChooser.setFileFilter(filter);

        int result = fileChooser.showOpenDialog(mainMenuView);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            return selectedFile.getAbsolutePath();
        }
        return null;
    }

    /**
     * Displays a dialog box to export a KenKen puzzle.
     *
     * @return The full path of the file where the KenKen puzzle will be exported.
     */
    public String showExportDialog() {
        JFileChooser fileChooser = new JFileChooser(new File(SAVE_DIRECTORY));
        fileChooser.setDialogTitle("Export KenKen");

        FileNameExtensionFilter filter = new FileNameExtensionFilter("KenKen files (*." + GAME_EXTENSION + ")", GAME_EXTENSION);
        fileChooser.setFileFilter(filter);

        int result = fileChooser.showSaveDialog(pauseView);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            String fileName = selectedFile.getName();
            if (!fileName.trim().isEmpty()) {
                String filePath = selectedFile.getAbsolutePath();
                if (!filePath.endsWith("." + KENKEN_EXTENSION)) {
                    filePath += "." + KENKEN_EXTENSION;
                }
                return filePath;
            }
        }
        return null;
    }

    /**
     * Displays a dialog box to import a KenKen puzzle.
     *
     * @return The full path of the file to be imported.
     */
    public String showImportDialog() {
        JFileChooser fileChooser = new JFileChooser(new File(SAVE_DIRECTORY));
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setDialogTitle("Select the file to import");

        FileNameExtensionFilter filter = new FileNameExtensionFilter("KenKen files (*." + GAME_EXTENSION + ")", GAME_EXTENSION);
        fileChooser.setFileFilter(filter);

        int result = fileChooser.showOpenDialog(mainMenuView);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            return selectedFile.getAbsolutePath();
        }
        return null;
    }
    /**
     * Displays a dialog box to save a score.
     *
     * @return The full path of the file to be imported.
     */

    public void showSaveScoreDialog() {
        String user = JOptionPane.showInputDialog(null, "Enter your username:", "Save Score", JOptionPane.QUESTION_MESSAGE);
        saveScore(user);

        
    }
}
