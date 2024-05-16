package ir.ac.kntu;

public class SecondUserMenu implements MenuProperty {

    public enum UserOptions {
        ACCOUNT_MANAGEMENT,
        CONTACTS,
        MONEY_TRANSFER,
        SUPPORT,
        SETTINGS,
        RETURN,
        UNDEFINED
    }

    private static SecondUserMenu instance = new SecondUserMenu();

    private SecondUserMenu() {
    }

    public static SecondUserMenu getInstance() {
        return instance;
    }

    @Override
    public void printTheMenu() {
        System.out.println();
        System.out.println("***********************");
        System.out.println("1- Account management");
        System.out.println("2- Contacts");
        System.out.println("3- Money transfer");
        System.out.println("4- Support");
        System.out.println("5- Settings");
        System.out.println("6- return");
        System.out.println("***********************");
        System.out.println();
        System.out.print("Select your roles: ");
    }

    @Override
    public Object getOption() {
        UserOptions[] roles = UserOptions.values();
        int input = ScannerWrapper.getInstance().nextInt();
        input--;
        if (input >= 0 && input < roles.length) {
            return roles[input];
        }
        return UserOptions.UNDEFINED;
    }
}
//package ir.ac.kntu;
//
//public class MainMenu implements MenuProperty{
//    public enum MenuMain {
//        USER,
//        SUPPORT,
//        EXIT,
//        UNDEFINED
//    }
//
//    private static MainMenu instance = new MainMenu();
//
//    private MainMenu() {
//    }
//
//    public static MainMenu getInstance() {
//        return instance;
//    }
//
//    @Override
//    public void printTheMenu() {
//        System.out.println();
//        System.out.println("***********************");
//        System.out.println("1- User");
//        System.out.println("2- Support");
//        System.out.println("3- Exit");
//        System.out.println("***********************");
//        System.out.println();
//        System.out.print("Select your roles: ");
//    }
//
//    @Override
//    public MenuMain getOption() {
//        MenuMain[] roles = MenuMain.values();
//        int input = ScannerWrapper.getInstance().nextInt();
//        input--;
//        if (input >= 0 && input < roles.length) {
//            return roles[input];
//        }
//        return MenuMain.UNDEFINED;
//    }
//
//}

