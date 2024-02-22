package main;

import main.model.Kitchen;
import main.model.employee.Employee;
import main.model.employee.RestaurantEmployees;
import main.model.employee.Supplier;
import main.model.menu.MenuItem;
import main.model.menu.RestaurantMenu;
import main.model.order.Order;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;

public class Restaurant {

    private List<Order> realizedOrder;
    private List<Order> deliveryOrders;
    private RestaurantMenu restaurantMenu;
    private RestaurantEmployees restaurantEmployees;
    private Kitchen kitchen;
    private boolean works;

    public Restaurant() {
        this.realizedOrder = new ArrayList<>();
        this.deliveryOrders = new ArrayList<>();
        this.restaurantMenu = new RestaurantMenu();
        this.restaurantEmployees = new RestaurantEmployees();
        this.kitchen = new Kitchen();
        this.works = false;
    }

    public boolean addMenuItem(MenuItem menuItem) {
        return restaurantMenu.add(menuItem);
    }

    public void printMenuItems() {
        restaurantMenu.printAll();
    }

    public boolean removeMenuItem(int idMenuItem) {
        return restaurantMenu.remove(idMenuItem);
    }

    public boolean setUnavailableMenuItem(int idMenuItem) {
        return restaurantMenu.setUnavailable(idMenuItem);
    }

    public boolean saveMenuItems() {
        return restaurantMenu.write();
    }

    public boolean readMenuItems() {
        return restaurantMenu.read();
    }

    public int getMenuItemsSize() {
        return restaurantMenu.getMenuItemsSize ();
    }

    public boolean addEmployee(Employee employee) {
        return restaurantEmployees.add(employee);
    }

    public boolean removeEmployee(int employeeId) {
        return restaurantEmployees.remove(employeeId);
    }

    public void printEmployeeDetails(int employeeId) {
        restaurantEmployees.printEmployeeDetails(employeeId);
    }

    public void printEmployees() {
        restaurantEmployees.printAll();
    }

    public MenuItem getMenuItem(int menuItemId) {
        return restaurantMenu.getMenuItem(menuItemId);
    }

    public boolean addOrder(Order order) {
        return kitchen.add(order);
    }

    public void printWaitingOrder() {
        kitchen.printAll();
    }


    public void printRealizedOrders (){
        System.out.printf("%1$-10s %2$-20s %3$-30s %4$-20s",
                "ID", "TYP ZAMOWIENIE", "ADRES LUB NR STOLIKA",  "CZAS REALIZACJI");
        System.out.println();

        realizedOrder.forEach(Order::print);
    }

    public double countRevenue() {
        double revenue = 0;

        for (Order order: realizedOrder){
            revenue += order.getPrice();
        }

        return revenue;
    }

    public void startRestaurant() {
        if (!works){
            startKitchen();
            startDeliveryRemote();
            startDeliveryStationary();
        }
        else {
            System.out.println("---\t RESTAURACJA JUZ ROZPOCZELA PRACE\t---");
        }
    }

    public void suspendRestaurant() {
        if (works){
            works =false;
        }
        else {
            System.out.println("---\t NIE MOZNA ZATRZYMAC PRACY GDYZ RESTAURACJE JEJ JESZCZE NIE ROZPOCZELA !!!\t---");
        }
    }

    private void startKitchen() {
        works = true;
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (works){
                    Order order = kitchen.startCook(restaurantEmployees.getCooks());

                    if (order != null){
                        deliveryOrders.add(order);
                    }
                    else {
                        try {
                            Thread.sleep(3000);
                        }
                        catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
        });
        thread.start();
    }

    private void startDeliveryRemote(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (works){
                    List<Supplier> suppliers = restaurantEmployees.getDrivers();
                    if (suppliers.size() <1){
                        try {
                            Thread.sleep(3000);
                        }
                        catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    else {
                        for (Supplier supplier: suppliers){
                            if (supplier.getClass().getSimpleName().equals("Driver")){
                                for (Order order: deliveryOrders){
                                    if (order.getClass().getSimpleName().equals("RemoteOrder")){
                                        startDelivery(order, supplier);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        });
        thread.start();
    }

    private void startDeliveryStationary(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (works){
                    List<Supplier> suppliers = restaurantEmployees.getWaiters();
                    if (suppliers.size() <1){
                        try {
                            Thread.sleep(3000);
                        }
                        catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    else {
                        for (Supplier supplier: suppliers){
                            if (supplier.getClass().getSimpleName().equals("Waiter")){
                                for (Order order: deliveryOrders){
                                    if (order.getClass().getSimpleName().equals("StationaryOrder")){
                                        startDelivery(order, supplier);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        });
        thread.start();
    }

    private void startDelivery(Order order, Supplier supplier){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    Thread.sleep(supplier.getDelivery_time() * 60L *  1000L);
                    deliveryOrders.remove(order);
                    realizedOrder.add(order);

                    if (order.getWaitingTime()< 15){
                        double tip = order.getPrice() *0.1 -order.getWaitingTime();
                        supplier.addTip(tip);
                    }
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.run();
    }

    public void setInitialStructure(List<MenuItem> menuItemList, List<Employee> employeeList) {
        this.restaurantMenu.setMenuItems(menuItemList);
        this.restaurantEmployees.setEmployeeList(employeeList);
    }

}
