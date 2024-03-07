package tests;

import exceptions.CellAlreadyInGroupException;
import exceptions.CellHasNoGroupException;
import exceptions.GroupCellsNotContiguousException;
import exceptions.TooManyOperandsException;
import models.KenKen;
import models.KenKenSolver;
import models.operations.*;
import org.junit.Test;

public class KenKenSolverTest {
	@Test
	public void solve3() {
		KenKen kenKen = new KenKen(3);

		try {
			kenKen.addGroup(new OperationLCM(3));
			kenKen.addCellToLastGrop(0, 0);
			kenKen.addCellToLastGrop(1, 0);
			kenKen.addGroup(new OperationSubtraction(1));
			kenKen.addCellToLastGrop(0, 1);
			kenKen.addCellToLastGrop(0, 2);
			kenKen.addGroup(new OperationMultiplication(6));
			kenKen.addCellToLastGrop(1, 1);
			kenKen.addCellToLastGrop(1, 2);
			kenKen.addCellToLastGrop(2, 2);
			kenKen.addGroup(new OperationAddition(5));
			kenKen.addCellToLastGrop(2, 0);
			kenKen.addCellToLastGrop(2, 1);
		} catch (TooManyOperandsException | CellAlreadyInGroupException e) {
			assert false;
		}
		try {
			kenKen.checkCellsGroups();
		} catch (CellHasNoGroupException | GroupCellsNotContiguousException e) {
			assert false;
		}

		KenKenSolver kenKenSolver = new KenKenSolver(kenKen);
		assert kenKenSolver.solve();

		int[][] solution = {
			{3, 1, 2},
			{1, 2, 3},
			{2, 3, 1}
		};

		for (int i = 0; i < kenKen.getSize(); i++)
			for (int j = 0; j < kenKen.getSize(); j++)
				assert kenKen.getValue(i, j) == solution[i][j];
	}

	@Test
	public void solve4() {
		KenKen kenKen = new KenKen(4);

		try {
			kenKen.addGroup(new OperationMultiplication(12));
			kenKen.addCellToLastGrop(0, 0);
			kenKen.addCellToLastGrop(0, 1);
			kenKen.addCellToLastGrop(1, 0);
			kenKen.addGroup(new OperationDivision(4));
			kenKen.addCellToLastGrop(0, 2);
			kenKen.addCellToLastGrop(1, 2);
			kenKen.addGroup(new OperationSubtraction(1));
			kenKen.addCellToLastGrop(0, 3);
			kenKen.addCellToLastGrop(1, 3);
			kenKen.addGroup(new OperationLCM(6));
			kenKen.addCellToLastGrop(1, 1);
			kenKen.addCellToLastGrop(2, 1);
			kenKen.addCellToLastGrop(2, 0);
			kenKen.addGroup(new OperationGCD(2));
			kenKen.addCellToLastGrop(2, 2);
			kenKen.addCellToLastGrop(2, 3);
			kenKen.addGroup(new OperationAddition(10));
			kenKen.addCellToLastGrop(3, 0);
			kenKen.addCellToLastGrop(3, 1);
			kenKen.addCellToLastGrop(3, 2);
			kenKen.addCellToLastGrop(3, 3);
		} catch (TooManyOperandsException | CellAlreadyInGroupException e) {
			assert false;
		}
		try {
			kenKen.checkCellsGroups();
		} catch (CellHasNoGroupException | GroupCellsNotContiguousException e) {
			assert false;
		}

		KenKenSolver kenKenSolver = new KenKenSolver(kenKen);
		assert kenKenSolver.solve();

		int[][] solution = {
			{1, 3, 4, 2},
			{4, 2, 1, 3},
			{3, 1, 2, 4},
			{2, 4, 3, 1}
		};

		for (int i = 0; i < kenKen.getSize(); i++)
			for (int j = 0; j < kenKen.getSize(); j++)
				assert kenKen.getValue(i, j) == solution[i][j];
	}

	@Test
	public void noSolution3() {
		KenKen kenKen = new KenKen(3);

		try {
			kenKen.addGroup(new OperationLCM(3));
			kenKen.addCellToLastGrop(0, 0);
			kenKen.addCellToLastGrop(1, 0);
			kenKen.addGroup(new OperationSubtraction(1));
			kenKen.addCellToLastGrop(0, 1);
			kenKen.addCellToLastGrop(0, 2);
			kenKen.addGroup(new OperationMultiplication(7));
			kenKen.addCellToLastGrop(1, 1);
			kenKen.addCellToLastGrop(1, 2);
			kenKen.addCellToLastGrop(2, 2);
			kenKen.addGroup(new OperationAddition(5));
			kenKen.addCellToLastGrop(2, 0);
			kenKen.addCellToLastGrop(2, 1);
		} catch (TooManyOperandsException | CellAlreadyInGroupException e) {
			assert false;
		}
		try {
			kenKen.checkCellsGroups();
		} catch (CellHasNoGroupException | GroupCellsNotContiguousException e) {
			assert false;
		}

		KenKenSolver kenKenSolver = new KenKenSolver(kenKen);
		assert !kenKenSolver.solve();
	}
}
