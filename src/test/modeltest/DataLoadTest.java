package modeltest;

import com.sun.org.apache.xpath.internal.operations.Neg;
import model.Debt;
import model.Investment;
import model.LongTermList;
import model.exceptions.NegativeAmt;
import model.trantype.LongTermTran;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.fail;

public class DataLoadTest {

    @Test
    public void testPositive() throws IOException, NegativeAmt {
        Investment investLoad = new Investment();
        try {
            load(investLoad);
        } catch (NegativeAmt neg) {
            fail("Exception not Expected.");
        }
    }

    @Test
    public void testNegative() throws IOException, NegativeAmt {
        Debt debtLoad = new Debt();
        try {
            load(debtLoad);
        } catch (NegativeAmt neg) {
            System.out.println("good");
        }
    }

    private void load(LongTermList load) throws NegativeAmt {
        load.loadData();
    }
}
