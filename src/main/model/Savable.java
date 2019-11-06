package model;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

public interface Savable {

    void saveData(String path) throws FileNotFoundException, UnsupportedEncodingException;

}
