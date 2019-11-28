package model;

import model.exceptions.NegativeAmt;
import model.trantype.LongTermTran;
import model.trantype.Transaction;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public abstract class TranList {
    private ArrayList<Transaction> tranList;

    TranList() {
        tranList = new ArrayList<>();
    }

    //MODIFIES: this
    //EFFECTS: Transaction trans is added to the income list
    public void insert(LongTermTran trans) {
        tranList.add(trans);
    }

    //MODIFIES: this
    //EFFECTS: Transaction trans is added to the list
    public  void insert(Transaction trans) {
        tranList.add(trans);
    }

    public abstract void saveData(String path) throws FileNotFoundException, UnsupportedEncodingException;

    public abstract void loadData(String path) throws FileNotFoundException, UnsupportedEncodingException,
            NegativeAmt, IOException;

    // EFFECTS: returns size of income list
    public int getSize() {
        return tranList.size();
    }

    //MODIFIES: this
    //EFFECTS: Element i is deleted from  the list
    public void delete(int i) {
        //tranList.remove(this.getTrans(i));
        tranList.remove(i);
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

}
