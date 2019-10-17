package modeltest;

import model.trantype.DayToDayTran;
import model.trantype.Transaction;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TransactionTest {
    private DayToDayTran transaction;
    private static final double AMOUNT = 400.23;
    private static final String DESC = "Test";
    private Transaction tran2;

    @BeforeEach
    public void runBefore() {
        transaction = new DayToDayTran (AMOUNT, DESC);
    }

    @Test
    public void testGetTransDetail() {
        assertEquals(("\n|| Description: " + DESC + "            Amount: " + AMOUNT + " ||"),
                transaction.getTransDetail());
    }
    @Test
    public void testGetTrans() {
        assertEquals(transaction, transaction.getTrans());
    }


    @Test
    public void testGetAmount() {
        assertEquals(transaction.getAmount(), AMOUNT);
    }

    @Test
    public void testGetDesc() {
        assertEquals(transaction.getDesc(), DESC);
    }

    @Test
    public void testSetAmount() {
        transaction.setAmount(550);
        assertEquals(transaction.getAmount(), 550);
    }

    @Test
    public void testSetDesc() {
        transaction.setDesc("NEW");
        assertEquals(transaction.getDesc(), "NEW");
    }
}