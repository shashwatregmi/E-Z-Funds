package model;

public interface Category {

    public void insert(Transaction trans);

    public void delete(int i);

    public int getSize();

    public Transaction getTrans(int i);

    public boolean contains(Transaction trans);

    public void report();

}
