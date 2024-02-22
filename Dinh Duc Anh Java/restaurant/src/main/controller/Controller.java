package main.controller;

import main.data.SampleData;
import main.model.employee.Employee;
import main.model.menu.MenuItem;
import main.Restaurant;
import main.model.order.Order;
import main.model.order.RemoteOrder;
import main.model.order.StationaryOrder;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Controller {

    private Restaurant restaurant;

    public Controller() {
        this.restaurant = new Restaurant ();
    }


    public void startInitialStructure (){
        restaurant.setInitialStructure (SampleData.menuItemList(), SampleData.employeeList());
        List<Order> orderList = new ArrayList<>();

        for (int i =0; i < 10; i ++){
            Order order = getRandomOrder();
            orderList.add(order);
        }

        for (Order order: orderList){
            restaurant.addOrder(order);
        }
    }

    public void startRestaurant() {
        System.out.println("***\tROZPOCZECIE PRACY RESTAURACJI\t***");
        restaurant.startRestaurant();
    }

    public void suspendRestaurant() {
        System.out.println("***\tZATRZYMANIE PRACY RESTAURACJI\t***");
        restaurant.suspendRestaurant();
    }

    public void printMenuItems() {
        System.out.println("***\tMENU RESTAURACJI\t***");
        restaurant.printMenuItems();
    }

    public void addMenuItem(MenuItem menuItem) {
        System.out.println("***\tDODAWANIE NOWEJ POZYCJI DO MENU\t***");
        boolean result = restaurant.addMenuItem(menuItem);
        printResult(result);
    }

    public void removeMenuItem(int idMenuItem) {
        System.out.println("***\tUSUWANIE POZYCJI Z MENU\t***");
        boolean result = restaurant.removeMenuItem(idMenuItem);
        printResult(result);
    }

    public void setUnavailableMenuItem(int idMenuItem) {
        System.out.println("***\tOZNACZENIE POZYCJI W MENU JAKO NIEAKTYWNA\t***");
        boolean result = restaurant.setUnavailableMenuItem( idMenuItem);
        printResult(result);
    }

    public void saveMenuItems() {
        System.out.println("***\tZAPISYWANIE MENU DO PLIKU\t***");
        boolean result = restaurant.saveMenuItems();
        printResult(result);
    }

    public void readMenuItems() {
        System.out.println("***\tODCZYTANIE MENU Z PLIKU\t***");
        boolean result = restaurant.readMenuItems();
        printResult(result);
    }

    public void addOrder(Order order) {
        System.out.println("***\tSKLADANIE ZAMOWIENIA OD KLIENTA\t***");
        boolean result = restaurant.addOrder(order);
    }

    public void addRandomOrder() {
        System.out.println("***\tSKLADANIE ZAMOWIENIA LOSOWEGO\t***");
        boolean result = restaurant.addOrder(getRandomOrder());
        printResult(result);
    }

    public void printWaitingOrder() {
        System.out.println("***\tWYPISANIE ZAMOWIEN OCZEKUJACYCH NA REALIZACJE\t***");
        restaurant.printWaitingOrder();
    }

    public void printFinishedOrder() {
        System.out.println("***\tWYPISANIE ZAMOWIEN ZREALIZOWANCYH\t***");
        restaurant.printRealizedOrders();
    }

    public void countRevenue() {
        System.out.println("***\tWYLICZENIE UTARGU\t***");
        System.out.println("UTARG = " + restaurant.countRevenue() + " [PLN]");
    }

    public void addEmployee(Employee employee) {
        System.out.println("***\tZATRUDNIENIE PRACOWNIKA\t***");
        boolean result = restaurant.addEmployee(employee);
        printResult(result);
    }

    public void removeEmployee(int employeeId) {
        System.out.println("***\tZWOLNIENIE PRACOWNIKA\t***");
        boolean result = restaurant.removeEmployee(employeeId);
        printResult(result);
    }

    public void printEmployeeDetails(int employeeId) {
        System.out.println("***\tWYSWIETLENIE INFOMACJI O PRACOWNIKU\t***");
        restaurant.printEmployeeDetails(employeeId);
    }

    public void printEmployees() {
        System.out.println("***\tWYSWIETLENIE LISTY PRACOWNIKOW\t***");
        restaurant.printEmployees();
    }

    private void printResult(boolean result){
        if (result){
            System.out.println("+++\t ZAKONCZONE SUKCESEM \t+++");
        }
        else {
            System.out.println("---\t ZAKONCZONE NIEPOWODZENIEM \t---");
        }
    }

    public MenuItem getMenuItem(int menuItemId) {
        return restaurant.getMenuItem(menuItemId);
    }

    private Order  getRandomOrder (){
        Order order;
        boolean isStationary = new Random().nextBoolean();

        if (isStationary){
            order = new StationaryOrder(getRandomNumber(1, 20));
        }
        else {
            order = new RemoteOrder("Warszawa, Grzybowska nr : " + getRandomNumber(1,300));
        }

        int allDishTOAdd = getRandomNumber(1, 5);
        int sizeMenuItems = restaurant.getMenuItemsSize();

        for (int i =0; i < allDishTOAdd; i++){
            int menuItemId = getRandomNumber(0, sizeMenuItems -1);
            MenuItem menuItem = restaurant.getMenuItem(menuItemId);
        }

        return order;
    }

    private int getRandomNumber (int min, int max){
        Random random = new Random();

        return random.nextInt(max - min + 1) + min;
    }
}
