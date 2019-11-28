package model.observer;

import model.trantype.Transaction;

public interface MessageNotification {
    void update(Transaction t);
}
