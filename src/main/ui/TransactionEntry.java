package ui;

import model.TranList;
import model.exceptions.NegativeAmt;

import java.util.Scanner;

public class TransactionEntry {
    private Scanner scan = new Scanner(System.in).useDelimiter("\\n");


    // EFFECTS: setup title
    void setup(String type) {
        System.out.print("\n--" + type + " Entry Mode--\n");
    }

    // EFFECTS: takes in user input and returns value
    protected double amtEntry() throws NegativeAmt {
        double amount = 0;
        System.out.println("Please enter the amount:");
        amount = scan.nextDouble();
        if (amount < 0) {
            throw new NegativeAmt();
        }
        return amount;
    }

    // EFFECTS: takes in user input and returns value
    protected String descEntry() {
        System.out.println("Please enter a description for this transaction:");
        return scan.next();
    }


}
