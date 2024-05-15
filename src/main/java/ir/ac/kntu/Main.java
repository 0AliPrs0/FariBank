package ir.ac.kntu;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
//        ArrayList<User> user = new ArrayList<>();
//        ArrayList<Request> requests = new ArrayList<>();
        RoleMenu role;

        do {
            Menus.getInstance().printTheMainMenu();
            role = Menus.getInstance().getOptionMainMenu();
            handleTheMainMenu(role);
        } while (role != RoleMenu.EXIT);

        ScannerWrapper.getInstance().close();
    }

    public static void handleTheMainMenu(RoleMenu roles){
        switch (roles) {
            case USER -> handleUserRoles(user, requests);
            case SUPPORT -> handleSupportRoles();
            case EXIT -> System.out.println("Exiting the program ...");
            default -> System.out.println("Invalid Input!");
        }
    }

    public static void handleUserRoles(){
        UserHandler.implementTheUserMenu();
    }
}
