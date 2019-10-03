package model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

// Represents a array list of incomes

public class Income implements Loadable, Savable {
    private List<String> incomeRead = Files.readAllLines(Paths.get("./data/Income.txt"));
    private ArrayList<Transaction> incomeList;

    //EFFECTS: income list is empty
    public Income() throws IOException {
        incomeList = new ArrayList<>();
    }

    //MODIFIES: this
    //EFFECTS: Transaction trans is added to the income list
    public void insert(Transaction trans) {
        incomeList.add(trans);
    }

    //MODIFIES: this
    //EFFECTS: Element i is deleted from  the income list
    public void delete(int i) {
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

    // MODIFIES: incomeWriter
    // EFFECTS: writes contents in this to incomeWriter which writes it to appropriate file
    public void saveData() throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter incomeWriter = new PrintWriter("./data/Income.txt","UTF-8");
        for (int i = 0; i < this.getSize(); i++) {
            incomeWriter.println(this.getTrans(i).getDesc() + "~~" + this.getTrans(i).getAmount());
        }
        incomeWriter.close();
    }

    // REQUIRES: that the file being read has description and amount seperated with ~~.
    // MODIFIES: this and incomeRead
    // EFFECTS: loads all lines from array passed in into this for user usage.
    public void loadData() {
        for (String line : incomeRead) {
            ArrayList<String> partsOfLine = splitOnChar(line);
            String desc = partsOfLine.get(0);
            double amount = Double.parseDouble(partsOfLine.get(1));
            Transaction loadTransaction = new Transaction(amount, desc);
            this.insert(loadTransaction);
        }
    }

    // EFFECTS: returns the array list which has been split on ~~.
    private static ArrayList<String> splitOnChar(String line) {
        String[] splits = line.split("~~");
        return new ArrayList<>(Arrays.asList(splits));
    }

}
