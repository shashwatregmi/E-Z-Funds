package modeltest;

import model.Expense;
import model.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Expensetest {
    private Expense expenseTest;
    private Transaction trans;
    private static final int COUNT = 5;


    @BeforeEach
    public void runBefore() {
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

        expenseTest.remove(COUNT - 1);
        expenseTest.remove(COUNT - 2);

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
            expenseTest.remove(i);
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
}
