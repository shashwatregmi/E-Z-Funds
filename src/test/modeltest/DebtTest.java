package modeltest;
import model.Debt;
import model.Expense;
import model.Loadable;
import model.LongTermList;
import model.trantype.DayToDayTran;
import model.trantype.LongTermTran;
import model.trantype.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DebtTest {

    private LongTermList debtTest;
    private LongTermTran trans;
    private static final int COUNT = 5;

    @BeforeEach
    public void runBefore() throws IOException {
        debtTest = new Debt();
        trans = new LongTermTran(123, "Test", 1, 1);
    }

    @Test
    public void testInsert() {
        debtTest.insert(trans);
        assertTrue(debtTest.contains(trans));
        assertEquals(1, debtTest.getSize());
    }

    @Test
    public void testInsertLots() {
        for (int i = 0; i < COUNT; i++) {
            debtTest.insert(trans);
        }

        for (int i = 0; i < COUNT; i++) {
            assertTrue(debtTest.contains(trans));
            assertEquals(COUNT, debtTest.getSize());
        }
    }

    @Test
    public void testRemove() {
        for (int i = 0; i < COUNT; i++) {
            debtTest.insert(trans);
        }

        debtTest.delete(COUNT - 1);
        debtTest.delete(COUNT - 2);

        assertTrue(debtTest.contains(trans));
        assertEquals(COUNT - 2, debtTest.getSize());
    }

    @Test
    public void testRemoveLots() {
        for (int i = 0; i < COUNT; i++) {
            debtTest.insert(trans);
        }

        int j = COUNT - 1;
        for (int i = j; i >= 0 ; i--) {
            debtTest.delete(i);
        }

        assertFalse(debtTest.contains(trans));
        assertEquals(0, debtTest.getSize());
    }

    @Test
    public void testGetSize() {
        for (int i = 0; i < COUNT; i++) {
            debtTest.insert(trans);
        }
        assertEquals(COUNT, debtTest.getSize());
    }

    @Test
    public void testGetTrans() {
        debtTest.insert(new LongTermTran(123.22,"new", 1, 1));
        debtTest.insert(trans);
        assertEquals(trans, debtTest.getTrans(1));
    }

    @Test
    public void testContains(){
        assertFalse(debtTest.contains(trans));
        debtTest.insert(trans);
        assertTrue(debtTest.contains(trans));
    }

    @Test
    public void testContainsLots(){
        assertFalse(debtTest.contains(trans));
        LongTermTran tranNew = new LongTermTran(333, "TT", 1 ,1);
        assertFalse(debtTest.contains(tranNew));
        debtTest.insert(trans);
        debtTest.insert(tranNew);
        assertTrue(debtTest.contains(trans));
        assertTrue(debtTest.contains(tranNew));
    }

    @Test
    public void testLoadData() throws IOException {
        Debt debtLoad = new Debt();
        load(debtLoad);
        assertEquals(debtLoad.getTrans(0).getAmount(), 100);
        assertEquals(debtLoad.getTrans(0).getDesc(), "Test1");
        assertEquals(debtLoad.getTrans(0).getTerm(), 1);
        assertEquals(debtLoad.getTrans(0).getInterestRate(), 1);
        assertEquals(debtLoad.getTrans(1).getAmount(), 200);
        assertEquals(debtLoad.getTrans(1).getDesc(), "Test2");
        assertEquals(debtLoad.getTrans(1).getTerm(), 2);
        assertEquals(debtLoad.getTrans(1).getInterestRate(), 2);
    }

    @Test
    public void testSaveData() throws IOException {
        Debt debtSave = new Debt();
        LongTermTran tran = new LongTermTran(456, "Test2", 12, 0.99);
        LongTermTran tranNew = new LongTermTran(123, "Test1", 60, 3.15);
        debtSave.insert(tran);
        debtSave.insert(tranNew);
        debtSave.saveData();
        Debt debtLoad = new Debt();
        load(debtLoad);
        load(debtSave);
        assertEquals(debtSave.getTrans(0).getAmount(), 456);
        assertEquals(debtSave.getTrans(0).getDesc(), "Test2");
        assertEquals(debtSave.getTrans(0).getTerm(), 12);
        assertEquals(debtSave.getTrans(0).getInterestRate(), 0.99);
        assertEquals(debtSave.getTrans(1).getAmount(), 123);
        assertEquals(debtSave.getTrans(1).getDesc(), "Test1");
        assertEquals(debtSave.getTrans(1).getTerm(), 60);
        assertEquals(debtSave.getTrans(1).getInterestRate(), 3.15);

        assertEquals(debtLoad.getTrans(0).getAmount(), 456);
        assertEquals(debtLoad.getTrans(0).getDesc(), "Test2");
        assertEquals(debtLoad.getTrans(0).getTerm(), 12);
        assertEquals(debtLoad.getTrans(0).getInterestRate(), 0.99);
        assertEquals(debtLoad.getTrans(1).getAmount(), 123);
        assertEquals(debtLoad.getTrans(1).getDesc(), "Test1");
        assertEquals(debtLoad.getTrans(1).getTerm(), 60);
        assertEquals(debtLoad.getTrans(1).getInterestRate(), 3.15);
    }

    public void load(Loadable debt){
        debt.loadData();
    }
}
