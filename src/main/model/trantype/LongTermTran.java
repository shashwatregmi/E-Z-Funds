package model.trantype;

import model.exceptions.NegativeAmt;

public class LongTermTran extends Transaction {

    // REQUIRES: amount, term, interestRate must be > 0
    // MODIFIES: this
    // EFFECTS: creates new LongTermTran
    public LongTermTran(double amount, String desc, int term, double interestRate) throws NegativeAmt {
        super(amount, desc);
        this.term = term;
        this.interestRate = interestRate;
    }

    //EFFECTS: returns formatted string for this and notify all observers.
    @Override
    public String getTransDetail() {
        notifyObservers(this);
        return ("\n|| Description: " + desc + "            Amount: " + amount
                + "  Interest Rate:" + interestRate + "  Term:" + term + " ||");
    }

    // EFFECTS: returns amount with interest
    public double getAmount(int i) {
        return super.getAmount() * ((this.getInterestRate() / 100) + 1);
    }

    // EFFECTS: returns amount with interest due in negative
    public double getAmount(String s) {
        return (super.getAmount() * ((this.getInterestRate() / 100) + 1)) * -1;
    }


}
