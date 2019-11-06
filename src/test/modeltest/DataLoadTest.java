package modeltest;

import model.LongTermList;
import model.exceptions.NegativeAmt;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.fail;

public class DataLoadTest {

    @Test
    public void testPositive() throws IOException, NegativeAmt {
        LongTermList investLoad = new LongTermList();
        try {
            load(investLoad);
        } catch (NegativeAmt neg) {
            fail("Exception not Expected.");
        }
    }

    @Test
    public void testNegative() throws IOException, NegativeAmt {
        LongTermList debtLoad = new LongTermList();
        try {
            load(debtLoad);
            fail("exception should have been thrown");
        } catch (NegativeAmt neg) {
            System.out.println("good");
        }
    }

    private void load(LongTermList load) throws NegativeAmt, IOException {
        load.loadData("./data/Expense.txt");
    }
}
