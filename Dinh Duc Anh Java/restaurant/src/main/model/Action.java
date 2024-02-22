package main.model;

import java.util.List;

public interface Action <T> {

    public boolean add (T t);
    public boolean remove(int uId);
    public void printAll();
}
