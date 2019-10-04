package modeltest;

import model.Expense;
import model.Income;
import model.Loadable;
import model.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ExpenseTest {

    private Expense expenseTest;
    private Transaction trans;
    private static final int COUNT = 5;


    @BeforeEach
    public void runBefore() throws IOException {
        expenseTest = new Expense();
        trans = new Transaction(123, "Test");
    }

    @Test
    public void testInsert() {
        expenseTest.insert(trans);
        assertTrue(expenseTest.contains(trans));
        assertEquals(1, expenseTest.getSize());
    }

    @Test
    public void testInsertLots() {
        for (int i = 0; i < COUNT; i++) {
            expenseTest.insert(trans);
        }

        for (int i = 0; i < COUNT; i++) {
            assertTrue(expenseTest.contains(trans));
            assertEquals(COUNT, expenseTest.getSize());
        }
    }

    @Test
    public void testRemove() {
        for (int i = 0; i < COUNT; i++) {
            expenseTest.insert(trans);
        }

        expenseTest.delete(COUNT - 1);
        expenseTest.delete(COUNT - 2);

        assertTrue(expenseTest.contains(trans));
        assertEquals(COUNT - 2, expenseTest.getSize());
    }

    @Test
    public void testRemoveLots() {
        for (int i = 0; i < COUNT; i++) {
            expenseTest.insert(trans);
        }

        int j = COUNT - 1;
        for (int i = j; i >= 0 ; i--) {
            expenseTest.delete(i);
        }

        assertFalse(expenseTest.contains(trans));
        assertEquals(0, expenseTest.getSize());
    }

    @Test
    public void testGetSize() {
        for (int i = 0; i < COUNT; i++) {
            expenseTest.insert(trans);
        }
        assertEquals(COUNT, expenseTest.getSize());
    }

    @Test
    public void testGetTrans() {
        expenseTest.insert(new Transaction(123.22,"new"));
        expenseTest.insert(trans);
        assertEquals(trans, expenseTest.getTrans(1));
    }

    @Test
    public void testContains(){
        assertFalse(expenseTest.contains(trans));
        expenseTest.insert(trans);
        assertTrue(expenseTest.contains(trans));
    }

    @Test
    public void testContainsLots(){
        assertFalse(expenseTest.contains(trans));
        Transaction tranNew = new Transaction(333, "TT");
        assertFalse(expenseTest.contains(tranNew));
        expenseTest.insert(trans);
        expenseTest.insert(tranNew);
        assertTrue(expenseTest.contains(trans));
        assertTrue(expenseTest.contains(tranNew));
    }

    @Test
    public void testLoadData() throws IOException {
        Expense expenseLoad = new Expense();
        load(expenseLoad);
        assertEquals(expenseLoad.getTrans(0).getAmount(), 100);
        assertEquals(expenseLoad.getTrans(0).getDesc(), "Test1");
        assertEquals(expenseLoad.getTrans(1).getAmount(), 200);
        assertEquals(expenseLoad.getTrans(1).getDesc(), "Test2");
    }

    @Test
    public void testSaveData() throws IOException {
        Expense expenseSave = new Expense();
        Transaction tran = new Transaction(456, "Test2");
        Transaction tranNew = new Transaction(123, "Test1");
        expenseSave.insert(tran);
        expenseSave.insert(tranNew);
        expenseSave.saveData();
        load(expenseSave);
        assertEquals(expenseSave.getTrans(0).getAmount(), 456);
        assertEquals(expenseSave.getTrans(0).getDesc(), "Test2");
        assertEquals(expenseSave.getTrans(1).getAmount(), 123);
        assertEquals(expenseSave.getTrans(1).getDesc(), "Test1");
    }

    public void load(Loadable expense){
        expense.loadData();
    }
}
