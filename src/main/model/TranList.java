package model;

import model.exceptions.NegativeAmt;
import model.trantype.LongTermTran;
import model.trantype.Transaction;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public abstract class TranList {

    public abstract void saveData(String path) throws FileNotFoundException, UnsupportedEncodingException;

    public abstract void loadData(String path) throws FileNotFoundException, UnsupportedEncodingException,
            NegativeAmt, IOException;

    public abstract void insert(Transaction trans);

    public abstract void insert(LongTermTran trans);


    public abstract int getSize();

    public abstract void delete(int i);

    public abstract Transaction getTrans(int i);

}
