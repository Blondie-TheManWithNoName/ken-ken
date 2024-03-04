import exceptions.*;
import models.KenKen;
import models.KenKenSolver;
import models.operations.*;
import presentation.views.KenKenSolverView;
import presentation.views.KenKenView;

import javax.swing.*;

public class Main {
	public static void main(String[] args) {
		KenKen kenKen = new KenKen(3);
		KenKen kenKen2 = new KenKen(4);
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
			kenKen.checkAllCellsHaveGroup();

			kenKen2.addGroup(new OperationMultiplication(12));
			kenKen2.addCellToLastGrop(0, 0);
			kenKen2.addCellToLastGrop(0, 1);
			kenKen2.addCellToLastGrop(1, 0);
			kenKen2.addGroup(new OperationDivision(4));
			kenKen2.addCellToLastGrop(0, 2);
			kenKen2.addCellToLastGrop(1, 2);
			kenKen2.addGroup(new OperationSubtraction(1));
			kenKen2.addCellToLastGrop(0, 3);
			kenKen2.addCellToLastGrop(1, 3);
			kenKen2.addGroup(new OperationLCM(6));
			kenKen2.addCellToLastGrop(1, 1);
			kenKen2.addCellToLastGrop(2, 1);
			kenKen2.addCellToLastGrop(2, 0);
			kenKen2.addGroup(new OperationGCD(2));
			kenKen2.addCellToLastGrop(2, 2);
			kenKen2.addCellToLastGrop(2, 3);
			kenKen2.addGroup(new OperationAddition(10));
			kenKen2.addCellToLastGrop(3, 0);
			kenKen2.addCellToLastGrop(3, 1);
			kenKen2.addCellToLastGrop(3, 2);
			kenKen2.addCellToLastGrop(3, 3);
			kenKen2.checkAllCellsHaveGroup();
		} catch (GroupCellsNotContiguousException | TooManyOperandsException | CellAlreadyInGroupException | CellHasNoGroupException e) {
			System.out.println(e.getMessage());
			return;
		}

		KenKenView view = new KenKenSolverView(new KenKenSolver(kenKen2));
		SwingUtilities.invokeLater(view::start);
	}
}
