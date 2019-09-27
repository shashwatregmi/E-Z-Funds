package modeltest;

import model.Income;
import model.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class IncomeTest {

    private Income incomeTest;
    private Transaction trans;
    private static final int COUNT = 5;


    @BeforeEach
    public void runBefore() {
        incomeTest = new Income();
        trans = new Transaction(123, "Test");
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

        incomeTest.remove(COUNT - 1);
        incomeTest.remove(COUNT - 2);

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
            incomeTest.remove(i);
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
        incomeTest.insert(new Transaction(123.22,"new"));
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
        Transaction tranNew = new Transaction(333, "TT");
        assertFalse(incomeTest.contains(tranNew));
        incomeTest.insert(trans);
        incomeTest.insert(tranNew);
        assertTrue(incomeTest.contains(trans));
        assertTrue(incomeTest.contains(tranNew));
    }
}
