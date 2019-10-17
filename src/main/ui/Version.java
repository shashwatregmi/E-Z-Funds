package ui;

import model.exceptions.NegativeAmt;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

public interface Version {

    public void loadData() throws NegativeAmt;

    public void saveData() throws FileNotFoundException, UnsupportedEncodingException;

    public void run() throws FileNotFoundException, UnsupportedEncodingException, NegativeAmt;

}
