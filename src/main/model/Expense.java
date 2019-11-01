package model;

import model.trantype.DayToDayTran;
import model.trantype.Transaction;
import model.exceptions.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

// Represents a array list of expenses

public class Expense extends TranList {
    private List<String> expenseRead = Files.readAllLines(Paths.get("./data/Expense.txt"));


    public Expense() throws IOException {
        super();
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
            Transaction loadTransaction = new DayToDayTran(amount, desc);
            this.insert(loadTransaction);

        }
    }
}
