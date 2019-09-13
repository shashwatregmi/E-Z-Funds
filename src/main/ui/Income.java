package ui;

import java.util.*;

public class Income {
    private double amount;
    private String desc;

    public Income (double amount){
        this.amount = amount;
        this.desc = "";
    }

    public void set_desc (String desc){
        this.desc = desc;
    }

    public void get_income_detail (){
        System.out.println("Description: " + desc + "            Amount: " + amount + "\n");

    }
}
