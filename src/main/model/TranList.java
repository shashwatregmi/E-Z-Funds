package model;

import model.exceptions.NegativeAmt;
import model.trantype.Transaction;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;

public abstract class TranList implements Savable, Loadable {
    private ArrayList<Transaction> tranList;

    //EFFECTS: list is empty
    public TranList() throws IOException {
        tranList = new ArrayList<>();
    }

    //MODIFIES: this
    //EFFECTS: Transaction trans is added to the list
    public void insert(Transaction trans) {
        tranList.add(trans);
    }

    //MODIFIES: this
    //EFFECTS: Element i is deleted from  the list
    public void delete(int i) {
        tranList.remove(this.getTrans(i));
    }

    // EFFECTS: returns size of list
    public int getSize() {
        return tranList.size();
    }

    // REQUIRES: the int i must be a valid index of the array
    // EFFECTS: returns the transaction at index i
    public Transaction getTrans(int i) {
        return tranList.get(i);
    }

    // EFFECTS: Returns true if Transaction trans is in the list
    // and false otherwise
    public boolean contains(Transaction trans) {
        return tranList.contains(trans);
    }

    @Override
    public abstract void saveData() throws FileNotFoundException, UnsupportedEncodingException;

    @Override
    public abstract void loadData() throws NegativeAmt;

    // EFFECTS: returns the array list which has been split on ~~.
    public ArrayList<String> splitOnChar(String line) {
        String[] splits = line.split("~~");
        return new ArrayList<>(Arrays.asList(splits));
    }

}
