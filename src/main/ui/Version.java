package ui;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

public interface Version {

    public void loadData();

    public void saveData() throws FileNotFoundException, UnsupportedEncodingException;

    public void run() throws FileNotFoundException, UnsupportedEncodingException;

}
