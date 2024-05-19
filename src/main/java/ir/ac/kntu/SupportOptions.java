package ir.ac.kntu;

public class SupportOptions {
    public static void handleSupportOptions(Bank myBank, Support support) {
        Menus.MenuSupport option;

        do {
            Menus.getInstance().printTheSupportMenu();
            option = Menus.getInstance().getOptionSupportMenu();
            handleTheUserMenu(option, myBank, support);
        } while (option != Menus.MenuSupport.RETURN);

    }

    public static void handleTheUserMenu(Menus.MenuSupport options, Bank myBank, Support support) {
        switch (options) {
//            case ACCOUNT_MANAGEMENT -> ;
//            case CONTACTS -> ;
//            case MONEY_TRANSFER -> ;
//            case SUPPORT -> ;
//            case SETTINGS -> ;
//            case RETURN -> ;
            default -> System.out.println("Invalid Input!");
        }
    }
}
