package main.model.employee;

public class Supplier extends Employee {

    protected double tip;
    protected int delivery_time;

    public Supplier(String name, String surname, String phone) {
        super(name, surname, phone);
        this.tip = 0;
    }

    public int getDelivery_time() {
        return delivery_time;
    }

    public void addTip(double value){
        tip += value;
    }
}
