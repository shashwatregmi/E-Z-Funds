package model;

import model.exceptions.NegativeAmt;
import model.trantype.DayToDayTran;
import model.trantype.LongTermTran;
import model.trantype.Transaction;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class LongTermList extends TranList implements Savable, Loadable {
    private ArrayList<LongTermTran> tranList;

    //EFFECTS: income list is empty
    public LongTermList() throws IOException {
        tranList = new ArrayList<LongTermTran>();
    }

    //MODIFIES: this
    //EFFECTS: Transaction trans is added to the income list
    public void insert(LongTermTran trans) {
        tranList.add(trans);
    }

    @Override
    public void insert(Transaction trans) {
        return;
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
    public void saveData(String path) throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter writer = new PrintWriter(path);
        for (int i = 0; i < this.getSize(); i++) {
            print(writer, i);
        }
        writer.close();
    }

    private void print(PrintWriter writer, int i) {
        writer.println(this.getTrans(i).getDesc() + "~~~" + this.getTrans(i).getAmount()
                + "~~~" + this.getTrans(i).getTerm() + "~~~" + this.getTrans(i).getInterestRate());
    }


    //@Override
    public void loadData(String path) throws NegativeAmt, IOException {
        List<String> read = Files.readAllLines(Paths.get(path));
        for (String line : read) {
            ArrayList<String> partsOfLine = splitOnChar(line);
            String desc = partsOfLine.get(0);
            double amount = Double.parseDouble(partsOfLine.get(1));
            int term = Integer.parseInt(partsOfLine.get(2));
            double rate = Double.parseDouble(partsOfLine.get(3));
            LongTermTran loadTransaction = new LongTermTran(amount, desc, term, rate);
            this.insert(loadTransaction);
        }
    }



    // EFFECTS: returns the array list of Strings which has been split on ~~~.
    public ArrayList<String> splitOnChar(String line) {
        String[] splits = line.split("~~~");
        return new ArrayList<>(Arrays.asList(splits));
    }

}
