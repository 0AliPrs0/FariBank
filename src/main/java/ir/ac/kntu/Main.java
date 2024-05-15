package ir.ac.kntu;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ArrayList<UserAccount> user = new ArrayList<>();
        ArrayList<Authentication> authentications = new ArrayList<>();
        RoleMenu role;

        do {
            Menus.getInstance().printTheMainMenu();
            role = Menus.getInstance().getOptionMainMenu();
            handleTheMainMenu(role, user, authentications);
        } while (role != RoleMenu.EXIT);

        ScannerWrapper.getInstance().close();
    }

    public static void handleTheMainMenu(RoleMenu roles, UserMenu user, Authentication authentication){
        switch (roles) {
            case USER -> handleUserRoles(user, authentication);
            case SUPPORT -> handleSupportRoles();
            case EXIT -> System.out.println("Exiting the program ...");
            default -> System.out.println("Invalid Input!");
        }
    }

    public static void handleUserRoles(){
        UserHandler.implementTheUserMenu();
    }

    public static void handleSupportRoles(){

    }
}
