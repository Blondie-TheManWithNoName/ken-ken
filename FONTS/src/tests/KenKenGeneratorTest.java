package tests;

import static org.junit.Assert.*;

import java.util.EnumSet;

import org.junit.Test;

import exceptions.CannotCreateOperationException;
import exceptions.CellHasNoGroupException;
import exceptions.GroupCellsNotContiguousException;
import models.kenken.KenKen;
import models.kenken.KenKenGenerator;
import models.operations.Operation;
import models.topologies.Topology;

public class KenKenGeneratorTest {
    @Test
    public void generateKenKen_Success() {
//        int size = 4;
//        int fixedValues = 6; // Adjust this value as needed
//        Topology topology = new Topology(size);
//        KenKenGenerator generator = new KenKenGenerator(size, fixedValues, topology);
//
//        // Define the set of allowed operation types
//        Set<OperationType> allowedOperations = EnumSet.of(
//                OperationType.ADDITION,
//                OperationType.SUBTRACTION,
//                OperationType.MULTIPLICATION
//        );
//
//        // Generate the KenKen puzzle
//        assertTrue(generator.generate());
//
//        // Check if the generated puzzle is not null
//        KenKen kenKen = generator.getKenKen();
//        assertNotNull(kenKen);
//
//        // Check if the generated puzzle size matches the specified size
//        assertEquals(size, kenKen.getSize());
//
//        // Verify that the number of fixed values matches the specified fixedValues
//        assertEquals(fixedValues, kenKen.getFixedCellCount());
//
//        // Validate the generated groups
//        try {
//            kenKen.checkCellsGroups();
//        } catch (CellHasNoGroupException | GroupCellsNotContiguousException | CannotCreateOperationException e) {
//            fail("Exception occurred during group validation: " + e.getMessage());
//        }
//
//        // Verify that each group in the puzzle has a valid operation
//        for (Operation operation : kenKen.getOperations()) {
//            assertTrue(allowedOperations.contains(operation.getOperationType()));
//        }
    }
}
