package ui;

import model.Expense;
import model.Income;
import model.Transaction;

import java.util.Scanner;
// setups the program and takes user input/displays data according to user input

public class Program {
    private Scanner scan = new Scanner(System.in);
    private Income incomeList = new Income();
    private Expense expenseList = new Expense();

    // EFFECTS: depending on user input calls function that enters new transaction, edits it or displays report
    private void newEntry() {
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
            incomeList.incomeReport();
            System.out.println("What row would you like to delete?");
            int row = scan.nextInt();
            incomeList.delete(row - 1);
            System.out.println("Row " + row + " has been deleted.");
        } else if (choice.equals("E") || choice.equals("e")) {
            expenseList.expenseReport();
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
        incomeList.incomeReport();
        expenseList.expenseReport();
        System.out.println("\nPress any key to return to main menu.");
        String choice = scan.next();
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
    }

    // EFFECTS: starts the program. Mode is chosen depending on user input and we loop until user exits/app stopped
    public void run() {
        while (true) {
            intro();
            int mode = scan.nextInt();

            if (mode == 1) {
                newEntry();
            } else if (mode == 2) {
                delEntry();
            } else if (mode == 3) {
                report();
            } else if (mode == 4) {
                return;
            } else {
                System.out.println("That was not a valid input. Please try again.");
            }
        }
    }
}
