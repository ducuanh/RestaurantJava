package main.exception;

public class LastEmployeeFireFromRestaurant extends Exception{


    public LastEmployeeFireFromRestaurant(String position ) {
        super("UWAGA ZOSTAL USUNIETY OSTATNI PRACAOWNIK NA STANOWISKU: " + position);
    }
}
