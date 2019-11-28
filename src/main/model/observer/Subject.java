package model.observer;

import model.trantype.Transaction;

import java.util.ArrayList;
import java.util.List;

public class Subject {
    private List<MessageNotification> observers = new ArrayList<>();

    //MODIFIES: this
    // EFFECTS: adds observer to observers
    public void addObserver(MessageNotification observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    //EFFECTS: calls update on every observer in observers
    public void notifyObservers(Transaction t) {
        for (MessageNotification observer : observers) {
            observer.update(t);
        }
    }

    // EFFECTS: returns if observers contains specific msg.
    public boolean contains(MessageNotification msg) {
        return observers.contains(msg);
    }
}
