package main.model.employee;

public class Employee {

    protected String name;
    protected String surname;
    protected String phone;
    protected int completedOrders;

    public Employee(String name, String surname, String phone) {
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.completedOrders = 0;
    }

    public void print(){
        System.out.printf("%1$-25s %2$-25s %3$-25s",
                "IMIE", "NAZWISKO", "NR TELEFONU");

        System.out.printf("%1$-25s %2$-25s %3$-25s",
                name, surname, phone);

    }

    @Override
    public String toString() {
        return name + " : " + surname;
    }
}
