package model.trantype;

import model.Debt;
import model.Investment;

public class LongTermTran extends Transaction {

    // REQUIRES: amount, term, interestRate must be > 0
    // MODIFIES: this
    // EFFECTS: creates new LongTermTran
    public LongTermTran(double amount, String desc, int term, double interestRate) {
        super(amount, desc);
        this.term = term;
        this.interestRate = interestRate;
    }

    //EFFECTS: returns formatted string for this
    @Override
    public String getTransDetail() {
        return ("\n|| Description: " + desc + "            Amount: " + amount
                + "  Interest Rate:" + interestRate + "  Term:" + term + " ||");
    }

    //EFFECTS: returns term
    public int getTerm() {
        return this.term;
    }

    // EFFECTS: returns amount with interest
    public double getAmount(int i) {
        return super.getAmount() * ((this.getInterestRate() / 100) + 1);
    }

    // EFFECTS: returns amount with interest due in negative
    public double getAmount(String s) {
        return (super.getAmount() * ((this.getInterestRate() / 100) + 1)) * -1;
    }

    //EFFECTS: return interestRate
    public double getInterestRate() {
        return this.interestRate;
    }

}
