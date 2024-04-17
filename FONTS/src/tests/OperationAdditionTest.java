package tests;

import exceptions.NonIntegerResultException;
import exceptions.OperandsDoNotMatchException;
import models.operations.OperationAddition;
import org.junit.Test;

public class OperationAdditionTest {
    private final static OperationAddition op12 = new OperationAddition(12);
    private final static OperationAddition op22 = new OperationAddition(22);
    private final static OperationAddition op72 = new OperationAddition(72);

    @Test
    public void check() throws OperandsDoNotMatchException, NonIntegerResultException {
        assert !op12.check(new int[]{1, 2});
        assert op12.check(new int[]{1, 3, 8});

        assert op22.check(new int[]{9, 9, 4});
        assert !op22.check(new int[]{1, 5, 9});

        assert !op72.check(new int[]{1, 70});
        assert !op72.check(new int[]{33, 10, 30});
        assert op72.check(new int[]{10, 30, 20, 12});
    }
}
