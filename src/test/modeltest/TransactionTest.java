package modeltest;

import model.trantype.DayToDayTran;
import model.trantype.Transaction;
import model.trantype.UnexpectedStream;
import org.junit.jupiter.api.*;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TransactionTest {
    private DayToDayTran transaction;
    private UnexpectedStream transaction2;
    private static final double AMOUNT = 400.23;
    private static final String DESC = "Test";
    private static final String SOURCE = "idk";

    @BeforeEach
    public void runBefore() throws IOException {
        transaction = new DayToDayTran (AMOUNT, DESC);
        transaction2 = new UnexpectedStream(AMOUNT, DESC, "idk");
    }

    @Test
    public void testGetTransDetail() {
        assertEquals(("\n|| Description: " + DESC + "            Amount: " + AMOUNT + " ||"),
                transaction.getTransDetail());
        assertEquals(("\n|| Description: " + DESC + "            Amount: " + AMOUNT
                + "  Source:" + SOURCE), transaction2.getTransDetail());

    }

    @Test
    public void testGetSource(){
        assertEquals(SOURCE, transaction2.getSource());
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