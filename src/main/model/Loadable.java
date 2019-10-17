package model;

import model.exceptions.NegativeAmt;

public interface Loadable {

    public void loadData() throws NegativeAmt;
}
