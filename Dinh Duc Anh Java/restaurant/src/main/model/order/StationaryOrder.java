package main.model.order;


public class StationaryOrder extends Order {

    private int table;

    public StationaryOrder(int table) {
        super();
        this.table = table;
    }

    @Override
    public void print() {
        System.out.printf("%1$-10s %2$-20s %3$-30s %4$-30s",
                uId, "STACJONARNE", table, getWaitingTime());
        System.out.println();
    }
}
