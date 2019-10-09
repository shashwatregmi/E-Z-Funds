package modeltest;
import model.Debt;
import model.Investment;
import model.Loadable;
import model.LongTermList;
import model.trantype.LongTermTran;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class InvestTest {

    private LongTermList investList;
    private LongTermTran trans;

    @Test
    public void testLoadData() throws IOException {
        Investment investLoad = new Investment();
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
    public void testSaveData() throws IOException {
        Investment investSave = new Investment();
        LongTermTran tran = new LongTermTran(456, "Test2", 12, 0.99);
        LongTermTran tranNew = new LongTermTran(123, "Test1", 60, 3.15);
        investSave.insert(tran);
        investSave.insert(tranNew);
        investSave.saveData();
        Investment investLoad = new Investment();
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

    public void load(Loadable debt){
        debt.loadData();
    }
}
