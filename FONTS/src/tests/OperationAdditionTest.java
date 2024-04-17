package tests;

import exceptions.NonIntegerResultException;
import exceptions.OperandsDoNotMatchException;
import models.operations.OperationAddition;
import org.junit.Test;

public class OperationAdditionTest {
    private final static OperationAddition op1 = new OperationAddition(12);
    private final static OperationAddition op2 = new OperationAddition(22);
    private final static OperationAddition op3 = new OperationAddition(72);

    @Test
    public void check() throws OperandsDoNotMatchException, NonIntegerResultException {
        assert !op1.check(new int[]{1, 2});
        assert op1.check(new int[]{1, 3, 8});

        assert op2.check(new int[]{9, 9, 4});
        assert !op2.check(new int[]{1, 5, 9});

        assert !op3.check(new int[]{1, 70});
        assert !op3.check(new int[]{33, 10, 30});
        assert op3.check(new int[]{10, 30, 20, 12});
    }
}
