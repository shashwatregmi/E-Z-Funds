package ui;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Program program = new Program();
        program.loadData();
        program.run();
    }
}
