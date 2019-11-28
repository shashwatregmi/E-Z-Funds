package model;

import model.observer.MessageNotification;
import model.trantype.Transaction;

public class Devices implements MessageNotification {

    private String name;

    public Devices(String name) {
        this.name = name;
    }

    @Override
    // EFFECTS: system message printed out if high value transaction spotted.
    public void update(Transaction t) {
        if (t.getAmount() > 1000) {
            System.out.println("High value transaction alert!!");
        }
    }
}
