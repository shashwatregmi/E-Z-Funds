package model;

import model.trantype.LongTermTran;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Debt extends LongTermList {
    private List<String> reader = Files.readAllLines(Paths.get("./data/Debt.txt"));


    public Debt() throws IOException {
        super();
    }

    // EFFECTS: writes contents in this to writer which writes it to appropriate file
    public void saveData() throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter writer = new PrintWriter("./data/Debt.txt","UTF-8");
        for (int i = 0; i < this.getSize(); i++) {
            writer.println(this.getTrans(i).getDesc() + "~~~" + this.getTrans(i).getAmount()
                    + "~~~" + this.getTrans(i).getTerm() + "~~~" + this.getTrans(i).getInterestRate());
        }
        writer.close();
    }

    // REQUIRES: that the file being read has description and amount seperated with ~~~.
    // MODIFIES: this and reader
    // EFFECTS: loads all lines from array passed in into this for user usage.
    public void loadData() {
        for (String line : reader) {
            ArrayList<String> partsOfLine = splitOnChar(line);
            String desc = partsOfLine.get(0);
            double amount = Double.parseDouble(partsOfLine.get(1));
            int term = Integer.parseInt(partsOfLine.get(2));
            double rate = Double.parseDouble(partsOfLine.get(3));
            LongTermTran loadTransaction = new LongTermTran(amount, desc, term, rate);
            this.insert(loadTransaction);
        }
    }
}
