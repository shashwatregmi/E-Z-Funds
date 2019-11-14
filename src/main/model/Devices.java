package model;

import model.observer.MessageNotification;
import model.trantype.Transaction;

public class Devices implements MessageNotification {

    String name;

    public Devices(String name) {
        this.name = name;
    }

    @Override
    public void update(Transaction t) {
        if (t.getAmount() > 1000) {
            System.out.println("High value transaction alert!!");
        }
    }
}
