package model.trantype;

import model.Devices;
import model.exceptions.NegativeAmt;
import model.observer.Subject;

import java.util.Objects;

public abstract class Transaction extends Subject {
    protected double amount;
    protected String desc;
    int term;
    double interestRate;
    String source;

    // MODIFIES: this
    // EFFECTS: creates new Transaction
    public Transaction(double amount, String desc) throws NegativeAmt {
        if (amount > 0) {
            this.amount = amount;
            this.desc = desc;
        } else {
            throw new NegativeAmt();
        }

        notifyObservers(this);
    }

    //EFFECTS: returns this
    public Transaction getTrans() {
        return this;
    }

    //EFFECTS: returns field amount
    public Double getAmount() {
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

    //EFFECTS: returns term
    public Integer getTerm() {
        return this.term;
    }

    //EFFECTS: return interestRate
    public Double getInterestRate() {
        return this.interestRate;
    }

    // MODIFIES: device and this
    // EFFECTS: creates new device with name s and calls addobserver to add it to observers list.
    public void addDevice(String s) {
        Devices device = new Devices(s);
        addObserver(device);
    }
}

