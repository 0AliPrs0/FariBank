package ir.ac.kntu;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
//        ArrayList<User> user = new ArrayList<>();
//        ArrayList<Request> requests = new ArrayList<>();
        Role role;

        do {
            Menus.getInstance().printTheMainMenu();
            role = Menus.getInstance().getOptionMainMenu();
            handleTheMainMenu(role);
        } while (role != Role.EXIT);

        ScannerWrapper.getInstance().close();
    }

    public static void handleTheMainMenu(Role roles){
        switch (roles) {
            case USER -> handleUserRoles(user, requests);
            case SUPPORT -> handleSupportRoles();
            case EXIT -> System.out.println("Exiting the program ...");
            default -> System.out.println("Invalid Input!");
        }
    }

//    public static void main(String[] argv) {
//        Option option;
//
//        Menu.getInstance().printTheMenu();
//
//        option = Menu.getInstance().getOption();
//
//        while (option != Option.EXIT) {
//            handleTheOption(option);
//            Menu.getInstance().printTheMenu();
//            option = Menu.getInstance().getOption();
//        }
//        ScannerWrapper.getInstance().close();
//    }
}
