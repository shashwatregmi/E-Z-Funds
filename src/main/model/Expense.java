package model;

import java.util.ArrayList;

// Represents a array list of expenses

public class Expense {
    private ArrayList<Transaction> expenseList;

    //EFFECTS: expense list is empty
    public Expense() {
        expenseList = new ArrayList<>();
    }

    //MODIFIES: this
    //EFFECTS: Transaction trans is added to the expense list
    public void insert(Transaction trans) {
        expenseList.add(trans);
    }

    //MODIFIES: this
    //EFFECTS: Element i is deleted from  the income list
    public void delete(Integer i) {
        expenseList.remove(this.getTrans(i));
    }

    // EFFECTS: returns size of income list
    public int getSize() {
        return expenseList.size();
    }

    // REQUIRES: the int i must be a valid index of the array
    // EFFECTS: returns the transaction at index i
    public Transaction getTrans(int i) {
        return expenseList.get(i);
    }

    // EFFECTS: Returns true if Transaction trans is in the Expense list
    // and false otherwise
    public boolean contains(Transaction trans) {
        return expenseList.contains(trans);
    }

    // EFFECTS: prints out detail of every element in
    //          income list in a formatted manner.
    public void expenseReport() {
        System.out.println("-- EXPENSE --");
        for (int i = 0; i < this.getSize(); i++) {
            System.out.println(this.getTrans(i).getTransDetail());
        }
    }
}
