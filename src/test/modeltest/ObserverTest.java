package modeltest;

import model.Devices;
import model.exceptions.NegativeAmt;
import model.observer.Subject;
import model.trantype.DayToDayTran;
import model.trantype.Transaction;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class ObserverTest {
    Subject subject = new Subject();
    Devices devices = new Devices("Phone");

    @Test
    public void testAdd(){
        subject.addObserver(devices);
        assertTrue(subject.contains(devices));
    }

    @Test
    public void update() throws NegativeAmt {
        Devices dev2 = new Devices("new");
        subject.addObserver(dev2);
        Transaction t = new DayToDayTran(123, "new");
        subject.notifyObservers(t);
    }
}
