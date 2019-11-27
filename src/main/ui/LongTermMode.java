package ui;

import model.LongTermList;
import model.TranList;
import model.exceptions.NegativeAmt;
import model.trantype.LongTermTran;
import ui.exceptions.OutOfBounds;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class LongTermMode extends TransactionEntry {
    private Scanner scan = new Scanner(System.in).useDelimiter("\\n");
    protected TranList debtList;
    protected TranList investList;
    protected Map<String, TranList> transactions = new HashMap<>();

    public LongTermMode() throws IOException {
        investList = new LongTermList();
        debtList = new LongTermList();
        transactions.put("Investment", investList);
        transactions.put("Debt", debtList);
    }

    protected void load() throws IOException, NegativeAmt {
        investList = transactions.get("Investment");
        debtList = transactions.get("Debt");
        investList.loadData("./data/Invest.txt");
        debtList.loadData("./data/Debt.txt");
    }

    //EFFECTS: prints all transactions from transactions based on input in a reportable fashion
    private void specificReport(String name) {
        System.out.println("\n-- " + name + " --");
        TranList rep = transactions.get(name);
        for (int i = 0; i < rep.getSize(); i++) {
            System.out.println(rep.getTrans(i).getTransDetail());
        }
    }

    // EFFECTS: displays the report of the LongTerm transactions by calling respective functions
    void ltReport() {
        specificReport("Investment");
        specificReport("Debt");
        System.out.println("\nPress any key to return to main menu.");
        String choice = scan.next();
    }

    // EFFECTS:depending on user input calls function that enters new Long term transaction, edits it or displays report
    void ltNewEntry() throws FileNotFoundException, NegativeAmt {
        System.out.println("Press I to enter Investments or D to enter Debts or Q to quit.");
        String choice = scan.next();
        if (choice.equals("I") || choice.equals("i")) {
            entry("Investment");
        } else if (choice.equals("D") || choice.equals("d")) {
            entry("Debt");
        } else if (choice.equals("Q") || choice.equals("q")) {
            return;
        } else {
            System.out.println("That was not a valid input. Please try again.");
        }
    }

    // MODIFIES:investmentlist
    // EFFECTS:sets up investment and takes user input from user. Then setups new transaction and puts in investmentlist
    private void entry(String name) throws NegativeAmt {
        setup(name);
        double amount = amtEntry();
        String desc = descEntry();
        int term = termEntry();
        double rate = rateEntry();
        LongTermTran transaction = new LongTermTran(amount, desc, term, rate);
        TranList list = transactions.get(name);
        list.insert(transaction);
        System.out.println(transaction.getTransDetail());
    }

    // EFFECTS: takes in user input and returns value
    private Integer termEntry() throws NegativeAmt {
        int term = 0;
        System.out.println("Please enter the term for this transaction:");
        term = scan.nextInt();
        if (term < 0) {
            throw new NegativeAmt();
        }
        return term;
    }

    // EFFECTS: takes in user input and returns value
    private Double rateEntry() throws NegativeAmt {
        double rate = 0;
        System.out.println("Please enter the Interest Rate for this transaction:");
        rate = scan.nextDouble();
        if (rate < 0) {
            throw new NegativeAmt();
        }
        return rate;
    }

    // REQUIRES: there must be a trasaction of investment/debt if it is to be deleted
    // MODIFIES: the investmentList or debtList
    // EFFECTS: displays report and then allows user to delete transactions
    void ltDelEntry() throws OutOfBounds {
        System.out.println("Would you like to delete a investment(I) or a debt(D)?");
        String choice = scan.next();
        if (choice.equals("I") || choice.equals("i")) {
           //delete("Investment");
        } else if (choice.equals("D") || choice.equals("d")) {
            //delete("Debt");
        } else {
            System.out.println("That was not a valid input.");
        }
    }

    // REQUIRES: there must be a trasaction of investment if it is to be deleted
    // MODIFIES: the investmentlist
    // EFFECTS: allows user to delete transaction
    public void delete(String name, int row) throws OutOfBounds {
        //specificReport(name);
        //System.out.println("What row would you like to delete?");
        //int row = scan.nextInt();
        TranList list = transactions.get(name);
        if (row > list.getSize()) {
            throw new OutOfBounds();
        }
        list.delete(row);
        //System.out.println("Row " + row + " has been deleted.");
    }

    public void save() throws FileNotFoundException, UnsupportedEncodingException {
        investList = transactions.get("Investment");
        debtList = transactions.get("Debt");
        investList.saveData("./data/Invest.txt");
        debtList.saveData("./data/Debt.txt");
    }
}
