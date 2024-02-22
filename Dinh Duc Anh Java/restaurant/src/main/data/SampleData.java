package main.data;

import main.model.employee.Cook;
import main.model.employee.Driver;
import main.model.employee.Employee;
import main.model.employee.Waiter;
import main.model.menu.MenuItem;

import java.util.ArrayList;
import java.util.List;


public class SampleData {


    public static List<MenuItem> menuItemList () {
        List<MenuItem> menuItemList = new ArrayList<>();
        menuItemList.add(new MenuItem("Zupa", "Kwasnica goralska", 15));
        menuItemList.add(new MenuItem("Danie dnia", "Kotlet schabowy + ziemniaki + surowka", 30));
        menuItemList.add(new MenuItem("Pizza", "45 cm z szynka, serem i ananasem", 45));
        menuItemList.add(new MenuItem("Zapienanka", "Ser, szynka, prazona cebulka", 12));
        menuItemList.add(new MenuItem("Deser", "Lody czekoladowe z awokado i polewa owocowa", 15));
        return menuItemList;
    }


    public static  List<Employee> employeeList (){
        List<Employee> employees = new ArrayList<>();
        employees.add(new Cook("Jan", "Kucharski", "504 585 585"));
        employees.add(new Cook("Piotr", "Wesolowski", "752 585 255"));
        employees.add(new Waiter("Daniel", "Wolski", "792 145 258"));
        employees.add(new Driver("Anna", "Wysocka", "521 458 652"));

        return employees;
    }

}
