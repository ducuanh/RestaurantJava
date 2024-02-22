package main.model.menu;

import main.data.DataOperation;
import main.model.Action;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class RestaurantMenu implements Action<MenuItem>, DataOperation, Serializable {

    private List<MenuItem> menuItems;

    public RestaurantMenu() {
        this.menuItems = new ArrayList<>();
    }

    @Override
    public boolean add(MenuItem menuItem) {
        return menuItems.add(menuItem);
    }

    @Override
    public boolean remove(int uId) {
        MenuItem menuItem = menuItems.stream().filter( menuItem1 -> menuItem1.getUId() == uId ).findFirst().orElse(null);

        if (menuItem != null){
            return menuItems.remove(menuItem);
        }

        return false;
    }

    @Override
    public void printAll() {
        System.out.printf("%1$-4s %2$-20s %3$-30s %4$-15s %5$-10s",
                "\nID", "NAZWA", "OPIS", "CENA [PLN]", "AKTYWNA POZYCJA\n");

        menuItems.forEach(MenuItem::print);
    }

    @Override
    public boolean write() {
        try {
            ObjectOutputStream outputStream =  new ObjectOutputStream(new FileOutputStream(
                    "restaurantMenu.txt"));

            for (MenuItem menuItem : menuItems){
                outputStream.writeObject(menuItem);
            }
            outputStream.close();
            return true;
        }
        catch (IOException e){
            System.out.println("-.-BLAD ZAPISU");
        }
        return false;
    }

    @Override
    public boolean read() {
        try {
            ObjectInputStream stream = new ObjectInputStream(new FileInputStream("restaurantMenu.txt"));
            menuItems = new ArrayList<>();
            while (true)
                try {
                    {
                        menuItems.add((MenuItem)stream.readObject());
                    }
                }
                catch (Exception e) {
                    break;}

            stream.close();
        }
        catch (IOException e){
            System.out.println("-.-BLAD ODCZYTU");
            return false;
        }
        return true;
    }

    public boolean setUnavailable(int uId){
        MenuItem menuItem = menuItems.stream().filter( menuItem1 -> menuItem1.getUId() == uId ).findFirst().orElse(null);

        if (menuItem != null){
            menuItem.setActive(false);
            return true;
        }
        return false;
    }

    public MenuItem getMenuItem(int menuItemId) {
        if (menuItems.size() > menuItemId && menuItemId > -1){
            return menuItems.get(menuItemId);
        }
        return null;
    }

    public int getMenuItemsSize() {
        return menuItems.size();
    }

    public void setMenuItems(List<MenuItem> menuItemList) {
        this.menuItems = menuItemList;
    }
}
