package model.observer;

import model.trantype.Transaction;

public interface MessageNotification {
    public void update(Transaction t);
}
