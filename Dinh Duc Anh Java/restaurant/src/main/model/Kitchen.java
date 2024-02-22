package main.model;

import main.model.employee.Cook;
import main.model.order.Order;
import main.model.order.RemoteOrder;
import main.model.order.StationaryOrder;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Kitchen implements Action<Order> {

    private List<Order> stationaryOrders;
    private List<Order> remoteOrders;
    private List<Cook> cooks;

    // second
    private final int COOKING_TIME_ONE_MENU_ITEM = 30;
//    private final int COOKING_TIME_ONE_MENU_ITEM = 3;
    private final int COOK_TIME_REDUCE = 2;

    public Kitchen() {
        this.stationaryOrders = new ArrayList<>();
        this.remoteOrders = new ArrayList<>();
        this.cooks = new ArrayList<>();
    }

    @Override
    public boolean add(Order order) {
        String classSimpleName = order.getClass().getSimpleName();

        switch (classSimpleName){
            case "RemoteOrder":
                return remoteOrders.add((RemoteOrder) order);

            case "StationaryOrder":
                return stationaryOrders.add((StationaryOrder) order);
        }

        return false;
    }

    @Override
    public boolean remove(int uId) {

        return false;
    }

    @Override
    public void printAll() {

        System.out.printf("%1$-10s %2$-20s %3$-30s %4$-30s",
                "ID", "TYP ZAMOWIENIA", "NR STOLIKA", "CZAS OCZEKIWANIA [min]");
        System.out.println();
        stationaryOrders.forEach(Order::print);


        System.out.printf("%1$-10s %2$-20s %3$-30s %4$-30s",
                "\nID", "TYP ZAMOWIENIA", "ADRES DOSTAWY", "CZAS OCZEKIWANIA [min]");
        System.out.println();
        remoteOrders.forEach(Order::print);
    }

    public Order startCook (List<Cook> cookList){
        this.cooks = cookList;

        Order order = getExpiredOrder();

        if (order != null){
            System.out.println("+++\t PRZEDAWNIONE ZAMOWIENIE KLIENT OTRZYMAL RABAT 20 %\t +++");
            cook(order);
        }

        if (stationaryOrders.size() > 0){
            return cookStationaryOrder();
        }
        else if (remoteOrders.size() > 0){
            return cookRemoteOrder();
        }

        return null;
    }

    private Order cookStationaryOrder (){
        Order order = stationaryOrders.get(0);

        if (order.getMenuItems().size() == 0){
            return order;
        }

        if (cook(order)){
            stationaryOrders.remove(order);
        }

        return order;
    }

    private Order cookRemoteOrder() {
        Order order = remoteOrders.get(0);

        if (order.getMenuItems().size() == 0){
            return order;
        }

        if (cook(order)){
            remoteOrders.remove(order);
        }

        return order;
    }

    private boolean cook (Order order){
        int cookTime = COOKING_TIME_ONE_MENU_ITEM - (cooks.size() * COOK_TIME_REDUCE);

        if (cookTime < 0){
            cookTime = 2;
        }

        for (int i=0; i < order.getMenuItems().size(); i ++){
            try {
                Thread.sleep(COOKING_TIME_ONE_MENU_ITEM);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    private Order getExpiredOrder (){
        Order order = findExpired(stationaryOrders);

        if (order ==null){
            order = findExpired(remoteOrders);
        }

        return order;
    }
    private Order findExpired (List<Order> orderList){
        for (Order order: orderList){
            if (order.getWaitingTime() > 15){
                order.setExpired(true);

                boolean refused = new Random().nextBoolean();
                if (refused){
                    System.out.println("---\t PRZEDAWNIONE ZAMOWIENIE KLIENT ODMOWIL REALIZACJE\t ---");
                    order.print();
                    orderList.remove(order);
                    return null;
                }
                else {
                    return order;
                }
            }
        }
        return null;
    }
}
