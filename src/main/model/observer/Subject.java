package model.observer;

import model.trantype.Transaction;

import java.util.ArrayList;
import java.util.List;

public class Subject {
    private List<MessageNotification> observers = new ArrayList<>();

    public void addObserver(MessageNotification observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    public void notifyObservers(Transaction t) {
        for (MessageNotification observer : observers) {
            observer.update(t);
        }
    }

    public boolean contains(MessageNotification msg) {
        return observers.contains(msg);
    }
}
