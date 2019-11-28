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
    //private ArrayList<LongTermTran> tranList;

    //EFFECTS: income list is empty
    public LongTermList() throws IOException {
        super();
    }

    @Override
    // REQUIRES: valid path be passed in.
    // EFFECTS: writes transaction details into specified path file.
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
    // REQUIRES: valid file path
    // MODIFIES: this
    // EFFECTS: reads file in path and parses each line. Saves each tran in Transaction list.
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
    private ArrayList<String> splitOnChar(String line) {
        String[] splits = line.split("~~~");
        return new ArrayList<>(Arrays.asList(splits));
    }

}
