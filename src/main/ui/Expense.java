package ui;

import java.util.*;

public class Expense {
    private double amount;
    private String desc;

    public Expense(double amount, String desc) {
        this.amount = amount;
        this.desc = desc;
    }

    public void set_desc(String desc) {
        this.desc = desc;
    }

    public String get_expense_detail() {
        return ("\n|| Description: " + desc + "            Amount: " + amount + " ||\n");

    }
}
