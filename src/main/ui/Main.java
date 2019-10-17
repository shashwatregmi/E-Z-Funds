package ui;

import model.exceptions.NegativeAmt;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, NegativeAmt {
        Version program = new Program();
        try {
            program.loadData();
        } catch (NegativeAmt negativeamt) {
            System.out.println("Error. Negative Amount in file.");
        }

        program.run();
    }
}
