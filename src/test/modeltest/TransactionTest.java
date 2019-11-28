package modeltest;

import model.Devices;
import model.exceptions.NegativeAmt;
import model.trantype.DayToDayTran;
import model.trantype.Transaction;
import model.trantype.UnexpectedStreamTransaction;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TransactionTest {
    private DayToDayTran transaction;
    private UnexpectedStreamTransaction transaction2;
    private static final double AMOUNT = 400.23;
    private static final String DESC = "Test";
    private static final String SOURCE = "idk";
    private List<DayToDayTran> tran = new ArrayList<>();


    @BeforeEach
    void runBefore() throws IOException, NegativeAmt {
        transaction = new DayToDayTran (AMOUNT, DESC);
        transaction2 = new UnexpectedStreamTransaction(AMOUNT, DESC, "idk");

        try {
            Transaction tran = new DayToDayTran(-123, "test");
            fail("Exception should be thrown");
        } catch (NegativeAmt negativeAmt) {

        }
    }

    @Test
    void testGetTransDetail() {
        assertEquals(("\n|| Description: " + DESC + "            Amount: " + AMOUNT + " ||"),
                transaction.getTransDetail());
        assertEquals(("\n|| Description: " + DESC + "            Amount: " + AMOUNT
                + "  Source:" + SOURCE), transaction2.getTransDetail());

    }

    @Test
    void testGetSource(){
        assertEquals(SOURCE, transaction2.getSource());
    }

    @Test
    void testGetTrans() {
        assertEquals(transaction, transaction.getTrans());
    }


    @Test
    void testGetAmount() {
        assertEquals(transaction.getAmount(), AMOUNT);
    }

    @Test
    void testGetDesc() {
        assertEquals(transaction.getDesc(), DESC);
    }

    @Test
    void testSetAmount() {
        transaction.setAmount(550);
        assertEquals(transaction.getAmount(), 550);
    }

    @Test
    void testSetDesc() {
        transaction.setDesc("NEW");
        assertEquals(transaction.getDesc(), "NEW");
    }

    @Test
    void addUnexcepTran() {
        transaction2.addUnexcepTran(123, "123", transaction);
        assertEquals(transaction, transaction2.getTrans());
    }

    @Test
    void addDevice() {
        transaction2.addDevice("phone");
    }
}