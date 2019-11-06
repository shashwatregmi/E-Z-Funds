package modeltest;
import model.Loadable;
import model.LongTermList;
import model.exceptions.NegativeAmt;
import model.trantype.LongTermTran;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class InvestTest {

    private LongTermList investList;
    private LongTermTran trans;
    private static final int COUNT = 5;


    @Test
    public void testLoadData() throws IOException, NegativeAmt {
        LongTermList investLoad = new LongTermList();
        load(investLoad);
        assertEquals(investLoad.getTrans(0).getAmount(), 100);
        assertEquals(investLoad.getTrans(0).getDesc(), "Test1");
        assertEquals(investLoad.getTrans(0).getTerm(), 1);
        assertEquals(investLoad.getTrans(0).getInterestRate(), 1);
        assertEquals(investLoad.getTrans(1).getAmount(), 200);
        assertEquals(investLoad.getTrans(1).getDesc(), "Test2");
        assertEquals(investLoad.getTrans(1).getTerm(), 2);
        assertEquals(investLoad.getTrans(1).getInterestRate(), 2);
    }

    @Test
    public void testSaveData() throws IOException, NegativeAmt {
        LongTermList investSave = new LongTermList();
        LongTermTran tran = new LongTermTran(456, "Test2", 12, 0.99);
        LongTermTran tranNew = new LongTermTran(123, "Test1", 60, 3.15);
        investSave.insert(tran);
        investSave.insert(tranNew);
        investSave.saveData("./data/Expense.txt");
        LongTermList investLoad = new LongTermList();
        load(investLoad);
        load(investSave);
        assertEquals(investSave.getTrans(0).getAmount(), 456);
        assertEquals(investSave.getTrans(0).getDesc(), "Test2");
        assertEquals(investSave.getTrans(0).getTerm(), 12);
        assertEquals(investSave.getTrans(0).getInterestRate(), 0.99);
        assertEquals(investSave.getTrans(1).getAmount(), 123);
        assertEquals(investSave.getTrans(1).getDesc(), "Test1");
        assertEquals(investSave.getTrans(1).getTerm(), 60);
        assertEquals(investSave.getTrans(1).getInterestRate(), 3.15);

        assertEquals(investLoad.getTrans(0).getAmount(), 456);
        assertEquals(investLoad.getTrans(0).getDesc(), "Test2");
        assertEquals(investLoad.getTrans(0).getTerm(), 12);
        assertEquals(investLoad.getTrans(0).getInterestRate(), 0.99);
        assertEquals(investLoad.getTrans(1).getAmount(), 123);
        assertEquals(investLoad.getTrans(1).getDesc(), "Test1");
        assertEquals(investLoad.getTrans(1).getTerm(), 60);
        assertEquals(investLoad.getTrans(1).getInterestRate(), 3.15);
    }

    public void load(Loadable invest) throws NegativeAmt, IOException {
        invest.loadData("./data/Expense.txt");
    }

    @Test
    public void testRemove() throws NegativeAmt, IOException {
        investList = new LongTermList();
        trans = new LongTermTran(123,"123",123,123);
        for (int i = 0; i < COUNT; i++) {
            investList.insert(trans);
        }

        investList.delete(COUNT - 1);
        investList.delete(COUNT - 2);

        assertTrue(investList.contains(trans));
        assertEquals(COUNT - 2, investList.getSize());
    }

    @Test
    public void testRemoveLots() throws NegativeAmt, IOException {
        investList = new LongTermList();
        trans = new LongTermTran(123,"123",123,123);
        for (int i = 0; i < COUNT; i++) {
            investList.insert(trans);
        }

        int j = COUNT - 1;
        for (int i = j; i >= 0 ; i--) {
            investList.delete(i);
        }

        assertFalse(investList.contains(trans));
        assertEquals(0, investList.getSize());
    }
}
