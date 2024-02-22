package main.view;


import main.controller.Controller;
import main.model.employee.Cook;
import main.model.employee.Driver;
import main.model.employee.Employee;
import main.model.employee.Waiter;
import main.model.menu.MenuItem;
import main.model.order.Order;
import main.model.order.RemoteOrder;
import main.model.order.StationaryOrder;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {

    private Controller controller;
    private Scanner scanner;

    public Menu() {
        this.scanner = new Scanner(System.in);
        this.controller = new Controller();
        controller.startInitialStructure();
    }

    public void startMainMenu(){
        boolean runMenu = true;
        mainOptions();
        String userChoice;

        while (runMenu){
            userChoice = scanner.next();
            switch (userChoice){
                case "1":
                    startMenuMenu();
                    break;
                case "2":
                    startOrderMenu();
                    break;
                case "3":
                    startEmployeeMenu();
                    break;
                case ("4"):
                    controller.startRestaurant();
                    break;
                case ("5"):
                    controller.suspendRestaurant();
                    break;
                case "M":
                    mainOptions();
                    break;
                case "W":
                    runMenu = false;
                    break;
                default:
                    System.out.println("NIE MA TAKIEJ OPCJI !!!");
                    break;
            }
        }
        System.exit(0);
    }

    private void startMenuMenu (){
        boolean runMenu = true;
        String userChoice;
        int idMenuItem;
        
        menuOptions();

        while (runMenu){
            userChoice = scanner.next();
            switch (userChoice){
                case "1":
                    controller.printMenuItems();
                    break;
                case "2":
                    MenuItem menuItem = getMenuItem();
                    controller.addMenuItem(menuItem);
                    break;
                case "3":
                    idMenuItem = getId("pozycji z Menu");
                    controller.removeMenuItem(idMenuItem);
                    break;
                case ("4"):
                    idMenuItem = getId("pozycji z Menu");
                    controller.setUnavailableMenuItem(idMenuItem);
                    break;
                case ("5"):
                    controller.saveMenuItems();
                    break;
                case ("6"):
                    controller.readMenuItems();
                    break;
                case "M":
                    menuOptions();
                    break;
                case "0":
                    runMenu = false;
                    break;
                default:
                    System.out.println("NIE MA TAKIEJ OPCJI !!!");
                    break;
            }
            scanner.nextLine();
        }
    }
    private void startOrderMenu() {
        boolean runMenu = true;
        String userChoice;
        
        orderOptions();

        while (runMenu){
            userChoice = scanner.next();
            switch (userChoice){
                case "1":
                    Order order = getOrder();
                    controller.addOrder(order);
                    break;
                case "2":
                    controller.addRandomOrder();
                    break;
                case "3":
                    controller.printWaitingOrder();
                    break;
                case ("4"):
                    controller.printFinishedOrder();
                    break;
                case ("5"):
                    controller.countRevenue();
                    break;
                case "M":
                    orderOptions();
                    break;
                case "0":
                    runMenu = false;
                    break;
                default:
                    System.out.println("NIE MA TAKIEJ OPCJI !!!");
                    break;
            }
        }
        scanner.nextLine();
    }

    private void startEmployeeMenu() {
        boolean runMenu = true;
        String userChoice;
        int employeeId;
        
        employeeOptions();
        
        while (runMenu){
            userChoice = scanner.next();
            switch (userChoice){
                case "1":
                    Employee employee = getEmployee();
                    controller.addEmployee(employee);
                    break;
                case "2":
                    employeeId = getId("pracownika");
                    controller.removeEmployee(employeeId);
                    break;
                case "3":
                    employeeId = getId("pracownika");
                    controller.printEmployeeDetails(employeeId);
                    break;
                case ("4"):
                    controller.printEmployees();
                    break;
                case "M":
                    employeeOptions();
                    break;
                case "0":
                    runMenu = false;
                    break;
                default:
                    System.out.println("NIE MA TAKIEJ OPCJI !!!");
                    break;
            }
        }
        scanner.nextLine();
    }
    private void mainOptions(){
        String options = "\nMENU GLOWNE PROGRAMU" +
                "\n1\tMENU" +
                "\n2\tZAMOWIENIA" +
                "\n3\tPRACOWNICY" +
                "\n4\tRozpoczecie pracy restauracji" +
                "\n5\tZatrzymanie pracy restauracji" +
                "\nM\tWyswietl Menu" +
                "\nW\tWYJSCIE";
        showOptions(options);
    }

    private void menuOptions(){
        String options  =  "\nMENU" +
                "\n1\tWypisanie wszystkich pozycji" +
                "\n2\tDodanie nowe pozycji" +
                "\n3\tUsuniecie pozycji" +
                "\n4\tOznaczenie danej pozycji z Menu jako niedopstepnej" +
                "\n5\tZapisanie aktulanego Menu do pliku" +
                "\n6\tWczytanie aktualnego Menu z pliku" +
                "\nM\tWyswietl Menu" +
                "\n0\tPOWROT TO MENU GlOWNEGO";
        showOptions(options);
    }


    private void orderOptions(){
        String options  =  "\nZAMOWIENIE" +
                "\n1\tZlozenia zamowienia" +
                "\n2\tZlozenie losowego zamowienia " +
                "\n3\tWypisania zamowien ktore czekaja na realizacje" +
                "\n4\tWypisania zamowien ktore zostaly zrealizowane" +
                "\n5\tWyliczenia utargu" +
                "\nM\tWyswietl Menu" +
                "\n0\tPOWROT TO MENU GLOWNEGO";
        showOptions(options);
    }

    private void employeeOptions(){
        String options  =  "\nPRACOWNIK" +
                "\n1\tZatrudnij pracownika" +
                "\n2\tWyrzucenie istniejacego pracownika " +
                "\n3\tWypisanie informacji o danym pracownikow" +
                "\n4\tWylistowanie wszystkich pracownikow" +
                "\nM\tWyswietl Menu" +
                "\n0\tPOWROT TO MENU GLOWNEGO";
        showOptions(options);
    }

    private void showOptions (String s){
        String cutscene =  "\n########################################################################";
        String options = cutscene +
                "\nRESTAURACJA" +
                cutscene +
                s +
                cutscene;
        System.out.println(options);
    }

    private int getId(String objectType) {
        int id = -1;

        System.out.println("Wprowadz ID: " + objectType);

        try {
            id = scanner.nextInt();
        }
        catch (InputMismatchException e) {
            System.out.println("NIEPRAWIDLOWA WARTOSC!!! \tUZYJ CYFR");
        }

        scanner.nextLine();
        return id;
    }

    private MenuItem getMenuItem() {
        System.out.println("\tWPROWADZ NAZWE: ");
        String name = scanner.next();

        System.out.println("\tWPROWADZ OPIS: ");
        String description = scanner.next();

        double price = 0;
        boolean read = true;
        while (read){
            System.out.println("\tWPROWADZ CENE: ");
            try {
                price = scanner.nextDouble();
                read = false;
            }
            catch (InputMismatchException e){
                System.out.println("NIEPRAWIDLOWA WARTOSC!!! \tSPROBUJ PONOWNIE UZYWAJAC CYFR");
            }
            scanner.nextLine();
        }
        return new MenuItem(name, description, price);
    }

    private Order getOrder() {
        Order order = null;
        boolean read = true;

        String typeOrder;
        while (read){
            read = false;

            System.out.println("1\tSTACJONARNE\n" +
                    "2\tZDALNE\n" +
                    "WYBIERZ RODZAJ ZAMOWIENIE:\n ");

            typeOrder = scanner.next();
            switch (typeOrder){
                case "1":
                    int table = getId("stolika");
                    order = new StationaryOrder(table);
                    break;
                case "2":
                    System.out.println("\tWPROWADZ ADRES DOSTAWY");
                    String deliveryAddress = scanner.next();
                    order = new RemoteOrder(deliveryAddress);
                    break;
                default:
                    read = true;
                    System.out.println("NIEPRAWIDLOWA WARTOSC!!! \tSPROBUJ PONOWNIE UZYWAJAC WLASCIWYCH CYFR !!!");
                    break;
            }
        }

        read = true;
        int menuItemId;

        while (read){
            controller.printMenuItems();

            menuItemId = getId(" DANIA: \t***aby zakonczyc dodawanie dania wpisz -1");

            if (menuItemId == -1){
                read  = false;
            }
            else {
                MenuItem menuItem = controller.getMenuItem(menuItemId);
                if (menuItem != null){
                    order.addMenuItem(menuItem);
                }
                else {
                    System.out.println("---\tWPROWADZONO NIEPRAWIDŁOWE ID DANIA\t---");
                }
            }
        }

        return order;
    }

    private Employee getEmployee() {
        Employee employee = null;
        System.out.println("\tWPROWADZ IMIE: ");
        String name = scanner.next();

        System.out.println("\tWPROWADZ NAZWISKO: ");
        String surname = scanner.next();

        System.out.println("\tWPROWADZ NR TELEFONU: ");
        String phone = scanner.next();

        boolean read = true;
        String profession;

        while (read){
            System.out.println("1\tKUCHARZ\n" +
                    "2\tKELNER\n" +
                    "3\tKIEROWCA\n" +
                    "WYBIERZ STANOWISKO:\n "
                    );

            profession = scanner.next();
            read = false;

            switch (profession){
                case "1":
                    employee = new Cook(name, surname, phone);
                    break;

                case "2":
                    employee = new Waiter(name, surname, phone);
                    break;

                case "3":
                    employee = new Driver(name, surname, phone);
                    break;
                default:
                    read = true;
                    System.out.println("NIEPRAWIDLOWA WARTOSC!!! \tSPROBUJ PONOWNIE UZYWAJAC WLASCIWYCH CYFR !!!");
                    break;
            }
        }
        return employee;
    }

}
