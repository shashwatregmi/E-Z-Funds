package ui;

import java.util.*;

public class Main {
    Scanner scan = new Scanner(System.in);
    private ArrayList<Income> incomelist;
    private ArrayList<Expense> expenselist;

    public Main() {
        incomelist = new ArrayList<>();
        expenselist = new ArrayList<>();

        while (true) {
            System.out.println("\n-----------------------------------------------------------");
            System.out.println("Would you like to:");
            System.out.println("Enter a new transaction [1]");
            System.out.println("Delete a transaction [2]");
            System.out.println("View the report [3]");
            System.out.println("Quit [4]");
            Integer mode = scan.nextInt();

            if (mode == 1) {
                newentry();
            } else if (mode == 2) {
                delentry();
            } else if (mode == 3) {
                report();
            } else if (mode == 4) {
                return;
            } else {
                System.out.println("That was not a valid input. Please try again.");
            }
        }
    }

    public void newentry() {
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

    public void delentry() {
        System.out.println("Would you like to delete a income(I) or a expense(E)?");
        String choice = scan.next();
        if (choice.equals("I") || choice.equals("i")) {
            incomereport();
            System.out.println("What row would you like to delete?");
            int row = scan.nextInt();
            incomelist.remove(row - 1);
            System.out.println("Row " + row + " has been deleted.");
        } else if (choice.equals("E") || choice.equals("e")) {
            expensereport();
            System.out.println("What row would you like to delete?");
            int row = scan.nextInt();
            expenselist.remove(row - 1);
            System.out.println("Row " + row + " has been deleted.");
        } else {
            System.out.println("That was not a valid input.");
        }
    }

    public void report() {
        incomereport();
        expensereport();
        System.out.println("\nPress any key to return to main menu.");
        String choice = scan.next();
    }

    public void incomereport() {
        System.out.println("-- INCOME --");
        for (int i = 0; i < incomelist.size(); i++) {
            System.out.println(incomelist.get(i).get_income_detail());
        }
    }

    public void expensereport() {
        System.out.println("-- EXPENSE --\n");
        for (int i = 0; i < expenselist.size(); i++) {
            System.out.println(expenselist.get(i).get_expense_detail());
        }
    }

    public void income() {
        setup("Income");
        double amount = amtentry();
        String desc = descentry();
        Income income1 = new Income(amount, desc);
        incomelist.add(income1);
        System.out.println(income1.get_income_detail());
        //System.out.println("The transaction has been recorded. Would you like to see the report?\n");
        //report();
    }

    public void expense() {
        setup("Expense");
        double amount = amtentry();
        String desc = descentry();
        Expense expense1 = new Expense(amount, desc);
        expenselist.add(expense1);
        System.out.println(expense1.get_expense_detail());
        //System.out.println("The transaction has been recorded. Would you like to see the report?\n");
        //report();
    }

    public void setup(String type) {
        System.out.print("\n--" + type + " Entry Mode--\n");
    }

    public double amtentry() {
        System.out.println("Please enter the amount:");
        return scan.nextDouble();
    }

    public String descentry() {
        System.out.println("Please enter a description for this transaction:");
        return scan.next();
    }

    public static void main(String[] args) {
        new Main();
    }

}
