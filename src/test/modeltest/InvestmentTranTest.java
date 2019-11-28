package modeltest;

import model.exceptions.NegativeAmt;
import model.trantype.LongTermTran;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InvestmentTranTest {
    private LongTermTran longTermTran;
    private static final double AMOUNT = 400.23;
    private static final String DESC = "Test";
    private static final Integer TERM = 30;
    private static final double INTRATE = 2.99;


    @BeforeEach
    void runBefore() throws IOException, NegativeAmt {
        longTermTran = new LongTermTran(AMOUNT, DESC, TERM, INTRATE);
    }

    @Test
    void testGetTransDetail() {
        assertEquals(("\n|| Description: " + DESC + "            Amount: " + AMOUNT
                        + "  Interest Rate:" + INTRATE + "  Term:" + TERM + " ||"),
                longTermTran.getTransDetail());
    }

    @Test
    void testGetAmount(){
        assertEquals(longTermTran.getAmount(1), AMOUNT * (INTRATE/100 + 1));
    }

    @Test
    void testGetNegAmount() {
        assertEquals(longTermTran.getAmount("s"), (AMOUNT * (INTRATE/100 + 1))*-1);
    }

    @Test
    void testGetTerm() {
        assertEquals(longTermTran.getTerm(), 30);
    }

    @Test
    void testGetIntRate() {
        assertEquals(longTermTran.getInterestRate(), 2.99);
    }

    @Test
    void testGetTrans() {
        assertEquals(longTermTran, longTermTran.getTrans());
    }



}
