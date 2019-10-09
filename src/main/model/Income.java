package model;

import model.trantype.DayToDayTran;
import model.trantype.Transaction;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

// Represents a array list of incomes

public class Income extends TranList implements Loadable, Savable {
    private List<String> incomeRead = Files.readAllLines(Paths.get("./data/Income.txt"));

    public Income() throws IOException {
        super();
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
            Transaction loadTransaction = new DayToDayTran(amount, desc);
            this.insert(loadTransaction);
        }
    }



}
