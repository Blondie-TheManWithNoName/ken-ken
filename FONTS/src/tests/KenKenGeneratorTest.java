package tests;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import exceptions.OperandsDoNotMatchException;
import models.kenken.KenKen;
import models.kenken.KenKenGenerator;
import models.operations.Operation;
import models.operations.OperationAddition;
import models.operations.OperationSubtraction;
import models.topologies.Shape;
import models.topologies.Topology;

public class KenKenGeneratorTest {
	private KenKenGenerator generator;
    private int size;
    private int fixedCells;
    private Topology topology;
    private List<Class<? extends Operation>> allowedOperations;
    private KenKen kenKen;

    @Before
    public void init()
    {
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
        assert generator.generate();
        
    }

    @Test
    public void getKenKenTest(){
        try{
            generator = new KenKenGenerator(size, fixedCells, topology, allowedOperations);
        } catch(OperandsDoNotMatchException e) {
            assert false;
        }
        kenKen = generator.getKenKen();
        if (kenKen != null) {
            assert true;
        } else {
            assert false;
        }


        
    }

    @Test
    public void generateTest(){
        
    }
    @Test
    public void generateLatinSquareTest(){
        
    }
    @Test
    public void generateGroupsTest1(){
        
    }
    
    @Test
    public void generateGroupsTest2(){
        
    }

    @Test
    public void topologyFitsTest(){
        
    }
    
    @Test
    public void createGroupTest(){
        
    }
    
    @Test
    public void removeGroupTest(){
        
    }
    
    @Test
    public void hasEmptySpacesTest(){
        
    }
}
