package ui;

import java.util.*;

public class Income {
    private double amount;
    private String desc;

    public Income(double amount, String desc) {
        this.amount = amount;
        this.desc = desc;
    }

    public void set_desc(String desc) {
        this.desc = desc;
    }

    public String get_income_detail() {
        return ("\n|| Description: " + desc + "            Amount: " + amount + " ||\n");

    }
}
