package ui;

public class Main {
    public static void main(String[] args) {
        Income income_1 = new Income(710.23);
        income_1.set_desc("Paycheck from 15 to 31 August 2019.");
        income(income_1);
        expense();
    }

    public static void expense(){
        System.out.println("Your expenses are listed below:");
        System.out.println("No expenses registered.");
    }

    public static void income(Income income_1){
        System.out.println("Your incomes are listed below:");
        income_1.get_income_detail();
    }
}
