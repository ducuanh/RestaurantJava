package main.model.menu;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class MenuItem implements Serializable {

    private static int currentId = 1;

    private int uId;
    private String name;
    private String description;
    private double price;
    private boolean active;

    public MenuItem(String name, String description, double price) {
        this.uId = currentId;
        this.name = name;
        this.description = description;
        this.price = price;

        this.active = true;
        currentId++;
    }

    public int getUId() {
        return uId;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void print (){
        System.out.printf("%1$-4s %2$-20s %3$-30s %4$-15s %5$-10s",
                getUId(), name, description, price, stringActive());
        System.out.println();
    }

    private String stringActive (){
        String active;

        if (this.active){
            active = "TAK";
        }
        else {
            active = "NIE";
        }
        return active;
    }

    private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
        currentId = stream.readInt();
    }

    private void writeObject(ObjectOutputStream stream) throws IOException {
        stream.defaultWriteObject();
        stream.writeInt(currentId);
    }
}
