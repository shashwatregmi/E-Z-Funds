package model.trantype;

import model.exceptions.NegativeAmt;

import java.util.Objects;

public abstract class Transaction {
    protected double amount;
    protected String desc;
    protected int term;
    protected double interestRate;
    protected String source;

    // MODIFIES: this
    // EFFECTS: creates new Transaction
    public Transaction(double amount, String desc) throws NegativeAmt {
        if (amount > 0) {
            this.amount = amount;
            this.desc = desc;
        } else {
            throw new NegativeAmt();
        }
    }

    //EFFECTS: returns this
    public Transaction getTrans() {
        return this;
    }

    //EFFECTS: returns field amount
    public double getAmount() {
        return this.amount;
    }

    // EFFECTS: returns all fields  of this
    public abstract String getTransDetail();

    //EFFECTS: return field description
    public String getDesc() {
        return this.desc;
    }

    // MODIFIES: this
    // EFFECTS: sets field to passed in variable
    public void setDesc(String desc) {
        this.desc = desc;
    }

    // MODIFIES: this
    // EFFECTS: sets field to passed in variable
    public void setAmount(double amount) {
        this.amount = amount;
    }

}

