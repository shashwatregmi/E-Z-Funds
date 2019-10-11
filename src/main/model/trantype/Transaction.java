package model.trantype;

public abstract class Transaction {
    protected double amount;
    protected String desc;
    protected int term;
    protected double interestRate;

    // REQUIRES: amount must be > 0
    // MODIFIES: this
    // EFFECTS: creates new Transaction
    public Transaction(double amount, String desc) {
        this.amount = amount;
        this.desc = desc;
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
    public String getTransDetail() {
        return (this.getDesc() + this.getAmount());
    }

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

