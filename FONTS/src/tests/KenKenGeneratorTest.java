package tests;

import java.util.List;

import org.junit.*;

import exceptions.OperandsDoNotMatchException;
import models.kenken.KenKenGenerator;
import models.operations.Operation;
import models.operations.OperationAddition;
import models.operations.OperationSubtraction;
import models.topologies.Shape;
import models.topologies.Topology;

import static org.junit.Assert.assertTrue;

public class KenKenGeneratorTest {
	private KenKenGenerator generator;
    private int size;
    private int fixedCells;
    private Topology topology;
    private List<Class<? extends Operation>> allowedOperations;

    @Before
    public void init() {
		size = 4;
        fixedCells = 2;
        topology = new Topology(Shape.DASH);
        allowedOperations = List.of(
            OperationAddition.class,
			OperationSubtraction.class
        );
    }

    @Test
    public void generatorTest(){
        try{
            generator = new KenKenGenerator(size, fixedCells, topology, allowedOperations);
        } catch(OperandsDoNotMatchException e) {
            assert false;
        }
        assertTrue(generator.generate());
    }
}
