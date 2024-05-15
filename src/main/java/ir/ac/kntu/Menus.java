package ir.ac.kntu;

import java.util.Scanner;

public class Menus {
    private static Menus instance = new Menus();

    private Menus() {
    }

    public static Menus getInstance() {
        return instance;
    }

    public void printTheMainMenu() {
        System.out.println();
        System.out.println("***********************");
        System.out.println("1- User");
        System.out.println("2- Support");
        System.out.println("3- Exit");
        System.out.println("***********************");
        System.out.println();
        System.out.print("Select your roles: ");
    }
    public Role getOptionMainMenu() {
        Role[] roles = Role.values();
        int input = ScannerWrapper.getInstance().nextInt();
        if (input >= 0 && input < roles.length) {
            return roles[input];
        }
        return Role.UNDEFINED;
    }

    public void printTheUserMenu() {
        System.out.println();
        System.out.println("***********************");
        System.out.println("1- Log_in");
        System.out.println("2- Sign_up");
        System.out.println("3- Return");
        System.out.println("***********************");
        System.out.println();
        System.out.print("Select the option: ");
    }

    public UserMenu getOptionUserMenu() {
        UserMenu[] options = UserMenu.values();
        int input = ScannerWrapper.getInstance().nextInt();
        input--;
        if (input >= 0 && input < options.length) {
            return options[input];
        }
        return UserMenu.UNDEFINED;
    }

}
