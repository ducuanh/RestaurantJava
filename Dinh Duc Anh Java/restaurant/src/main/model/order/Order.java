package main.model.order;


import main.model.menu.MenuItem;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Order {

    private static int id;
    private boolean expired;

    protected String uId;
    private List<MenuItem> menuItems;
    protected long timeStart;

    public Order() {
        this.expired = false;
        LocalDateTime localDateTime = LocalDateTime.now();
        this.uId = id + " "+ localDateTime.getHour() + ":" +localDateTime.getMinute();
        this.menuItems = new ArrayList<>();
        id ++;
        this.timeStart = System.currentTimeMillis();
    }

    public int getWaitingTime() {
        long timeNow = System.currentTimeMillis();

        long waitingTime = ((timeNow - timeStart) / 1000) / 60;
        return (int) waitingTime;
    }

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    public boolean isExpired() {
        return expired;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }

    public double getPrice(){
        double price = 0;

        double tempPrice;
        for (MenuItem menuItem: menuItems){
            tempPrice = menuItem.getPrice();

            if (expired){
                tempPrice = tempPrice *0.8;
            }
            price += tempPrice;
        }
        return price;
    }

    public void addMenuItem(MenuItem menuItem){
        menuItems.add(menuItem);
    }

    public void print (){
        System.out.printf("%1$-10s %2$-20s",
                uId, getWaitingTime());
        System.out.println();
    }
}
