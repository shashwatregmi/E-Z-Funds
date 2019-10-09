package modeltest;

import model.trantype.LongTermTran;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InvestementTranTest {
    private LongTermTran longTermTran;
    private static final double AMOUNT = 400.23;
    private static final String DESC = "Test";
    private static final Integer TERM = 30;
    private static final double INTRATE = 2.99;


    @BeforeEach
    public void runBefore() {
        longTermTran = new LongTermTran(AMOUNT, DESC, TERM, INTRATE);
    }

    @Test
    public void testGetTransDetail() {
        assertEquals(("\n|| Description: " + DESC + "            Amount: " + AMOUNT
                        + "  Interest Rate:" + INTRATE + "  Term:" + TERM + " ||"),
                longTermTran.getTransDetail());
    }

    @Test
    public void testGetTerm() {
        assertEquals(longTermTran.getTerm(), 30);
    }

    @Test
    public void testGetIntRate() {
        assertEquals(longTermTran.getInterestRate(), 2.99);
    }

    @Test
    public void testGetTrans() {
        assertEquals(longTermTran, longTermTran.getTrans());
    }

}
