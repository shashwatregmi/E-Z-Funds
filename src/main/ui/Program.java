package ui;

import model.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
// setups the program and takes user input/displays data according to user input

public class Program implements Loadable, Savable {
    private Scanner scan = new Scanner(System.in).useDelimiter("\\n");
    private Category incomeList = new Income();
    private Category expenseList = new Expense();
    private int mode;
    private List<String> incomeRead = Files.readAllLines(Paths.get("Income.txt"));
    private List<String> expenseRead = Files.readAllLines(Paths.get("Expense.txt"));
    private PrintWriter incomeWriter = new PrintWriter("Income.txt","UTF-8");
    private PrintWriter expenseWriter = new PrintWriter("Expense.txt","UTF-8");

    Program() throws IOException {
        System.out.println("|| Personal Finance Calculator ||");
    }

    // EFFECTS: depending on user input calls function that enters new transaction, edits it or displays report
    private void newEntry() throws FileNotFoundException {
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

    // REQUIRES: there must be a trasaction of income/expense if it is to be deleted
    // MODIFIES: the incomelist or expenselist
    // EFFECTS: displays report and then allows user to delete transaction
    private void delEntry() {
        System.out.println("Would you like to delete a income(I) or a expense(E)?");
        String choice = scan.next();
        if (choice.equals("I") || choice.equals("i")) {
            incomeReport();
            System.out.println("What row would you like to delete?");
            int row = scan.nextInt();
            incomeList.delete(row - 1);
            System.out.println("Row " + row + " has been deleted.");
        } else if (choice.equals("E") || choice.equals("e")) {
            expenseReport();
            System.out.println("What row would you like to delete?");
            int row = scan.nextInt();
            expenseList.delete(row - 1);
            System.out.println("Row " + row + " has been deleted.");
        } else {
            System.out.println("That was not a valid input.");
        }
    }

    // EFFECTS: displays the report of the transactions by calling respective functions
    private void report() {
        incomeReport();
        expenseReport();
        System.out.println("\nPress any key to return to main menu.");
        String choice = scan.next();
    }

    private void incomeReport() {
        System.out.println("-- INCOME --");
        for (int i = 0; i < incomeList.getSize(); i++) {
            System.out.println(incomeList.getTrans(i).getTransDetail());
        }
    }

    private void expenseReport() {
        System.out.println("\n-- EXPENSE --");
        for (int i = 0; i < expenseList.getSize(); i++) {
            System.out.println(expenseList.getTrans(i).getTransDetail());
        }
    }

    // MODIFIES: incomelist
    // EFFECTS: sets up expense and takes user input from user. Then setups new transaction and puts in incomelist
    private void income() {
        setup("Income");
        double amount = amtEntry();
        String desc = descEntry();
        Transaction transaction = new Transaction(amount, desc);
        incomeList.insert(transaction);
        System.out.println(transaction.getTransDetail());
    }

    // MODIFIES: expenselist
    // EFFECTS: sets up expense and takes user input from user. Then setups new transaction and puts in expenselist
    private void expense() {
        setup("Expense");
        double amount = amtEntry();
        String desc = descEntry();
        Transaction transaction = new Transaction(amount, desc);
        expenseList.insert(transaction);
        System.out.println(transaction.getTransDetail());
    }

    // EFFECTS: setup title
    private void setup(String type) {
        System.out.print("\n--" + type + " Entry Mode--\n");
    }

    // EFFECTS: takes in user input and returns value
    private double amtEntry() {
        System.out.println("Please enter the amount:");
        return scan.nextDouble();
    }

    // EFFECTS: takes in user input and returns value
    private String descEntry() {
        System.out.println("Please enter a description for this transaction:");
        return scan.next();
    }

    // EFFECTS: some introduction outputs for program
    private void intro() {
        System.out.println("\n-----------------------------------------------------------");
        System.out.println("Would you like to:");
        System.out.println("Enter a new transaction [1]");
        System.out.println("Delete a transaction [2]");
        System.out.println("View the report [3]");
        System.out.println("Quit [4]");
        mode = scan.nextInt();
    }

    // REQUIRES: that the file being read has description and amount seperated with ~~.
    // MODIFIES: incomeList, expenseList, incomeRead and expenseRead
    // EFFECTS: loads all lines from input file "Income.txt" and "Expense.txt" into our incomeList
    //          and expenseList array for user usage.
    public void loadData() {
        loader(incomeRead, 0);
        loader(expenseRead, 1);
    }

    // REQUIRES: that the file being read has description and amount seperated with ~~.
    // MODIFIES: incomeList or expenseList and incomeRead or expenseRead
    // EFFECTS: loads all lines from array passed in into our incomeList/expenseList array for user usage.
    private void loader(List<String> file, int i) {
        for (String line : file) {
            ArrayList<String> partsOfLine = splitOnChar(line);
            String desc = partsOfLine.get(0);
            double amount = Double.parseDouble(partsOfLine.get(1));
            Transaction loadTransaction = new Transaction(amount, desc);
            if (i == 0) {
                incomeList.insert(loadTransaction);
            } else {
                expenseList.insert(loadTransaction);
            }
        }
    }

    // MODIFIES: incomeWriter and expenseWriter
    // EFFECTS: loads data from incomeList and expenseList array into our incomeWriter and expeseWriter writers which
    //          write to file
    public void saveData() {
        for (int i = 0; i < incomeList.getSize(); i++) {
            incomeWriter.println(incomeList.getTrans(i).getDesc() + "~~" + incomeList.getTrans(i).getAmount());
        }
        for (int i = 0; i < expenseList.getSize(); i++) {
            expenseWriter.println(expenseList.getTrans(i).getDesc() + "~~" + expenseList.getTrans(i).getAmount());
        }
    }

    // EFFECTS: returns the array list which has been split on ~~.
    private static ArrayList<String> splitOnChar(String line) {
        String[] splits = line.split("~~");
        return new ArrayList<>(Arrays.asList(splits));
    }

    // EFFECTS: starts the program. Mode is chosen depending on user input and we loop until user exits/app stopped
    void run() throws FileNotFoundException {
        while (true) {
            intro();
            if (mode == 1) {
                newEntry();
            } else if (mode == 2) {
                delEntry();
            } else if (mode == 3) {
                report();
            } else if (mode == 4) {
                saveData();
                incomeWriter.close();
                expenseWriter.close();
                return;
            } else {
                System.out.println("That was not a valid input. Please try again.");
            }
        }
    }
}
