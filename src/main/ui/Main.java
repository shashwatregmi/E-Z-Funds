package ui;

import model.TranList;
import model.exceptions.NegativeAmt;

import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Citation: The basic GUI example from the edX D11 website
// <https://edge.edx.org/courses/course-v1:UBC+CPSC210+all/courseware/f636f4e1dd5348ed8f6dc7c3defed983/cba8ffaf475e4b9f
// bba32931df89c90a/1?activate_block_id=block-v1%3AUBC%2BCPSC210%2Ball%2Btype%40vertical%2Bblock%40d13dcf17afb440b1826c4
// 45e272d92c6>

public class Main extends JFrame implements ActionListener {

    private JLabel label;
    private JTextField field;
    private JButton dayBtn;
    private JButton ltBtn;
    private JButton newEnt;
    private JButton delete;
    private JButton report;
    private JComboBox comboBox = new JComboBox();
    private DefaultListModel<String> listModel = new DefaultListModel<>();
    private JList incomeList = new JList<>(listModel);
    private Program program;
    private JPanel main = new JPanel();
    private JPanel top = new JPanel();
    private JPanel middle = new JPanel();
    private JPanel bottom = new JPanel();

    //CITATION for gridbaglayout reference:
    // https://examples.javacodegeeks.com/desktop-java/swing/java-swing-layout-example/

    private Main() throws IOException, NegativeAmt {
        JFrame window = new JFrame("Personal Finance Manager");
        window.setDefaultCloseOperation(EXIT_ON_CLOSE);
        window.setSize(600, 400);


        ////////////////////// TOP PANEL
        label = new JLabel("Transaction Type: ");
        comboBox.addItem("Day to Day Transactions");
        comboBox.addItem("Long Term Transactions");
        comboBox.setSelectedIndex(-1);
        comboBox.addActionListener(this);
        top.add(label);
        top.add(comboBox);


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



        //////MIDDLE PANEL
        middle.add(incomeList);


        //////// WINDOW VISIBILITY STUFF
        window.setContentPane(main);
        main.add(top);
        main.add(middle);
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

        comboBoxAction(e);

        if (e.getActionCommand().equals("newClick")) {
            try {
                program.dailyMode.newEntry();
            } catch (FileNotFoundException | NegativeAmt ex) {
                ex.printStackTrace();
            }
        }
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
                    break;
                case "Long Term Transactions":
                    program.setSysChoice(2);
                    label.setText("lt");
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + tranType);
            }
        }
    }

    private void loadIncome() {
        program.setSysChoice(1);
        TranList rep = program.dailyMode.transactions.get("Income");
        for (int i = 0; i < rep.getSize(); i++) {
            listModel.addElement(rep.getTrans(i).getTransDetail());
        }
        middle.setVisible(false);
        middle.setVisible(true);
    }

    public static void main(String[] args) throws IOException, NegativeAmt {
        new Main();
    }
}
