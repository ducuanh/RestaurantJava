package main.model.employee;

import main.exception.LastEmployeeFireFromRestaurant;
import main.model.Action;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RestaurantEmployees implements Action<Employee> {

    private List<Employee> employeeList;

    public RestaurantEmployees() {
        this.employeeList = new ArrayList<>();
    }

    @Override
    public boolean add(Employee employee) {
        return employeeList.add(employee);
    }

    @Override
    public boolean remove(int uId) {
        boolean result = false;

        if (employeeList.size() > uId){
            Employee employee = employeeList.get(uId);
            result = employeeList.remove(employee);

            checkIsLastOnPosition(employee.getClass().getSimpleName());
        }
        return result;
    }

    @Override
    public void printAll() {
        System.out.printf("%1$-4s %2$-25s ",
                "\nID", "IMIE : NAZWISKO");

        for (int i =0; i < employeeList.size(); i ++){
            System.out.println();
            System.out.printf("%1$-4s %2$-25s ",
                    i, employeeList.get(i));
        }

    }

    public void printEmployeeDetails (int id){
        if (employeeList.size() > id){
            Employee employee = employeeList.get(id);

            employee.print();
        } else {
            System.out.println("---\t WPROWADZONY NIEPRAWIDLOWE ID!!! \t---");
        }

    }

    public void checkIsLastOnPosition (String positionClassSimpleName){
        if (employeeList.stream().filter(employee -> employee.getClass().getSimpleName().equals(positionClassSimpleName)).findAny().isEmpty()){
            try {
                throw new LastEmployeeFireFromRestaurant(positionClassSimpleName);
            }
            catch (LastEmployeeFireFromRestaurant e){
                e .printStackTrace();
            }
        }
    }

    public List<Cook> getCooks (){
        List<Employee> cookList = employeeList.stream().filter(employee -> employee.getClass().getSimpleName().equals("Cook")).collect(Collectors.toList());

        List<Cook> cooks = new ArrayList<>();
        cookList.forEach(o ->cooks.add((Cook)o));

        return cooks;
    }

    public List<Supplier> getSupplier (){
        List<Employee> supplierList = employeeList.stream().filter(employee -> employee.getClass().getSimpleName().equals("Supplier")).collect(Collectors.toList());

        List<Supplier> suppliers = new ArrayList<>();
        supplierList.forEach(o ->suppliers.add((Supplier)o));

        return suppliers;
    }

    public List<Supplier> getWaiters (){
        List<Employee> supplierList = employeeList.stream().filter(employee -> employee.getClass().getSimpleName().equals("Waiter")).collect(Collectors.toList());

        List<Supplier> waiters = new ArrayList<>();
        supplierList.forEach(o ->waiters.add((Supplier)o));

        return waiters;
    }

    public List<Supplier> getDrivers (){
        List<Employee> supplierList = employeeList.stream().filter(employee -> employee.getClass().getSimpleName().equals("Driver")).collect(Collectors.toList());

        List<Supplier> drivers = new ArrayList<>();
        supplierList.forEach(o ->drivers.add((Supplier) o));

        return drivers;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }
}
