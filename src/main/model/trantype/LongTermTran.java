package model.trantype;

public class LongTermTran extends Transaction {


    // REQUIRES: amount, term, interestRate must be > 0
    // MODIFIES: this
    // EFFECTS: creates new Debt
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

    //EFFECTS: return interestRate
    public double getInterestRate() {
        return this.interestRate;
    }

}
