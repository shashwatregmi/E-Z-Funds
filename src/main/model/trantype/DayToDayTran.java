package model.trantype;

public class DayToDayTran extends Transaction {

    // REQUIRES: amount must be > 0
    // MODIFIES: this
    // EFFECTS: creates new Transaction
    public DayToDayTran(double amount, String desc) {
        super(amount, desc);
    }

}
