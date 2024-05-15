package ir.ac.kntu;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Bank myBank = new Bank();

        RoleMenu role;

        do {
            Menus.getInstance().printTheMainMenu();
            role = Menus.getInstance().getOptionMainMenu();
            handleTheMainMenu(role, myBank);
        } while (role != RoleMenu.EXIT);

        ScannerWrapper.getInstance().close();
    }

    public static void handleTheMainMenu(RoleMenu roles, Bank myBank){
        switch (roles) {
            case USER -> UserHandler.implementTheUserMenu(myBank);
            case SUPPORT -> handleSupportRoles();
            case EXIT -> System.out.println("Exiting the program ...");
            default -> System.out.println("Invalid Input!");
        }
    }

    public static void handleSupportRoles(){

    }
}
