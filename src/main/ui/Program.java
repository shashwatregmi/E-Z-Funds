package ui;

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
    DayToDayMode dailyMode;
    LongTermMode longTermMode;

    // EFFECTS: constructs program object and prints welcome message
    public Program() throws IOException {
        System.out.println("|| Personal Finance Manager ||");
        dailyMode = new DayToDayMode();
        longTermMode = new LongTermMode();
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
        dailyMode.load();
        longTermMode.load();
    }

    // EFFECTS: loads data from incomeList, expenseList, investmentList and debtList
    // array into our incomeWriter, expeseWriter, investmentWriter and debtWriter writers which  write to proper file
    public void saveData() throws FileNotFoundException, UnsupportedEncodingException {
        dailyMode.save();
        longTermMode.save();
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
    private void runSelection() {
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
    private void dayToDay() throws FileNotFoundException, UnsupportedEncodingException, NegativeAmt, OutOfBounds {
        if (mode == 1) {
            dailyMode.newEntry();
        } else if (mode == 2) {
            dailyMode.delEntry();
        } else if (mode == 3) {
            dailyMode.dayReport();
        } else if (mode == 4) {
            return;
        } else {
            System.out.println("\nThat was not a valid input. Please try again.");
        }
    }

    // EFFECTS: enters longTerm mode. Users allowed to work with LongTerm Transactions
    private void longTerm() throws FileNotFoundException, UnsupportedEncodingException, NegativeAmt, OutOfBounds {
        if (mode == 1) {
            longTermMode.ltNewEntry();
        } else if (mode == 2) {
            longTermMode.ltDelEntry();
        } else if (mode == 3) {
            longTermMode.ltReport();
        } else if (mode == 4) {
            return;
        } else {
            System.out.println("\nThat was not a valid input. Please try again.");
        }
    }

    // EFFECTS: Allows users to choose between 2 program modes and calls functions to invoke them
    private void modeSelection() throws Exit {
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
