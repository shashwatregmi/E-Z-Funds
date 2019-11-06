package modeltest;

import model.Loadable;
import model.DayTranList;
import model.exceptions.NegativeAmt;
import model.trantype.DayToDayTran;
import model.trantype.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class IncomeTest {

    private DayTranList incomeTest;
    private Transaction trans;
    private static final int COUNT = 5;


    @BeforeEach
    public void runBefore() throws IOException {
        incomeTest = new DayTranList();
        trans = new DayToDayTran(123, "Test");
    }

    @Test
    public void testInsert() {
        incomeTest.insert(trans);
        assertTrue(incomeTest.contains(trans));
        assertEquals(1, incomeTest.getSize());
    }

    @Test
    public void testInsertLots() {
        for (int i = 0; i < COUNT; i++) {
            incomeTest.insert(trans);
        }

        for (int i = 0; i < COUNT; i++) {
            assertTrue(incomeTest.contains(trans));
            assertEquals(COUNT, incomeTest.getSize());
        }
    }

    @Test
    public void testRemove() {
        for (int i = 0; i < COUNT; i++) {
            incomeTest.insert(trans);
        }

        incomeTest.delete(COUNT - 1);
        incomeTest.delete(COUNT - 2);

        assertTrue(incomeTest.contains(trans));
        assertEquals(COUNT - 2, incomeTest.getSize());
    }

    @Test
    public void testRemoveLots() {
        for (int i = 0; i < COUNT; i++) {
            incomeTest.insert(trans);
        }

        int j = COUNT - 1;
        for (int i = j; i >= 0 ; i--) {
            incomeTest.delete(i);
        }

        assertFalse(incomeTest.contains(trans));
        assertEquals(0, incomeTest.getSize());
    }

    @Test
    public void testGetSize() {
        for (int i = 0; i < COUNT; i++) {
            incomeTest.insert(trans);
        }
        assertEquals(COUNT, incomeTest.getSize());
    }

    @Test
    public void testGetTrans() {
        incomeTest.insert(new DayToDayTran(123.22,"new"));
        incomeTest.insert(trans);
        assertEquals(trans, incomeTest.getTrans(1));
    }

    @Test
    public void testContains(){
        assertFalse(incomeTest.contains(trans));
        incomeTest.insert(trans);
        assertTrue(incomeTest.contains(trans));
    }

    @Test
    public void testContainsLots(){
        assertFalse(incomeTest.contains(trans));
        Transaction tranNew = new DayToDayTran(333, "TT");
        assertFalse(incomeTest.contains(tranNew));
        incomeTest.insert(trans);
        incomeTest.insert(tranNew);
        assertTrue(incomeTest.contains(trans));
        assertTrue(incomeTest.contains(tranNew));
    }

    @Test
    public void testLoadData() throws IOException, NegativeAmt {
        DayTranList incomeLoad = new DayTranList();
        load(incomeLoad);
        assertEquals(incomeLoad.getTrans(0).getAmount(), 100);
        assertEquals(incomeLoad.getTrans(0).getDesc(), "Test1");
        assertEquals(incomeLoad.getTrans(1).getAmount(), 200);
        assertEquals(incomeLoad.getTrans(1).getDesc(), "Test2");
    }

    @Test
    public void testSaveData() throws IOException, NegativeAmt {
        DayTranList incomeSave = new DayTranList();
        Transaction tran = new DayToDayTran(456, "Test2");
        Transaction tranNew = new DayToDayTran(123, "Test1");
        incomeSave.insert(tran);
        incomeSave.insert(tranNew);
        incomeSave.saveData("./data/Expense.txt");
        load(incomeSave);
        DayTranList incomeLoad = new DayTranList();
        load(incomeSave);
        assertEquals(incomeSave.getTrans(0).getAmount(), 456);
        assertEquals(incomeSave.getTrans(0).getDesc(), "Test2");
        assertEquals(incomeSave.getTrans(1).getAmount(), 123);
        assertEquals(incomeSave.getTrans(1).getDesc(), "Test1");
        load(incomeLoad);
        assertEquals(incomeLoad.getTrans(0).getAmount(), 456);
        assertEquals(incomeLoad.getTrans(0).getDesc(), "Test2");
        assertEquals(incomeLoad.getTrans(1).getAmount(), 123);
        assertEquals(incomeLoad.getTrans(1).getDesc(), "Test1");
    }

    public void load(Loadable income) throws NegativeAmt, IOException {
        income.loadData("./data/Expense.txt");
    }

}
