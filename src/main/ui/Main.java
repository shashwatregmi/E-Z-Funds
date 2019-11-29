package ui;

import model.TranList;
import model.exceptions.NegativeAmt;
import network.PullWelcomeMsg;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;
import java.net.URL;

// Citation: The basic GUI example from the edX D11 website
// <https://edge.edx.org/courses/course-v1:UBC+CPSC210+all/courseware/f636f4e1dd5348ed8f6dc7c3defed983/cba8ffaf475e4b9f
// bba32931df89c90a/1?activate_block_id=block-v1%3AUBC%2BCPSC210%2Ball%2Btype%40vertical%2Bblock%40d13dcf17afb440b1826c4
// 45e272d92c6>

public class Main extends JFrame implements ActionListener {

    private JButton newEnt;
    private JButton delete;
    private JButton save;
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
    private JPanel middleTop = new JPanel();
    private JPanel middleBottom = new JPanel();
    private JTextField descField;
    private JTextField amtField;
    private JTextField rateField;
    private JTextField termField;
    private int sysChoice;
    private JRadioButton income;
    private JRadioButton expense;
    private JRadioButton invest;
    private JRadioButton debt;
    private int radioIndex;
    private boolean expSelect = true;
    private boolean incomeSelect = true;
    private boolean investSelect = true;
    private boolean debtSelect = true;

    private Main() throws IOException, NegativeAmt {
        JFrame window = new JFrame("Personal Finance Manager");
        window.setDefaultCloseOperation(EXIT_ON_CLOSE);
        window.setSize(1200, 900);
        JPanel main = new JPanel();
        JPanel top = new JPanel();
        JPanel bottom = new JPanel();
        JPanel superBottom = new JPanel();
        JPanel entryBottom = new JPanel();


        GridBagLayout layout = new GridBagLayout();
        main.setLayout(layout);
        GridBagConstraints gbc = new GridBagConstraints();
        entryBottom.setLayout(new GridLayout(3, 4));

        main.setBackground(Color.white);
        top.setBackground(Color.white);
        middleTop.setBackground(Color.white);
        middleBottom.setBackground(Color.white);
        bottom.setBackground(Color.white);
        superBottom.setBackground(Color.white);
        entryBottom.setBackground(Color.white);
        ////////////////////// TOP PANEL
        JLabel label = new JLabel("Transaction Type: ");
        comboBox.addItem("Day to Day Transactions");
        comboBox.addItem("Long Term Transactions");
        comboBox.setSelectedIndex(-1);
        comboBox.addActionListener(this);
        top.add(label);
        top.add(comboBox);
        label.setFont(new Font("News Gothic MT", Font.BOLD, 12));
        comboBox.setFont(new Font("News Gothic MT", Font.ITALIC, 12));

        //////MIDDLE PANEL
        middleTop.add(incomeList);
        middleTop.add(investList);
        middleBottom.add(expenseList);
        middleBottom.add(debtList);

        incomeList.setFont(new Font("News Gothic MT", Font.PLAIN, 12));
        expenseList.setFont(new Font("News Gothic MT", Font.PLAIN, 12));
        investList.setFont(new Font("News Gothic MT", Font.PLAIN, 12));
        debtList.setFont(new Font("News Gothic MT", Font.PLAIN, 12));



        incomeList.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                incomeIndex = incomeList.getSelectedIndex();
                delete.setEnabled(true);
                //newEnt.setEnabled(false);
                if (incomeFlag && incomeList.getSelectedIndex() >= 0) {
                    descField.setText(program.dailyMode.incomeList.getTrans(incomeIndex).getDesc());
                    amtField.setText(program.dailyMode.incomeList.getTrans(incomeIndex).getAmount().toString());
                }
                if (expSelect) {
                    expenseList.clearSelection();
                    expSelect = false;
                    incomeSelect = true;
                }
            }
        });

        expenseList.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                expenseIndex = expenseList.getSelectedIndex();
                delete.setEnabled(true);
                //newEnt.setEnabled(false);
                if (expenseFlag && expenseList.getSelectedIndex() >= 0) {
                    descField.setText(program.dailyMode.expenseList.getTrans(expenseIndex).getDesc());
                    amtField.setText(program.dailyMode.expenseList.getTrans(expenseIndex).getAmount().toString());
                }
                if (incomeSelect) {
                    incomeList.clearSelection();
                    incomeSelect = false;
                    expSelect = true;
                }
            }
        });

        investList.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                investIndex = investList.getSelectedIndex();
                delete.setEnabled(true);
                //newEnt.setEnabled(false);
                if (investFlag && investList.getSelectedIndex() >= 0) {
                    descField.setText(program.longTermMode.investList.getTrans(investIndex).getDesc());
                    amtField.setText(program.longTermMode.investList.getTrans(investIndex).getAmount().toString());
                    rateField.setText(
                            program.longTermMode.investList.getTrans(investIndex).getInterestRate().toString());
                    termField.setText((program.longTermMode.investList.getTrans(investIndex).getTerm()).toString());
                }
                if (debtSelect) {
                    debtList.clearSelection();
                    debtSelect = false;
                    investSelect = true;
                }
            }
        });

        debtList.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                debtIndex = debtList.getSelectedIndex();
                delete.setEnabled(true);
                //newEnt.setEnabled(false);
                if (debtFlag && debtList.getSelectedIndex() >= 0) {
                    descField.setText(program.longTermMode.debtList.getTrans(debtIndex).getDesc());
                    amtField.setText(program.longTermMode.debtList.getTrans(debtIndex).getAmount().toString());
                    rateField.setText(program.longTermMode.debtList.getTrans(debtIndex).getInterestRate().toString());
                    termField.setText((program.longTermMode.debtList.getTrans(debtIndex).getTerm()).toString());
                }
                if (investSelect) {
                    investList.clearSelection();
                    investSelect = false;
                    debtSelect = true;
                }
            }
        });

        ///Text Bottom
        income = new JRadioButton();
        expense = new JRadioButton();
        invest = new JRadioButton();
        debt = new JRadioButton();
        income.setFont(new Font("News Gothic MT", Font.ITALIC, 12));
        expense.setFont(new Font("News Gothic MT", Font.ITALIC, 12));
        debt.setFont(new Font("News Gothic MT", Font.ITALIC, 12));
        invest.setFont(new Font("News Gothic MT", Font.ITALIC, 12));
        income.setEnabled(false);
        expense.setEnabled(false);
        invest.setEnabled(false);
        debt.setEnabled(false);
        income.setText("Income");
        expense.setText("Expense");
        invest.setText("Investment");
        debt.setText("Debt");

        JLabel desc = new JLabel("Description:");
        JLabel amt = new JLabel("Amount:");
        JLabel rate = new JLabel("Interest Rate:");
        JLabel term = new JLabel("Term:");
        descField = new JTextField();
        amtField = new JTextField();
        rateField = new JTextField();
        termField = new JTextField();
        descField.setFont(new Font("News Gothic MT", Font.PLAIN, 12));
        amtField.setFont(new Font("News Gothic MT", Font.PLAIN, 12));
        rateField.setFont(new Font("News Gothic MT", Font.PLAIN, 12));
        termField.setFont(new Font("News Gothic MT", Font.PLAIN, 12));
        desc.setFont(new Font("News Gothic MT", Font.BOLD, 12));
        amt.setFont(new Font("News Gothic MT", Font.BOLD, 12));
        rate.setFont(new Font("News Gothic MT", Font.BOLD, 12));
        term.setFont(new Font("News Gothic MT", Font.BOLD, 12));


        entryBottom.add(income);
        entryBottom.add(expense);
        entryBottom.add(invest);
        entryBottom.add(debt);
        income.addActionListener(this);
        expense.addActionListener(this);
        invest.addActionListener(this);
        debt.addActionListener(this);
        entryBottom.add(desc);
        entryBottom.add(descField);
        entryBottom.add(amt);
        entryBottom.add(amtField);
        entryBottom.add(rate);
        entryBottom.add(rateField);
        entryBottom.add(term);
        entryBottom.add(termField);
        descField.setEnabled(false);
        amtField.setEnabled(false);
        termField.setEnabled(false);
        rateField.setEnabled(false);
        entryBottom.setBorder(BorderFactory.createTitledBorder("New Entry: "));
        entryBottom.setFont(new Font("News Gothic MT", Font.BOLD, 12));
        gbc.weighty = 1;


        //////////////// BOTTOM PANEL
        // NEW ENTRY Image: Icon made by Smashicons from www.flaticon.com
        // https://www.flaticon.com/free-icon/envelope_134975?term=open&page=1&position=22

        // Delete Image: Icon made by Freepik from www.flaticon.com
        // https://www.flaticon.com/free-icon/file_2246626?term=delete&page=1&position=32

        // Save Image: Icon made by Smashicons from www.flaticon.com
        //https://www.flaticon.com/free-icon/save_148730?term=save&page=1&position=1
        newEnt = new JButton("New Entry", new ImageIcon("./data/new.png"));
        newEnt.setActionCommand("newClick");
        newEnt.addActionListener(this);
        delete = new JButton("Delete Entry", new ImageIcon("./data/delete.png"));
        delete.setActionCommand("delClick");
        delete.addActionListener(this);
        save = new JButton("Save Entry", new ImageIcon("./data/save.png"));
        save.setActionCommand("saveClick");
        save.addActionListener(this);
        bottom.add(delete);
        bottom.add(newEnt);
        bottom.add(save);
        delete.setEnabled(false);
        save.setEnabled(false);
        newEnt.setEnabled(false);

        // ICON CITE (weather.png): Icon made by Freepik from www.flaticon.com
        // https://www.flaticon.com/free-icon/hot_1684375?term=weather&page=1&position=9
        // ICON CITE (cold.png): Icon made by Freepik from www.flaticon.com
        // https://www.flaticon.com/free-icon/cold_1684425
        JLabel weather = new JLabel();
        PullWelcomeMsg pull = new PullWelcomeMsg();
        weather.setText("NOTE: " + pull.finalWeather);
        if (pull.getTemp() > 8) {
            weather.setIcon(new ImageIcon("./data/weather.png"));
        } else {
            weather.setIcon(new ImageIcon("./data/cold.png"));
        }


        superBottom.add(weather);

        //////// WINDOW VISIBILITY STUFF
        window.setContentPane(main);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        main.add(top, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        main.add(middleTop,gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        main.add(middleBottom,gbc);
        gbc.gridx = 0;
        gbc.gridy = 3;
        main.add(entryBottom,gbc);
        gbc.gridx = 0;
        gbc.gridy = 4;
        main.add(bottom,gbc);
        gbc.gridx = 0;
        gbc.gridy = 5;
        main.add(superBottom,gbc);
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
            deleteChooser();
        } else if (e.getActionCommand().equals("newClick")) {
            newClick();
        } else if (e.getActionCommand().equals("saveClick")) {
            callSave();
        }
        if (income.isSelected()) {
            clearIncome();
        } else if (expense.isSelected()) {
            clearExpense();
        } else if (invest.isSelected()) {
            clearInvest();
        } else if (debt.isSelected()) {
            clearDebt();
        }
        comboBoxAction(e);
    }

    private void clearIncome() {
        radioIndex = 1;
        expense.setSelected(false);
        debt.setSelected(false);
        invest.setSelected(false);
    }

    private void clearExpense() {
        radioIndex = 2;
        income.setSelected(false);
        debt.setSelected(false);
        invest.setSelected(false);
    }

    private void clearInvest() {
        radioIndex = 3;
        expense.setSelected(false);
        income.setSelected(false);
        debt.setSelected(false);
    }

    private void clearDebt() {
        radioIndex = 4;
        expense.setSelected(false);
        invest.setSelected(false);
        income.setSelected(false);
    }

    private void callSave() {
        try {
            saveButton();
        } catch (Exception p) {
            p.printStackTrace();
        }
    }

    private void deleteChooser() {
        try {
            deleteChoice();
            // "UI Confirmation Alert, A3.wav" by InspectorJ (www.jshaw.co.uk) of Freesound.org
            // https://freesound.org/people/InspectorJ/sounds/403006/
            URL sound = new File("./data/delete.wav").toURI().toURL();
            java.applet.AudioClip clip = java.applet.Applet.newAudioClip(sound);
            clip.play();
            JOptionPane.showMessageDialog(null, "Transaction has been deleted!",
                    "Deleted", JOptionPane.WARNING_MESSAGE);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void deleteChoice() throws Exception {
        if (incomeIndex >= 0) {
            deleteIncome();
        } else if (expenseIndex >= 0) {
            deleteExpense();
        } else if (investIndex >= 0) {
            deleteInvest();
        } else if (debtIndex >= 0) {
            deleteDebt();
        }
        descField.setText("");
        amtField.setText("");
        rateField.setText("");
        termField.setText("");
    }

    private void saveButton() throws NegativeAmt, FileNotFoundException, UnsupportedEncodingException {
        income.setEnabled(false);
        expense.setEnabled(false);
        invest.setEnabled(false);
        debt.setEnabled(false);
        delete.setEnabled(false);
        if (radioIndex == 1) {
            incomeSave();
        } else if (radioIndex == 2) {
            expenseSave();
        } else if (radioIndex == 3) {
            investSave();
        } else if (radioIndex == 4) {
            debtSave();
        }
        save.setEnabled(false);
    }

    private void incomeSave() throws NegativeAmt, FileNotFoundException, UnsupportedEncodingException {
        String desc = descField.getText();
        Double amount = Double.valueOf(amtField.getText());
        program.dailyMode.entry("Income", desc, amount);
        incomeListModel.clear();
        incomeFlag = false;
        loadIncome();
        incomeIndex = -1;
        program.saveData();
    }

    private void expenseSave() throws NegativeAmt, FileNotFoundException, UnsupportedEncodingException {
        String desc = descField.getText();
        Double amount = Double.valueOf(amtField.getText());
        program.dailyMode.entry("Expense", desc, amount);
        expenseListModel.clear();
        expenseFlag = false;
        loadExpense();
        expenseIndex = -1;
        program.saveData();
    }

    private void investSave() throws NegativeAmt, FileNotFoundException, UnsupportedEncodingException {
        String desc = descField.getText();
        Double amount = Double.valueOf(amtField.getText());
        Double rate = Double.valueOf(rateField.getText());
        int term = Integer.parseInt(termField.getText());
        program.longTermMode.entry("Investment", desc, amount, rate, term);
        investListModel.clear();
        investFlag = false;
        loadInvest();
        investIndex = -1;
        program.saveData();
    }

    private void debtSave() throws NegativeAmt, FileNotFoundException, UnsupportedEncodingException {
        String desc = descField.getText();
        Double amount = Double.valueOf(amtField.getText());
        Double rate = Double.valueOf(rateField.getText());
        int term = Integer.parseInt(termField.getText());
        program.longTermMode.entry("Debt", desc, amount, rate, term);
        debtListModel.clear();
        debtFlag = false;
        loadDebt();
        debtIndex = -1;
        program.saveData();
    }

    private void newClick() {
        descField.setText("");
        amtField.setText("");
        rateField.setText("");
        termField.setText("");
        save.setEnabled(true);
        if (sysChoice == 1) {
            sysChoiceDay();
        } else {
            descField.setEnabled(true);
            amtField.setEnabled(true);
            rateField.setEnabled(true);
            termField.setEnabled(true);
            income.setEnabled(false);
            expense.setEnabled(false);
            invest.setEnabled(true);
            debt.setEnabled(true);
        }
    }

    private void sysChoiceDay() {
        descField.setEnabled(true);
        amtField.setEnabled(true);
        income.setEnabled(true);
        expense.setEnabled(true);
        invest.setEnabled(false);
        debt.setEnabled(false);
    }

    private void deleteIncome() throws Exception {
        program.dailyMode.delete("Income", incomeIndex);
        incomeFlag = false;
        incomeListModel.clear();
        loadIncome();
        incomeIndex = -1;
        program.saveData();
    }

    private void deleteExpense() throws Exception {
        program.dailyMode.delete("Expense", expenseIndex);
        expenseFlag = false;
        expenseListModel.clear();
        loadExpense();
        expenseIndex = -1;
        program.saveData();

    }

    private void deleteInvest() throws Exception {
        program.longTermMode.delete("Investment", investIndex);
        investFlag = false;
        investListModel.clear();
        loadInvest();
        investIndex = -1;
        program.saveData();

    }

    private void deleteDebt() throws Exception {
        program.longTermMode.delete("Debt", debtIndex);
        debtFlag = false;
        debtListModel.clear();
        loadDebt();
        debtIndex = -1;
        program.saveData();
    }

    private void comboBoxAction(ActionEvent e) {
        // CITED: https://www.youtube.com/watch?v=iOV_oaJhABQ
        if (e.getSource() == comboBox) {
            JComboBox cb = (JComboBox)e.getSource();
            String tranType = (String)cb.getSelectedItem();
            assert tranType != null;
            switch (tranType) {
                case "Day to Day Transactions":
                    preLoadDay();
                    break;
                case "Long Term Transactions":
                    preLoadLong();
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + tranType);
            }
            newEnt.setEnabled(true);
            delete.setEnabled(false);
            save.setEnabled(false);
        }
    }

    private void preLoadDay() {
        sysChoice = 1;
        loadIncome();
        loadExpense();
        descField.setEnabled(false);
        amtField.setEnabled(false);
        rateField.setEnabled(false);
        termField.setEnabled(false);
        income.setSelected(false);
        expense.setSelected(false);
        debt.setSelected(false);
        invest.setSelected(false);
        descField.setText("");
        amtField.setText("");
        rateField.setText("");
        termField.setText("");
    }

    private void preLoadLong() {
        loadInvest();
        loadDebt();
        sysChoice = 2;
        descField.setEnabled(false);
        amtField.setEnabled(false);
        rateField.setEnabled(false);
        termField.setEnabled(false);
        income.setSelected(false);
        expense.setSelected(false);
        debt.setSelected(false);
        invest.setSelected(false);
        descField.setText("");
        amtField.setText("");
        rateField.setText("");
        termField.setText("");
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
            middleTop.setBorder(BorderFactory.createTitledBorder("Income Entries: "));
            middleTop.setFont(new Font("News Gothic MT", Font.BOLD, 12));
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
            middleBottom.setBorder(BorderFactory.createTitledBorder("Expense Entries: "));
            middleBottom.setFont(new Font("News Gothic MT", Font.BOLD, 12));
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
            middleTop.setBorder(BorderFactory.createTitledBorder("Investment Entries: "));
            middleTop.setFont(new Font("News Gothic MT", Font.BOLD, 12));
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
            middleBottom.setBorder(BorderFactory.createTitledBorder("Debt Entries: "));
            middleBottom.setFont(new Font("News Gothic MT", Font.BOLD, 12));
        }
    }

    public static void main(String[] args) throws IOException, NegativeAmt {
        new Main();
    }
}
