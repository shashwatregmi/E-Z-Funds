package ui;

import model.*;
import model.trantype.DayToDayTran;
import model.trantype.LongTermTran;
import model.trantype.Transaction;
import model.exceptions.NegativeAmt;
import ui.exceptions.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;

// setups the program and takes user input/displays data according to user input
public class Program implements Version {

    private Scanner scan = new Scanner(System.in).useDelimiter("\\n");
    private int systemChoice = 0;
    private int mode;
    private Map<String, TranList> transactions = new HashMap<>();

    // EFFECTS: constructs program object and prints welcome message
    public Program() throws IOException {
        System.out.println("|| Personal Finance Manager ||");
        transactions.put("Expense", new DayTranList());
        transactions.put("Income", new DayTranList());
        transactions.put("Investment", new LongTermList());
        transactions.put("Debt", new LongTermList());
    }

    // EFFECTS: depending on user input calls function that enters new DaytoDay Transaction, edits it or displays report
    private void newEntry() throws FileNotFoundException, NegativeAmt {
        System.out.println("Press I to enter income or E to enter expense or Q to quit.");
        String choice = scan.next();
        if (choice.equals("I") || choice.equals("i")) {
            income();
        } else if (choice.equals("E") || choice.equals("e")) {
            expense();
        } else if (choice.equals("Q") || choice.equals("q")) {
            return;
        } else {
            System.out.println("That was not a valid input. Please try again.");
        }
    }

    // EFFECTS:depending on user input calls function that enters new Long term transaction, edits it or displays report
    private void ltNewEntry() throws FileNotFoundException, NegativeAmt {
        System.out.println("Press I to enter Investments or D to enter Debts or Q to quit.");
        String choice = scan.next();
        if (choice.equals("I") || choice.equals("i")) {
            invest();
        } else if (choice.equals("D") || choice.equals("d")) {
            debt();
        } else if (choice.equals("Q") || choice.equals("q")) {
            return;
        } else {
            System.out.println("That was not a valid input. Please try again.");
        }
    }

    // REQUIRES: there must be a trasaction of income/expense if it is to be deleted
    // MODIFIES: the incomelist or expenselist
    // EFFECTS: displays report and then allows user to delete transaction
    private void delEntry() throws OutOfBounds {
        System.out.println("Would you like to delete a income(I) or a expense(E)?");
        String choice = scan.next();
        try {
            if (choice.equals("I") || choice.equals("i")) {
                incomeDel();
            } else if (choice.equals("E") || choice.equals("e")) {
                expenseDel();
            } else {
                System.out.println("That was not a valid input.");
            }
        } catch (OutOfBounds outBounds) {
            System.out.println("That is not a valid row!");
        }
    }

    // REQUIRES: there must be a transaction of income if it is to be deleted
    // MODIFIES: the incomelist
    // EFFECTS: allows user to delete transaction
    public void incomeDel() throws OutOfBounds {
        incomeReport();
        System.out.println("What row would you like to delete?");
        int row = scan.nextInt();
        TranList incomeList = transactions.get("Income");
        if (row > incomeList.getSize()) {
            throw new OutOfBounds();
        }
        incomeList.delete(row - 1);
        System.out.println("Row " + row + " has been deleted.");
    }

    // REQUIRES: there must be a trasaction of expense if it is to be deleted
    // MODIFIES: the expenselist
    // EFFECTS: allows user to delete transaction
    public void expenseDel() throws OutOfBounds {
        expenseReport();
        System.out.println("What row would you like to delete?");
        int row = scan.nextInt();
        TranList expenseList = transactions.get("Expense");
        if (row > expenseList.getSize()) {
            throw new OutOfBounds();
        }
        expenseList.delete(row - 1);
        System.out.println("Row " + row + " has been deleted.");
    }

    // REQUIRES: there must be a trasaction of investment/debt if it is to be deleted
    // MODIFIES: the investmentList or debtList
    // EFFECTS: displays report and then allows user to delete transactions
    private void ltDelEntry() throws OutOfBounds {
        System.out.println("Would you like to delete a investment(I) or a debt(D)?");
        String choice = scan.next();
        try {
            if (choice.equals("I") || choice.equals("i")) {
                investDel();
            } else if (choice.equals("D") || choice.equals("d")) {
                debtDel();
            } else {
                System.out.println("That was not a valid input.");
            }
        } catch (OutOfBounds outBounds) {
            System.out.println("That is not a valid row!");
        }
    }

    // REQUIRES: there must be a trasaction of investment if it is to be deleted
    // MODIFIES: the investmentlist
    // EFFECTS: allows user to delete transaction
    public void investDel() throws OutOfBounds {
        investReport();
        System.out.println("What row would you like to delete?");
        int row = scan.nextInt();
        TranList investmentList = transactions.get("Investment");
        if (row > investmentList.getSize()) {
            throw new OutOfBounds();
        }
        investmentList.delete(row - 1);
        System.out.println("Row " + row + " has been deleted.");
    }

    // REQUIRES: there must be a trasaction of debt if it is to be deleted
    // MODIFIES: the debtlist
    // EFFECTS: allows user to delete transaction
    public void debtDel() throws OutOfBounds {
        debtReport();
        System.out.println("What row would you like to delete?");
        int row = scan.nextInt();
        TranList debtList = transactions.get("Debt");
        if (row > debtList.getSize()) {
            throw new OutOfBounds();
        }
        debtList.delete(row - 1);
        System.out.println("Row " + row + " has been deleted.");
    }

    // EFFECTS: displays the report of the DaytoDay transactions by calling respective functions
    private void dayReport() {
        incomeReport();
        expenseReport();
        System.out.println("\nPress any key to return to main menu.");
        String choice = scan.next();
    }

    // EFFECTS: displays the report of the LongTerm transactions by calling respective functions
    private void ltReport() {
        investReport();
        debtReport();
        System.out.println("\nPress any key to return to main menu.");
        String choice = scan.next();
    }

    //EFFECTS: prints all transactions in incomelist in a reportable fashion
    private void incomeReport() {
        System.out.println("-- INCOME --");
        TranList incomeRep = transactions.get("Income");
        for (int i = 0; i < incomeRep.getSize(); i++) {
            System.out.println(incomeRep.getTrans(i).getTransDetail());
        }
    }

    //EFFECTS: prints all transactions in expenselist in a reportable fashion
    private void expenseReport() {
        System.out.println("\n-- EXPENSE --");
        TranList expenseRep = transactions.get("Expense");
        for (int i = 0; i < expenseRep.getSize(); i++) {
            System.out.println(expenseRep.getTrans(i).getTransDetail());
        }
    }

    //EFFECTS: prints all transactions in investmentlist in a reportable fashion
    private void investReport() {
        System.out.println("\n-- INVESTMENT --");
        TranList investmentList = transactions.get("Investment");
        for (int i = 0; i < investmentList.getSize(); i++) {
            System.out.println(investmentList.getTrans(i).getTransDetail());
        }
    }

    //EFFECTS: prints all transactions in debtlist in a reportable fashion
    private void debtReport() {
        System.out.println("\n-- DEBT --");
        TranList debtList = transactions.get("Debt");
        for (int i = 0; i < debtList.getSize(); i++) {
            System.out.println(debtList.getTrans(i).getTransDetail());
        }
    }

    // MODIFIES: incomelist
    // EFFECTS: sets up income and takes user input from user. Then setups new transaction and puts in incomelist
    private void income() throws NegativeAmt {
        setup("Income");
        double amount = amtEntry();
        String desc = descEntry();
        Transaction transaction = new DayToDayTran(amount, desc);
        TranList incomeList = transactions.get("Income");
        incomeList.insert(transaction);
        System.out.println(transaction.getTransDetail());
    }

    // MODIFIES:investmentlist
    // EFFECTS:sets up investment and takes user input from user. Then setups new transaction and puts in investmentlist
    private void invest() throws NegativeAmt {
        setup("Investments");
        double amount = amtEntry();
        String desc = descEntry();
        int term = termEntry();
        double rate = rateEntry();
        LongTermTran transaction = new LongTermTran(amount, desc, term, rate);
        TranList investmentList = transactions.get("Investment");
        investmentList.insert(transaction);
        System.out.println(transaction.getTransDetail());
    }

    // MODIFIES: expenselist
    // EFFECTS: sets up expense and takes user input from user. Then setups new transaction and puts in expenselist
    private void expense() throws NegativeAmt {
        setup("Expense");
        double amount = amtEntry();
        String desc = descEntry();
        Transaction transaction = new DayToDayTran(amount, desc);
        TranList expenseList = transactions.get("Expense");
        expenseList.insert(transaction);
        System.out.println(transaction.getTransDetail());
    }

    // MODIFIES: debtList
    // EFFECTS: sets up debt and takes user input from user. Then setups new transaction and puts in debtlist
    private void debt() throws NegativeAmt {
        setup("Debt");
        double amount = amtEntry();
        String desc = descEntry();
        int term = termEntry();
        double rate = rateEntry();
        LongTermTran transaction = new LongTermTran(amount, desc, term, rate);
        TranList debtList = transactions.get("Investment");
        debtList.insert(transaction);
        System.out.println(transaction.getTransDetail());
    }


    // EFFECTS: setup title
    private void setup(String type) {
        System.out.print("\n--" + type + " Entry Mode--\n");
    }

    // EFFECTS: takes in user input and returns value
    private double amtEntry() throws NegativeAmt {
        double amount = 0;
        System.out.println("Please enter the amount:");
        amount = scan.nextDouble();
        if (amount < 0) {
            throw new NegativeAmt();
        }
        return amount;
    }

    // EFFECTS: takes in user input and returns value
    private String descEntry() {
        System.out.println("Please enter a description for this transaction:");
        return scan.next();
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

    // EFFECTS: some introduction outputs for program
    private void intro() {
        System.out.println("\n-----------------------------------------------------------");
        System.out.println("Would you like to:");
        System.out.println("Enter a new transaction [1]");
        System.out.println("Delete a transaction [2]");
        System.out.println("View the report [3]");
        System.out.println("Return [4]");
        mode = scan.nextInt();
    }


    // REQUIRES: that the file being read has data values seperated with proper characters.
    // MODIFIES: transactions
    // EFFECTS: loads all lines from input file "Income.txt", "Expense.txt", "Investment.txt", "Debt.txt" into our
    // transactions map for user usage.
    public void loadData() throws NegativeAmt, IOException {
        TranList incomeList = transactions.get("Income");
        TranList expenseList = transactions.get("Expense");
        TranList debtList = transactions.get("Debt");
        TranList investList = transactions.get("Investment");

        incomeList.loadData("./data/Income.txt");
        expenseList.loadData("./data/Expense.txt");
        investList.loadData("./data/Invest.txt");
        debtList.loadData("./data/Debt.txt");
    }

    // EFFECTS: loads data from incomeList, expenseList, investmentList and debtList
    // array into our incomeWriter, expeseWriter, investmentWriter and debtWriter writers which  write to proper file
    public void saveData() throws FileNotFoundException, UnsupportedEncodingException {
        TranList incomeList = transactions.get("Income");
        TranList expenseList = transactions.get("Expense");
        TranList debtList = transactions.get("Debt");
        TranList investList = transactions.get("Investment");

        incomeList.saveData("./data/Income.txt");
        expenseList.saveData("./data/Expense.txt");
        investList.saveData("./data/Invest.txt");
        debtList.saveData("./data/Debt.txt");
    }

    // MODIFIES: systemChoice
    // EFFECTS: starts the program. SystemChoice is chosen depending on user input and we loop until
    // user exits/app stopped
    public void run() throws FileNotFoundException, UnsupportedEncodingException, NegativeAmt {
        while (true) {
            try {
                modeSelection();
            } catch (Exit exit) {
                saveData();
                System.out.println("Your data has been saved. Thank you!");
                break;
            }
            runSelection();
        }
    }

    // EFFECTS: appropriate function is called depending on what choice user inputs.
    public void runSelection() {
        try {
            if (systemChoice == 1) {
                dayToDay();
            } else if (systemChoice == 2) {
                longTerm();
            }
        } catch (NegativeAmt | FileNotFoundException | UnsupportedEncodingException | OutOfBounds negativeAmt) {
            System.out.println("\nError! Negative Amount cannot be entered.");
        } finally {
            System.out.println("\n\nPlease choose what type of transaction type to manage next:");
        }
    }

    // EFFECTS: enters daytoday mode. Users allowed to work with DaytoDay Transactions
    public void dayToDay() throws FileNotFoundException, UnsupportedEncodingException, NegativeAmt, OutOfBounds {
        if (mode == 1) {
            newEntry();
        } else if (mode == 2) {
            delEntry();
        } else if (mode == 3) {
            dayReport();
        } else if (mode == 4) {
            return;
        } else {
            System.out.println("\nThat was not a valid input. Please try again.");
        }
    }

    // EFFECTS: enters longTerm mode. Users allowed to work with LongTerm Transactions
    public void longTerm() throws FileNotFoundException, UnsupportedEncodingException, NegativeAmt, OutOfBounds {
        if (mode == 1) {
            ltNewEntry();
        } else if (mode == 2) {
            ltDelEntry();
        } else if (mode == 3) {
            ltReport();
        } else if (mode == 4) {
            return;
        } else {
            System.out.println("\nThat was not a valid input. Please try again.");
        }
    }

    // EFFECTS: Allows users to choose between 2 program modes and calls functions to invoke them
    public void modeSelection() throws Exit {
        System.out.println("\n-----------------------------------------------------------");
        System.out.println("Would you like to:");
        System.out.println("Manage Day to Day Finances [1]");
        System.out.println("Manage Long Term Finances  [2]");
        System.out.println("Quit                       [3]");
        systemChoice = scan.nextInt();
        if (systemChoice == 1) {
            intro();
        } else if (systemChoice == 2) {
            intro();
        } else if (systemChoice == 3) {
            throw new Exit();
        }
    }
}
