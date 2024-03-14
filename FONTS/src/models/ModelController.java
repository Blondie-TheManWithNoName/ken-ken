package models;

import exceptions.*;
import models.kenken.*;
import models.operations.OperationFactory;
import models.topologies.Topology;

import java.util.List;

public class ModelController {
	public static final int MAX_SIZE = 15;

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

	// TODO: delete
	public void proposerDeleteGroup(int index) {
		proposer.deleteGroup(proposer.getGroup(index));
	}

	public void proposerDeleteGroup(Group group) {
		proposer.deleteGroup(group);
	}

	// TODO: delete
	public void proposerAddCellToGroup(int row, int col, int groupIndex) {
		proposer.addCellToGroup(row, col, proposer.getGroup(groupIndex));
	}

	public void proposerAddCellToGroup(int row, int col, Group group) {
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

	public boolean generateKenKen(int size, int fixedCells, Topology topology) {
		generator = new KenKenGenerator(size, fixedCells, topology);
		return generator.generate();
	}

	public void generatorPlay() {
		activeKenKen = generator.getKenKen();
	}

	/* LOAD KENKEN */

	// TODO: load();

	/* LOAD SAVED GAME */

	// TODO: loadSavedGame();

	private int getScore() {
		// TODO: Dani
		return 0;
	}
}
