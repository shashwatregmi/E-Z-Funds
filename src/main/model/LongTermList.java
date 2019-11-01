package model;

import model.exceptions.NegativeAmt;
import model.trantype.LongTermTran;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;

public abstract class LongTermList implements Savable, Loadable {
    private ArrayList<LongTermTran> tranList;

    //EFFECTS: income list is empty
    public LongTermList() throws IOException {
        tranList = new ArrayList<>();
    }

    //MODIFIES: this
    //EFFECTS: Transaction trans is added to the income list
    public void insert(LongTermTran trans) {
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
    public LongTermTran getTrans(int i) {
        return tranList.get(i);
    }

    // EFFECTS: Returns true if Transaction trans is in the Income list
    // and false otherwise
    public boolean contains(LongTermTran trans) {
        return tranList.contains(trans);
    }

    @Override
    public abstract void saveData() throws FileNotFoundException, UnsupportedEncodingException;

    @Override
    public abstract void loadData() throws NegativeAmt;

    // EFFECTS: returns the array list of Strings which has been split on ~~~.
    public ArrayList<String> splitOnChar(String line) {
        String[] splits = line.split("~~~");
        return new ArrayList<>(Arrays.asList(splits));
    }
}
