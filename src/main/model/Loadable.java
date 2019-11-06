package model;

import model.exceptions.NegativeAmt;

import java.io.IOException;

public interface Loadable {

    public void loadData(String path) throws NegativeAmt, IOException;
}
