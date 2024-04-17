package tests;

import exceptions.*;
import models.kenken.Group;
import models.kenken.KenKenProposer;
import models.operations.*;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;

public class KenKenProposerTest {

    private KenKenProposer kenKenProposer;
    private Group[] groups;

    @Before
    public void init()
    {
        kenKenProposer = new KenKenProposer(3);
        groups = new Group[5];
        groups[0] = kenKenProposer.createGroup(new OperationSubtraction(2));
        groups[1] = kenKenProposer.createGroup(new OperationSubtraction(1));
        groups[2] = kenKenProposer.createGroup(new OperationMultiplication(6));
        groups[3] = kenKenProposer.createGroup(new OperationDivision(2));
        groups[4] = kenKenProposer.createGroup(new OperationEquality(1));

		try {
			kenKenProposer.addCellToGroup(0, 0, groups[0]);
            kenKenProposer.addCellToGroup(0, 1, groups[0]);

            kenKenProposer.addCellToGroup(0, 2, groups[1]);
            kenKenProposer.addCellToGroup(1, 2, groups[1]);

            kenKenProposer.addCellToGroup(1, 0, groups[2]);
            kenKenProposer.addCellToGroup(2, 0, groups[2]);

            kenKenProposer.addCellToGroup(1, 1, groups[3]);
            kenKenProposer.addCellToGroup(2, 1, groups[3]);

            kenKenProposer.addCellToGroup(2, 2, groups[4]);
		} catch (GroupDoesNotExistException e) {
            assert false;
		}
    }


    @Test
    public void getSizeTest(){
        assertEquals("Size should be 3", 3, kenKenProposer.getSize());
    }

    @Test
    public void setFixedPositionTest()
    {
        try {
            kenKenProposer.setFixedPosition(0, 0, 4);
            assert false;
        }
        catch (ValueOutOfBoundsException e)
        {
            assert true;
        }

        try {
            kenKenProposer.setFixedPosition(0, 0, 3);
            assert true;
        }
        catch (ValueOutOfBoundsException e)
        {
            assert false;
        }
    }

    @Test
    public void getGroupNotationsEnumTest() {
        String[] notations = {"1) -2", "2) -1", "3) *6", "4) /2", "5) =1"};
        assertArrayEquals(notations, kenKenProposer.getGroupNotationsEnum());
    }

    @Test
    public void getGroupTest() {
        assertEquals("Group on groups[0] should be getGroup(0)", groups[0], kenKenProposer.getGroup(0));
        assertEquals("Group on groups[3] should be getGroup(3)", groups[3], kenKenProposer.getGroup(3));
    }

    @Test
    public void getCellGroupTest() {
        assertEquals("Group for cell(0, 0) should be group[0]", kenKenProposer.getCellGroup(0, 0), groups[0]);
        assertEquals("Group for cell(0, 1) should be group[0]", kenKenProposer.getCellGroup(0, 1), groups[0]);

        assertEquals("Group for cell(0, 2) should be group[1]", kenKenProposer.getCellGroup(0, 2), groups[1]);
        assertEquals("Group for cell(2, 0) should be group[2]", kenKenProposer.getCellGroup(2, 0), groups[2]);
        assertEquals("Group for cell(2, 1) should be group[3]", kenKenProposer.getCellGroup(2, 1), groups[3]);
        assertEquals("Group for cell(2, 2) should be group[4]", kenKenProposer.getCellGroup(2, 2), groups[4]);

        assertNotEquals("Group for cell(2, 2) should not be group[0]", kenKenProposer.getCellGroup(2, 2), groups[0]);
    }

    @Test
    public void removeCellGroupTest() {
        kenKenProposer.removeCellGroup(2, 2);
        assertNull("Group for cell(2, 2) should be null now", kenKenProposer.getCellGroup(2, 2));

		try {
            kenKenProposer.addCellToGroup(2, 2, groups[3]);
			kenKenProposer.addCellToGroup(2, 2, groups[4]);
		} catch (GroupDoesNotExistException e) {
            assert false;
		}
		kenKenProposer.removeCellGroup(2, 2);
        assertNull("Group for cell(2, 2) should be null now", kenKenProposer.getCellGroup(2, 2));

    }

    @Test
    public void deleteGroupTest()
    {
        kenKenProposer.deleteGroup(groups[0]);
        assertNotEquals("Group groups[0] should not be now on index 0", groups[0], kenKenProposer.getGroup(0));
        assertEquals("Group groups[1] should be now on index 0", groups[1], kenKenProposer.getGroup(0));
        assertNull("Group for cell(0, 0) should be null", kenKenProposer.getCellGroup(0, 0));
    }

    @Test
    public void generateGroupsTest()
    {
        kenKenProposer.removeCellGroup(2, 2);

        try {
            kenKenProposer.generateGroups();
            assert false;
        }
        catch (CellHasNoGroupException e)
        {
            assert true;
        }
        catch (TooManyOperandsException | GroupCellsNotContiguousException | CellAlreadyInGroupException e)
        {
            assert false;
        }
		try {
			kenKenProposer.addCellToGroup(2, 2, groups[3]);
		} catch (GroupDoesNotExistException e) {
			assert false;
		}
		try {
            kenKenProposer.generateGroups();
            assert false;
        }
        catch (TooManyOperandsException e)
        {
            assert true;
        }
        catch (CellHasNoGroupException | GroupCellsNotContiguousException | CellAlreadyInGroupException e)
        {
            assert false;
        }

		try {
			kenKenProposer.addCellToGroup(2, 2, groups[2]);
		} catch (GroupDoesNotExistException e) {
			assert false;
		}

		try {
            kenKenProposer.generateGroups();
            assert false;
        }
        catch (GroupCellsNotContiguousException e)
        {
            assert true;
        }
        catch (TooManyOperandsException | CellHasNoGroupException | CellAlreadyInGroupException e)
        {
            assert false;
        }


		try {
			kenKenProposer.addCellToGroup(0, 0, groups[2]);
		} catch (GroupDoesNotExistException e) {
            assert false;
		}

		try {
            kenKenProposer.generateGroups();
            assert false;
        }
        catch (CellAlreadyInGroupException e)
        {
            assert true;
        }
        catch (TooManyOperandsException | GroupCellsNotContiguousException | CellHasNoGroupException e)
        {
            assert false;
        }

        kenKenProposer.removeCellGroup(0, 0);
		try {
			kenKenProposer.addCellToGroup(0, 0, groups[0]);
		} catch (GroupDoesNotExistException e) {
			assert false;
		}
		kenKenProposer.removeCellGroup(2, 2);
		try {
			kenKenProposer.addCellToGroup(2, 2, groups[4]);
		} catch (GroupDoesNotExistException e) {
			assert true;
		}

        groups[4] = kenKenProposer.createGroup(new OperationEquality(1));
        try {
            kenKenProposer.addCellToGroup(2, 2, groups[4]);
        } catch (GroupDoesNotExistException e) {
            assert false;
        }

		try {
            kenKenProposer.generateGroups();
            assert true;
        } catch (TooManyOperandsException | GroupCellsNotContiguousException | CellHasNoGroupException |
                 CellAlreadyInGroupException e) {
            assert false;
        }
    }
}
