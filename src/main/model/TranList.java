package model;

import model.trantype.Transaction;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;

public abstract class TranList {
    private ArrayList<Transaction> tranList;

    //EFFECTS: income list is empty
    public TranList() throws IOException {
        tranList = new ArrayList<>();
    }

    //MODIFIES: this
    //EFFECTS: Transaction trans is added to the income list
    public void insert(Transaction trans) {
        tranList.add(trans);
    }

    //MODIFIES: this
    //EFFECTS: Element i is deleted from  the income list
    public void delete(int i) {
        tranList.remove(this.getTrans(i));
    }

    // EFFECTS: returns size of income list
    public int getSize() {
        return tranList.size();
    }

    // REQUIRES: the int i must be a valid index of the array
    // EFFECTS: returns the transaction at index i
    public Transaction getTrans(int i) {
        return tranList.get(i);
    }

    // EFFECTS: Returns true if Transaction trans is in the Income list
    // and false otherwise
    public boolean contains(Transaction trans) {
        return tranList.contains(trans);
    }

    public abstract void saveData() throws FileNotFoundException, UnsupportedEncodingException;

    public abstract void loadData();

    // EFFECTS: returns the array list which has been split on ~~.
    public ArrayList<String> splitOnChar(String line) {
        String[] splits = line.split("~~");
        return new ArrayList<>(Arrays.asList(splits));
    }

}
