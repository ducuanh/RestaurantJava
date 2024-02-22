package main.model.order;


public class RemoteOrder extends Order {

    private String deliveryAddress;

    public RemoteOrder(String deliveryAddress) {
        super();
        this.deliveryAddress = deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    @Override
    public void print() {
        System.out.printf("%1$-10s %2$-20s %3$-30s %4$-30s",
                uId, "ZDALNE", deliveryAddress, getWaitingTime());
        System.out.println();
    }


}
