package ui;

import model.TranList;
import model.exceptions.NegativeAmt;
import ui.exceptions.OutOfBounds;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Citation: The basic GUI example from the edX D11 website
// <https://edge.edx.org/courses/course-v1:UBC+CPSC210+all/courseware/f636f4e1dd5348ed8f6dc7c3defed983/cba8ffaf475e4b9f
// bba32931df89c90a/1?activate_block_id=block-v1%3AUBC%2BCPSC210%2Ball%2Btype%40vertical%2Bblock%40d13dcf17afb440b1826c4
// 45e272d92c6>

public class Main extends JFrame implements ActionListener {

    private JTextField field;
    private JButton newEnt;
    private JButton delete;
    private JButton report;
    private JComboBox comboBox = new JComboBox();
    private DefaultListModel<String> incomeListModel = new DefaultListModel<>();
    private DefaultListModel<String> expenseListModel = new DefaultListModel<>();
    private DefaultListModel<String> investListModel = new DefaultListModel<>();
    private DefaultListModel<String> debtListModel = new DefaultListModel<>();
    private JList incomeList = new JList<>(incomeListModel);
    private JList expenseList = new JList<>(expenseListModel);
    private JList investList = new JList<>(investListModel);
    private JList debtList = new JList<>(debtListModel);
    private Program program;
    private boolean incomeFlag = false;
    private boolean expenseFlag = false;
    private boolean investFlag = false;
    private boolean debtFlag = false;
    private int incomeIndex = -1;
    private int expenseIndex = -1;
    private int investIndex = -1;
    private int debtIndex = -1;


    private Main() throws IOException, NegativeAmt {
        JFrame window = new JFrame("Personal Finance Manager");
        window.setDefaultCloseOperation(EXIT_ON_CLOSE);
        window.setSize(600, 400);
        JPanel main = new JPanel();
        JPanel top = new JPanel();
        JPanel middleTop = new JPanel();
        JPanel middleBottom = new JPanel();
        JPanel bottom = new JPanel();

        ////////////////////// TOP PANEL
        JLabel label = new JLabel("Transaction Type: ");
        comboBox.addItem("Day to Day Transactions");
        comboBox.addItem("Long Term Transactions");
        comboBox.setSelectedIndex(-1);
        comboBox.addActionListener(this);
        top.add(label);
        top.add(comboBox);


        //////MIDDLE PANEL
        middleTop.add(incomeList);
        middleTop.add(investList);
        middleBottom.add(debtList);
        middleBottom.add(expenseList);

        incomeList.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                incomeIndex = incomeList.getSelectedIndex();
            }
        });

        expenseList.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                expenseIndex = expenseList.getSelectedIndex();
            }
        });

        investList.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                investIndex = investList.getSelectedIndex();
            }
        });

        debtList.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                debtIndex = debtList.getSelectedIndex();
            }
        });

        //////////////// BOTTOM PANEL
        newEnt = new JButton("New Entry");
        newEnt.setActionCommand("newClick");
        newEnt.addActionListener(this);
        delete = new JButton("Delete Entry");
        delete.setActionCommand("delClick");
        delete.addActionListener(this);
        report = new JButton("View Report");
        report.setActionCommand("repClick");
        report.addActionListener(this);
        bottom.add(newEnt);
        bottom.add(delete);
        bottom.add(report);


        //////// WINDOW VISIBILITY STUFF
        window.setContentPane(main);
        main.add(top);
        main.add(middleTop);
        main.add(middleBottom);
        main.add(bottom);
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        window.setResizable(false);

        ///////// Load the program here
        program = new Program();
        try {
            program.loadData();
        } catch (NegativeAmt negativeamt) {
            System.out.println("Error. Negative Amount in file.");
        }

        program.run();
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("delClick")) {
            try {
                if (incomeIndex >= 0) {
                    deleteIncome();
                } else if (expenseIndex >= 0) {
                    deleteExpense();
                } else if (investIndex >= 0) {
                    deleteInvest();
                } else if (debtIndex >= 0) {
                    deleteDebt();
                }
            } finally {
                return;
            }
        }

        comboBoxAction(e);
    }

    private void deleteIncome() throws Exception {
        program.dailyMode.delete("Income", incomeIndex);
        incomeFlag = false;
        incomeListModel.clear();
        loadIncome();
        incomeIndex = -1;
    }

    private void deleteExpense() throws Exception {
        program.dailyMode.delete("Expense", expenseIndex);
        expenseFlag = false;
        expenseListModel.clear();
        loadExpense();
        expenseIndex = -1;
    }

    private void deleteInvest() throws Exception {
        program.longTermMode.delete("Investment", investIndex);
        investFlag = false;
        investListModel.clear();
        loadInvest();
        investIndex = -1;
    }

    private void deleteDebt() throws Exception {
        program.longTermMode.delete("Debt", debtIndex);
        debtFlag = false;
        debtListModel.clear();
        loadDebt();
        debtIndex = -1;
    }

    private void comboBoxAction(ActionEvent e) {
        // CITED: https://www.youtube.com/watch?v=iOV_oaJhABQ
        if (e.getSource() == comboBox) {
            JComboBox cb = (JComboBox)e.getSource();
            String tranType = (String)cb.getSelectedItem();
            assert tranType != null;
            switch (tranType) {
                case "Day to Day Transactions":
                    loadIncome();
                    loadExpense();
                    break;
                case "Long Term Transactions":
                    program.setSysChoice(2);
                    loadInvest();
                    loadDebt();
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + tranType);
            }
        }
    }

    private void loadIncome() {
        program.setSysChoice(1);
        TranList rep = program.dailyMode.transactions.get("Income");
        if (!incomeFlag) {
            for (int i = 0; i < rep.getSize(); i++) {
                incomeListModel.addElement(rep.getTrans(i).getTransDetail());
            }
            investList.setVisible(false);
            incomeList.setVisible(false);
            incomeList.setVisible(true);
            incomeFlag = true;
            investFlag = false;
            investListModel.clear();
        }
    }

    private void loadExpense() {
        program.setSysChoice(1);
        TranList rep = program.dailyMode.transactions.get("Expense");
        if (!expenseFlag) {
            for (int i = 0; i < rep.getSize(); i++) {
                expenseListModel.addElement(rep.getTrans(i).getTransDetail());
            }
            debtList.setVisible(false);
            expenseList.setVisible(false);
            expenseList.setVisible(true);
            expenseFlag = true;
            debtFlag = false;
            debtListModel.clear();
        }
    }

    private void loadInvest() {
        program.setSysChoice(1);
        TranList rep = program.longTermMode.transactions.get("Investment");
        if (!investFlag) {
            for (int i = 0; i < rep.getSize(); i++) {
                investListModel.addElement(rep.getTrans(i).getTransDetail());
            }
            incomeList.setVisible(false);
            investList.setVisible(false);
            investList.setVisible(true);
            investFlag = true;
            incomeFlag = false;
            incomeListModel.clear();
        }
    }

    private void loadDebt() {
        program.setSysChoice(1);
        TranList rep = program.longTermMode.transactions.get("Debt");
        if (!debtFlag) {
            for (int i = 0; i < rep.getSize(); i++) {
                debtListModel.addElement(rep.getTrans(i).getTransDetail());
            }
            expenseList.setVisible(false);
            debtList.setVisible(false);
            debtList.setVisible(true);
            debtFlag = true;
            expenseFlag = false;
            expenseListModel.clear();
        }
    }


    public static void main(String[] args) throws IOException, NegativeAmt {
        new Main();
    }
}
