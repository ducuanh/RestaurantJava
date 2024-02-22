package main.model.employee;

public class Cook extends Employee{

    private final int REDUCE_TIME = 1;

    public Cook(String name, String surname, String phone) {
        super(name, surname, phone);
    }

    @Override
    public void print() {
        System.out.printf("%1$-25s %2$-25s %3$-25s %4$-25s %5$-25s",
                "IMIE", "NAZWISKO", "NR TELEFONU", "ZAWOD", "ZREALIZOWANE ZAMOWIENIA");

        System.out.println();

        System.out.printf("%1$-25s %2$-25s %3$-25s %4$-25s %5$-25s",
                name, surname, phone, "KUCHARZ", completedOrders);

    }
}
