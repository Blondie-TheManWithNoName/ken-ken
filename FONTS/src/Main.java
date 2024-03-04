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
		} catch (GroupCellsNotContiguousException | TooManyOperandsException | CellAlreadyInGroupException | CellHasNoGroupException e) {
			System.out.println(e.getMessage());
			return;
		}

		KenKenView view = new KenKenSolverView(new KenKenSolver(kenKen));
		SwingUtilities.invokeLater(view::start);
	}
}
