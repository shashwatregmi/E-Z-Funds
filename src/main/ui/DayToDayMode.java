package ui;

import model.DayTranList;
import model.TranList;
import model.exceptions.NegativeAmt;
import model.trantype.DayToDayTran;
import model.trantype.Transaction;
import ui.exceptions.OutOfBounds;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class DayToDayMode extends TransactionEntry {
    private Scanner scan = new Scanner(System.in).useDelimiter("\\n");
    protected TranList incomeList;
    protected TranList expenseList;
    protected Map<String, TranList> transactions = new HashMap<>();

    public DayToDayMode() throws IOException {
        incomeList = new DayTranList();
        expenseList = new DayTranList();
        transactions.put("Expense", incomeList);
        transactions.put("Income", expenseList);
    }

    protected void load() throws IOException, NegativeAmt {
        incomeList = transactions.get("Income");
        expenseList = transactions.get("Expense");
        incomeList.loadData("./data/Income.txt");
        expenseList.loadData("./data/Expense.txt");
    }

    // EFFECTS: depending on user input calls function that enters new DaytoDay Transaction, edits it or displays report
    void newEntry() throws FileNotFoundException, NegativeAmt {
        System.out.println("Press I to enter income or E to enter expense or Q to quit.");
        String choice = scan.next();
        if (choice.equals("I") || choice.equals("i")) {
            //entry("Income");
        } else if (choice.equals("E") || choice.equals("e")) {
            //entry("Expense");
        } else if (choice.equals("Q") || choice.equals("q")) {
            return;
        } else {
            System.out.println("That was not a valid input. Please try again.");
        }
    }

    // MODIFIES: incomelist/expenselist
    // EFFECTS: sets up income/expense and takes user input from user. Then setups new transaction and puts in proper
    // list
    public void entry(String name, String desc, Double amount) throws NegativeAmt {
        //setup(name);
        //double amount = amtEntry();
        //String desc = descEntry();
        Transaction transaction = new DayToDayTran(amount, desc);
        transaction.addDevice("Phone");
        transaction.addDevice("Laptop");
        TranList list = transactions.get(name);
        list.insert(transaction);
        System.out.println(transaction.getTransDetail());
    }

    // REQUIRES: there must be a trasaction of income/expense if it is to be deleted
    // MODIFIES: the incomelist or expenselist
    // EFFECTS: displays report and then allows user to delete transaction
    void delEntry() throws OutOfBounds {
        System.out.println("Would you like to delete a income(I) or a expense(E)?");
        String choice = scan.next();
        if (choice.equals("I") || choice.equals("i")) {
            //delete("Income");
        } else if (choice.equals("E") || choice.equals("e")) {
            //delete("Expense");
        } else {
            System.out.println("That was not a valid input.");
        }
    }

    // REQUIRES: there must be a transaction of income if it is to be deleted
    // MODIFIES: the incomelist
    // EFFECTS: allows user to delete transaction
    public void delete(String name, int row) throws OutOfBounds {
        //specificReport(name);
        //System.out.println("What row would you like to delete?");
        //int row = scan.nextInt();
        TranList list = transactions.get(name);
        list.delete(row);
        //System.out.println("Row " + row + " has been deleted.");
    }

    //EFFECTS: prints all transactions from transactions based on input in a reportable fashion
    private void specificReport(String name) {
        System.out.println("\n-- " + name + " --");
        TranList rep = transactions.get(name);
        for (int i = 0; i < rep.getSize(); i++) {
            System.out.println(rep.getTrans(i).getTransDetail());
        }
    }

    // EFFECTS: displays the report of the DaytoDay transactions by calling respective functions
    void dayReport() {
        specificReport("Income");
        specificReport("Expense");
        System.out.println("\nPress any key to return to main menu.");
        String choice = scan.next();
    }

    protected void save() throws FileNotFoundException, UnsupportedEncodingException {
        incomeList = transactions.get("Income");
        expenseList = transactions.get("Expense");
        incomeList.saveData("./data/Income.txt");
        expenseList.saveData("./data/Expense.txt");
    }
}
