package model.trantype;

import model.exceptions.NegativeAmt;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UnexpectedStreamTransaction extends Transaction {

    private List<DayToDayTran> tran = new ArrayList<>();

    public UnexpectedStreamTransaction(double amount, String desc, String source) throws IOException, NegativeAmt {
        super(amount, desc);
        this.source = source;
    }

    //EFFECTS: returns formatted string for this
    @Override
    public String getTransDetail() {
        return ("\n|| Description: " + desc + "            Amount: " + amount
                + "  Source:" + source);
    }

    public String getSource() {
        return source;
    }

    // REQUIRES: amt > 0
    // MODIFIES: this
    // EFFECTS: if not contained in tran, adds dtran to it and calls upexpected on dtran as well.
    public void addUnexcepTran(double amt, String desc, DayToDayTran dtran) {
        if (!tran.contains(dtran)) {
            tran.add(dtran);
            dtran.addUnexpected(this);
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UnexpectedStreamTransaction)) {
            return false;
        }
        UnexpectedStreamTransaction that = (UnexpectedStreamTransaction) o;
        return Objects.equals(tran, that.tran);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tran);
    }

    void removeUnexcepTran(double amt, String desc, DayToDayTran dtran) {
        if (tran.contains(dtran)) {
            tran.remove(dtran);
            dtran.removeUnexpected(this);
        }

    }

    //EFFECTS: returns tran
    public List<DayToDayTran> getList() {
        return tran;
    }
}
