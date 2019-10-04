package ui;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Version program = new Program();
        program.loadData();
        program.run();
    }
}
