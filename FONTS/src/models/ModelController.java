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

public class ModelController {
	public static final int MAX_SIZE = 15;

	private final PersistenceController persistenceController = new PersistenceController();

	private KenKenProposer proposer;
	private KenKenSolver solver;

	private KenKen activeKenKen;
	// TODO: Stopwatch class

	// TODO: delete method
	public void setActiveKenKen(KenKen activeKenKen) {
		this.activeKenKen = activeKenKen;
	}

	/* PROPOSE KENKEN */

	public void proposeKenKen(int size) {
		proposer = new KenKenProposer(size);
	}

	public Group proposerCreateGroup(String symbol, int target) {
		try {
			return proposer.createGroup(OperationFactory.createOperation(symbol, target));
		} catch (CannotCreateOperationException e) {
			return null;
		}
	}

	public String[] proposerGetGroupNotationsEnum() {
		return proposer.getGroupNotationsEnum();
	}

	public void proposerDeleteGroup(Group group) {
		proposer.deleteGroup(group);
	}

	public void proposerAddCellToGroup(int row, int col, Group group) throws GroupDoesNotExistException {
		proposer.addCellToGroup(row, col, group);
	}

	public void proposerRemoveCellGroup(int row, int col) {
		proposer.removeCellGroup(row, col);
	}

	public boolean proposerGenerate() {
		try {
			proposer.generateGroups();
		} catch (TooManyOperandsException | GroupCellsNotContiguousException | CellHasNoGroupException |
				 CellAlreadyInGroupException e) {
			return false;
		}
		return true;
	}

	/* GENERATE KENKEN */

	public boolean generateKenKen(int size, int fixedCells, Topology topology, List<Class<? extends Operation>> operations) throws OperandsDoNotMatchException {
        return new KenKenGenerator(size, fixedCells, topology, operations).generate();
    }

	/* EXPORT KENKEN */

	public void exportKenKen(String path) throws IOException {
		if (activeKenKen == null)
			return;
		persistenceController.saveKenKen(toDTO(activeKenKen), path);
	}

	/* LOAD KENKEN */

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

	public void setAiSolve() {
		solver = new KenKenSolver(activeKenKen);
	}

	public boolean aiSolve() {
		return solver.solve();
	}

	/* PLAY KENKEN */

	public void makeMove(int row, int col, int value) throws EraseFixedValueException, RewriteFixedPositionException, ValueOutOfBoundsException {
		if (value == 0)
			activeKenKen.erasePosition(row, col);
		else
			activeKenKen.setPosition(row, col, value);
	}

	public boolean check() {
		try {
			return activeKenKen.check();
		} catch (OperandsDoNotMatchException | NonIntegerResultException e) {
			return false;
		}
	}

	public void saveScore(String username) throws InvalidUsernameException, IOException {
		if (username.contains(" ") || username.isEmpty())
			throw new InvalidUsernameException();
		persistenceController.saveScore(new ScoreDTO(username, getScore()));
	}

	/* SAVE GAME */

	public void saveGame() throws IOException {
		if (activeKenKen == null)
			return;
		persistenceController.saveGame(toDTO(activeKenKen));
	}

	/* LOAD SAVED GAME */

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

	private int getScore() {
		// TODO: think about the implementation
		return new Random().nextInt(15000);
	}

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

	private static OperationDTO toDTO(Operation operation) {
		return new OperationDTO(OperationDTO.getOperationId(operation.getSymbol()), operation.getTarget());
	}

	private static Operation fromDTO(OperationDTO dto) throws CannotCreateOperationException {
		return OperationFactory.createOperation(dto.getSymbol(), dto.getTarget());
	}
}
