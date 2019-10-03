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

// Represents a array list of expenses

public class Expense implements Savable, Loadable {
    private List<String> expenseRead = Files.readAllLines(Paths.get("./data/Expense.txt"));
    private ArrayList<Transaction> expenseList;

    //EFFECTS: expense list is empty
    public Expense() throws IOException {
        expenseList = new ArrayList<>();
    }

    //MODIFIES: this
    //EFFECTS: Transaction trans is added to the expense list
    public void insert(Transaction trans) {
        expenseList.add(trans);
    }

    //MODIFIES: this
    //EFFECTS: Element i is deleted from  the income list
    public void delete(int i) {
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

    // MODIFIES: expenseWriter
    // EFFECTS: writes contents in this to expenseWriter which writes it to appropriate file
    public void saveData() throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter expenseWriter = new PrintWriter("./data/Expense.txt","UTF-8");
        for (int i = 0; i < this.getSize(); i++) {
            expenseWriter.println(this.getTrans(i).getDesc() + "~~" + this.getTrans(i).getAmount());
        }
        expenseWriter.close();
    }

    // REQUIRES: that the file being read has description and amount seperated with ~~.
    // MODIFIES: this and expense Read
    // EFFECTS: loads all lines from array passed in into this for user usage.
    public void loadData() {
        for (String line : expenseRead) {
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
