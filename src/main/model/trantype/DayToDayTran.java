package model.trantype;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DayToDayTran extends Transaction {

    public List<UnexpectedStream> unexpected = new ArrayList<>();

    // REQUIRES: amount must be > 0
    // MODIFIES: this
    // EFFECTS: creates new Transaction
    public DayToDayTran(double amount, String desc) {
        super(amount, desc);
    }

    @Override
    //EFFECTS: returns formatted string for this
    public String getTransDetail() {
        return ("\n|| Description: " + desc + "            Amount: " + amount + " ||");
    }

    public void addUnexpected(UnexpectedStream us) {
        if (!unexpected.contains(us)) {
            unexpected.add(us);
            us.addUnexcepTran(us.getAmount(), us.getTransDetail(), this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DayToDayTran)) {
            return false;
        }
        DayToDayTran that = (DayToDayTran) o;
        return Objects.equals(unexpected, that.unexpected);
    }

    @Override
    public int hashCode() {
        return Objects.hash(unexpected);
    }

    public void removeUnexpected(UnexpectedStream us) {
        if (!unexpected.contains(us)) {
            unexpected.add(us);
            us.removeUnexcepTran(us.getAmount(), us.getTransDetail(), this);
        }
    }
}
