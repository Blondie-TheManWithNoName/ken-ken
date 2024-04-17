package models;

import exceptions.*;
import models.kenken.*;
import models.operations.Operation;
import models.operations.OperationFactory;
import models.topologies.Topology;
import persistence.PersistenceController;
import persistence.dto.CellDTO;
import persistence.dto.GroupDTO;
import persistence.dto.KenKenDTO;
import persistence.dto.OperationDTO;

import java.io.IOException;
import java.util.List;

public class ModelController {
	public static final int MAX_SIZE = 15;

	private final PersistenceController persistenceController = new PersistenceController();

	private KenKenProposer proposer;
	private KenKenGenerator generator;
	private KenKenSolver solver;

	private KenKen activeKenKen;
	// TODO: Stopwatch class
	private List<Score> scores;

	// TODO: delete
	public KenKen getActiveKenKen() {
		return activeKenKen;
	}

	/* PROPOSE KENKEN */

	public void proposeKenKen(int size) {
		proposer = new KenKenProposer(size);
	}

	// TODO: delete
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

	public void proposerPlay() {
		activeKenKen = proposer.getKenKen();
	}

	public void proposerAiSolve() {
		solver = new KenKenSolver(activeKenKen);
	}

	/* GENERATE KENKEN */

	public boolean generateKenKen(int size, int fixedCells, Topology topology, List<Class<? extends Operation>> operations) throws OperandsDoNotMatchException {
        generator = new KenKenGenerator(size, fixedCells, topology, operations);
        return generator.generate();
    }

	public void generatorPlay() {
		activeKenKen = generator.getKenKen();
	}

	/* LOAD KENKEN */

	public KenKen loadKenKen(String path) throws CannotLoadKenKenException {
		try {
			return fromDTO(persistenceController.loadKenKen(path));
		} catch (IOException | CannotCreateOperationException | CellAlreadyInGroupException | TooManyOperandsException e) {
			throw new CannotLoadKenKenException();
		}
	}

	/* LOAD SAVED GAME */

	// TODO: loadSavedGame();

	private int getScore() {
		// TODO: Dani
		return 0;
	}

	private static KenKen fromDTO(KenKenDTO dto) throws CannotCreateOperationException, CellAlreadyInGroupException, TooManyOperandsException {
		KenKen kenKen = new KenKen(dto.getSize());
		for (GroupDTO group : dto.getGroups()) {
			kenKen.addGroup(fromDTO(group.getOperation()));
			for (CellDTO cell : group.getCells())
				kenKen.addCellToLastGroup(cell.getRow() - 1, cell.getCol() - 1);
		}
		return kenKen;
	}

	private static Operation fromDTO(OperationDTO dto) throws CannotCreateOperationException {
		return OperationFactory.createOperation(dto.getSymbol(), dto.getTarget());
	}
}
