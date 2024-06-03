package models;

import exceptions.*;
import models.kenken.*;
import models.operations.Operation;
import models.operations.OperationFactory;
import models.topologies.Topology;
import persistence.PersistenceController;
import persistence.dto.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**~~
 * ModelController class.
 * <p>
 * Controller class that manages the model of the application.
 * It is responsible for the communication between the view and the model.
 * It contains the main methods that the view can call to interact with the model.
 */
public class ModelController {
	public static final int MAX_SIZE = 10;

	private final PersistenceController persistenceController = new PersistenceController();

	private KenKenProposer proposer;
	private KenKenSolver solver;

	private KenKen activeKenKen;
	// TODO: Stopwatch class

	/**
	 * Method to set the active KenKen.
	 * @param activeKenKen KenKen to set as active.
	 */
	public void setActiveKenKen(KenKen activeKenKen) {
		this.activeKenKen = activeKenKen;
	}

	/**
	 * Method to get the active KenKen.
	 *
	 */
	public KenKen getActiveKenKen() {
		return this.activeKenKen;
	}

	/* PROPOSE KENKEN */

	/**
	 * Method to propose a KenKen.
	 *
	 * @param size Size of the KenKen.
	 */
	public void proposeKenKen(int size) {
		proposer = new KenKenProposer(size);
	}

	/**
	 * Method to propose a group.
	 *
	 * @param symbol Symbol of the operation.
	 * @param target Target of the operation.
	 * @return The proposed group.
	 */
	public Group proposerCreateGroup(String symbol, int target) {
		try {
			return proposer.createGroup(OperationFactory.createOperation(symbol, target));
		} catch (CannotCreateOperationException e) {
			return null;
		}
	}

	/**
	 * Method to delete a group.
	 * @param group Group to delete.
	 */
	public void proposerDeleteGroup(Group group) {
		proposer.deleteGroup(group);
	}

	/**
	 * Method to add a cell to a group.
	 *
	 * @param row Row of the cell.
	 * @param col Column of the cell.
	 * @param group Group to add the cell to.
	 * @throws GroupDoesNotExistException If the group does not exist.
	 */
	public void proposerAddCellToGroup(int row, int col, Group group) throws GroupDoesNotExistException {
		proposer.addCellToGroup(row, col, group);
	}

	/**
	 * Method to remove a cell from a group.
	 *
	 * @param row Row of the cell.
	 * @param col Column of the cell.
	 */
	public void proposerRemoveCellGroup(int row, int col) {
		proposer.removeCellGroup(row, col);
	}

	/**
	 * Method to generate a KenKen.
	 *
	 * @return The generated KenKen.
	 * @throws CellAlreadyInGroupException If a cell is already in a group.
	 * @throws CellHasNoGroupException If a cell has no group.
	 * @throws GroupCellsNotContiguousException If the cells of a group are not contiguous.
	 * @throws TooManyOperandsException If there are too many operands.
	 */
	public KenKen proposerGenerate() throws CellAlreadyInGroupException, CellHasNoGroupException, GroupCellsNotContiguousException, TooManyOperandsException {
		proposer.generateGroups();
		return proposer.getKenKen();
	}

	/* GENERATE KENKEN */

	/**
	 * Method to generate a KenKen.
	 *
	 * @param size Size of the KenKen.
	 * @param fixedCells Number of fixed cells.
	 * @param topology Topology of the KenKen.
	 * @param operations Operations of the KenKen.
	 * @return True if the KenKen was generated, false otherwise.
	 * @throws OperandsDoNotMatchException If the operands do not match.
	 */
	public boolean generateKenKen(int size, int fixedCells, Topology topology, List<Class<? extends Operation>> operations) throws OperandsDoNotMatchException, CannotCreateOperationException, ShapesAndOperationsDoNotMatchException {
		KenKenGenerator generator = new KenKenGenerator(size, fixedCells, topology, operations);
		boolean generated = generator.generate();
		if (generated)
			activeKenKen = generator.getKenKen();
        return generated;
    }

	/* EXPORT KENKEN */

	/**
	 * Method to export a KenKen.
	 *
	 * @param path Path to export the KenKen.
	 * @throws IOException If an I/O error occurs.
	 */
	public void exportKenKen(String path) throws IOException {
		if (activeKenKen == null)
			return;
		persistenceController.saveKenKen(toDTO(activeKenKen), path);
	}

	/* LOAD KENKEN */

	/**
	 * Method to load a KenKen.
	 *
	 * @param path Path to load the KenKen.
	 * @return The loaded KenKen.
	 * @throws CannotLoadKenKenException If the KenKen cannot be loaded.
	 */
	public KenKen loadKenKen(String path) throws CannotLoadKenKenException {
		try {
			activeKenKen = fromDTO(persistenceController.loadKenKen(path));
			try {
				activeKenKen.checkCellsGroups();
			} catch (CellHasNoGroupException | GroupCellsNotContiguousException e) {
				activeKenKen = null;
				throw new CannotLoadKenKenException();
			}
			return activeKenKen;
		} catch (IOException | CannotCreateOperationException | CellAlreadyInGroupException | TooManyOperandsException |
				 ValueOutOfBoundsException | RewriteFixedPositionException e) {
			throw new CannotLoadKenKenException();
		}
	}

	/* AI SOLVE KENKEN */

	/**
	 * Method to set the AI to solve the KenKen.
	 */
	public void setAiSolve() {
		solver = new KenKenSolver(activeKenKen);
	}

	/**
	 * Method to solve the KenKen with the AI.
	 *
	 * @return True if the KenKen was solved, false otherwise.
	 */
	public boolean aiSolve() {
		return solver.solve();
	}

	/* PLAY KENKEN */

	/**
	 * Method to play a KenKen.
	 * @param row Row of the cell.
	 * @param col Column of the cell.
	 * @param value Value to set.
	 * @throws EraseFixedValueException If a fixed value is erased.
	 * @throws RewriteFixedPositionException If a fixed position is rewritten.
	 * @throws ValueOutOfBoundsException If the value is out of bounds.
	 */
	public void makeMove(int row, int col, int value) throws EraseFixedValueException, RewriteFixedPositionException, ValueOutOfBoundsException {
		if (value == 0)
			activeKenKen.erasePosition(row, col);
		else
			activeKenKen.setPosition(row, col, value);
	}

	/**
	 * Method to check the KenKen.
	 *
	 * @return True if the KenKen is correct, false otherwise.
	 */
	public boolean check() {
		try {
			return activeKenKen.check();
		} catch (OperandsDoNotMatchException | NonIntegerResultException e) {
			return false;
		}
	}

	/**
	 * Method to get the score.
	 * @param username Username of the player.
	 * @throws InvalidUsernameException If the username is invalid.
	 * @throws IOException If an I/O error occurs.
	 */
	public void saveScore(String username, int topology, int operations, int minutes, int seconds) throws InvalidUsernameException, IOException {
		if (username.contains(" ") || username.isEmpty())
			throw new InvalidUsernameException();
		persistenceController.saveScore(new ScoreDTO(username, getScore(activeKenKen,topology, operations, minutes,seconds)));
	}

	/* SAVE GAME */

	/**
	 * Method to save the game.
	 * @throws IOException If an I/O error occurs.
	 */
	public void saveGame(String path) throws IOException {
		if (activeKenKen == null)
			return;
		persistenceController.saveGame(toDTO(activeKenKen), path);
	}

	/* LOAD SAVED GAME */

	/**
	 * Method to load a saved game.
	 * @param path Path to load the saved game.
	 * @return The loaded KenKen.
	 * @throws CannotLoadKenKenException If the KenKen cannot be loaded.
	 */
	public KenKen loadSavedGame(String path) throws CannotLoadKenKenException {
		try {
			activeKenKen = fromDTO(persistenceController.loadSavedGame(path));
			try {
				activeKenKen.checkCellsGroups();
			} catch (CellHasNoGroupException | GroupCellsNotContiguousException e) {
				activeKenKen = null;
				throw new CannotLoadKenKenException();
			}
			return activeKenKen;
		} catch (IOException | CannotCreateOperationException | CellAlreadyInGroupException | TooManyOperandsException |
				 ValueOutOfBoundsException | RewriteFixedPositionException e) {
			throw new CannotLoadKenKenException();
		}
	}

	/* CHECK RANKING */

	/**
	 * Method to check the ranking.
	 * @return The scores of the ranking.
	 * @throws CannotLoadScoresException If the scores cannot be loaded.
	 */
	public List<Score> checkRanking() throws CannotLoadScoresException {
		List<Score> scores = new ArrayList<>();
		List<ScoreDTO> scoreDTOS;
		try {
			scoreDTOS = persistenceController.loadScores();
		} catch (IOException e) {
			throw new CannotLoadScoresException();
		}
		for (ScoreDTO scoreDTO : scoreDTOS)
			scores.add(new Score(scoreDTO.getUser(), scoreDTO.getScore()));
		return scores;
	}

	/* PRIVATE METHODS */

	/**
	 * Method to get the score.
	 * @return The score.
	 */


	private int getScore(KenKen k,int t, int o, int m, int s) {
		int time = m * 60 + s;
		int score = k.getSize() * k.getSize() * k.getGroups().size() * t * o;
		score -= time;
		return Math.round(score);
	}

	/**
	 * Method to convert a KenKen to a DTO.
	 * @param kenKen KenKen to convert.
	 * @return The converted KenKen.
	 */
	private static KenKenDTO toDTO(KenKen kenKen) {
		KenKenDTO dto = new KenKenDTO(kenKen.getSize(), kenKen.getGroups().size());
		for (Group group : kenKen.getGroups()) {
			GroupDTO groupDTO = new GroupDTO(toDTO(group.getOperation()), group.getCells().size());
			for (Cell cell : group.getCells())
				groupDTO.addCell(new CellDTO(cell.getRow() + 1, cell.getCol() + 1, cell.isFixed(), cell.getValue()));
			dto.addGroup(groupDTO);
		}
		return dto;
	}

	/**
	 * Method to convert a DTO to a KenKen.
	 * @param dto DTO to convert.
	 * @return The converted KenKen.
	 * @throws CannotCreateOperationException If the operation cannot be created.
	 * @throws CellAlreadyInGroupException If a cell is already in a group.
	 * @throws TooManyOperandsException If there are too many operands.
	 * @throws ValueOutOfBoundsException If the value is out of bounds.
	 * @throws RewriteFixedPositionException If a fixed position is rewritten.
	 */
	private static KenKen fromDTO(KenKenDTO dto) throws CannotCreateOperationException, CellAlreadyInGroupException, TooManyOperandsException, ValueOutOfBoundsException, RewriteFixedPositionException {
		KenKen kenKen = new KenKen(dto.getSize());
		for (GroupDTO group : dto.getGroups()) {
			kenKen.addGroup(fromDTO(group.getOperation()));
			for (CellDTO cell : group.getCells()) {
				kenKen.addCellToLastGroup(cell.getRow() - 1, cell.getCol() - 1);
				if (cell.getValue() != 0) {
					if (cell.isFixed())
						kenKen.setFixedPosition(cell.getRow() - 1, cell.getCol() - 1, cell.getValue());
					else
						kenKen.setPosition(cell.getRow() - 1, cell.getCol() - 1, cell.getValue());
				}
			}
		}
		return kenKen;
	}

	/**
	 * Method to convert an operation to a DTO.
	 * @param operation Operation to convert.
	 * @return The converted operation.
	 */
	private static OperationDTO toDTO(Operation operation) {
		return new OperationDTO(OperationDTO.getOperationId(operation.getSymbol()), operation.getTarget());
	}

	/**
	 * Method to convert a DTO to an operation.
	 * @param dto DTO to convert.
	 * @return The converted operation.
	 * @throws CannotCreateOperationException If the operation cannot be created.
	 */
	private static Operation fromDTO(OperationDTO dto) throws CannotCreateOperationException {
		return OperationFactory.createOperation(dto.getSymbol(), dto.getTarget());
	}
}
