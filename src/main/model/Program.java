package model;

import java.util.Scanner;

public class Program {
    private Scanner scan = new Scanner(System.in);
    private Income incomeList = new Income();
    private Expense expenseList = new Expense();

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

    private void delEntry() {
        System.out.println("Would you like to delete a income(I) or a expense(E)?");
        String choice = scan.next();
        if (choice.equals("I") || choice.equals("i")) {
            incomeList.incomeReport();
            System.out.println("What row would you like to delete?");
            int row = scan.nextInt();
            incomeList.remove(row - 1);
            System.out.println("Row " + row + " has been deleted.");
        } else if (choice.equals("E") || choice.equals("e")) {
            expenseList.expenseReport();
            System.out.println("What row would you like to delete?");
            int row = scan.nextInt();
            expenseList.remove(row - 1);
            System.out.println("Row " + row + " has been deleted.");
        } else {
            System.out.println("That was not a valid input.");
        }
    }

    private void report() {
        incomeList.incomeReport();
        expenseList.expenseReport();
        System.out.println("\nPress any key to return to main menu.");
        String choice = scan.next();
    }

    private void income() {
        setup("Income");
        double amount = amtEntry();
        String desc = descEntry();
        Transaction transaction = new Transaction(amount, desc);
        incomeList.insert(transaction);
        System.out.println(transaction.getTransDetail());
    }

    private void expense() {
        setup("Expense");
        double amount = amtEntry();
        String desc = descEntry();
        Transaction transaction = new Transaction(amount, desc);
        expenseList.insert(transaction);
        System.out.println(transaction.getTransDetail());
    }

    private void setup(String type) {
        System.out.print("\n--" + type + " Entry Mode--\n");
    }

    private double amtEntry() {
        System.out.println("Please enter the amount:");
        return scan.nextDouble();
    }

    private String descEntry() {
        System.out.println("Please enter a description for this transaction:");
        return scan.next();
    }

    private void intro() {
        System.out.println("\n-----------------------------------------------------------");
        System.out.println("Would you like to:");
        System.out.println("Enter a new transaction [1]");
        System.out.println("Delete a transaction [2]");
        System.out.println("View the report [3]");
        System.out.println("Quit [4]");
    }

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
