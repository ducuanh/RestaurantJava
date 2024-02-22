package main.model.employee;

public class Driver extends Supplier {

    public Driver(String name, String surname, String phone) {
        super(name, surname, phone);
        this.delivery_time = 2;

    }

    @Override
    public void print() {
        System.out.printf("%1$-25s %2$-25s %3$-25s %4$-25s %5$-10s %6$-25s",
                "IMIE", "NAZWISKO", "NR TELEFONU", "ZAWOD", "NAPIWKI [PLN]", "ZREALIZOWANE ZAMOWIENIA");
        System.out.println();

        System.out.printf("%1$-25s %2$-25s %3$-25s %4$-25s %5$-10s %6$-25s",
                name, surname, phone, "KIEROWCA", tip, completedOrders);
    }
}
