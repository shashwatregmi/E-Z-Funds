package model;

import java.util.ArrayList;

// Represents a array list of incomes

public class Income {
    private ArrayList<Transaction> incomeList;

    //EFFECTS: income list is empty
    public Income() {
        incomeList = new ArrayList<>();
    }

    //MODIFIES: this
    //EFFECTS: Transaction trans is added to the income list
    public void insert(Transaction trans) {
        incomeList.add(trans);
    }

    //MODIFIES: this
    //EFFECTS: Element i is deleted from  the income list
    public void delete(Integer i) {
        incomeList.remove(this.getTrans(i));
    }

    // EFFECTS: returns size of income list
    public int getSize() {
        return incomeList.size();
    }

    // REQUIRES: the int i must be a valid index of the array
    // EFFECTS: returns the transaction at index i
    public Transaction getTrans(int i) {
        return incomeList.get(i);
    }

    // EFFECTS: Returns true if Transaction trans is in the Income list
    // and false otherwise
    public boolean contains(Transaction trans) {
        return incomeList.contains(trans);
    }

    // EFFECTS: prints out detail of every element in
    //          income list in a formatted manner.
    public void incomeReport() {
        System.out.println("-- INCOME --");
        for (int i = 0; i < this.getSize(); i++) {
            System.out.println(this.getTrans(i).getTransDetail());
        }
    }
}
