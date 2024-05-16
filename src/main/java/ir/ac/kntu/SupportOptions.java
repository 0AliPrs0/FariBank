package ir.ac.kntu;

public class SupportOptions {
    public static void handleSupportOptions(Bank myBank, Support support) {
        SupportMenu.MenuSupport option;

        do {
            SupportMenu.getInstance().printTheMenu();
            option = SupportMenu.getInstance().getOption();
            handleTheUserMenu(option, myBank, support);
        } while (option != SupportMenu.MenuSupport.RETURN);

    }

    public static void handleTheUserMenu(SupportMenu.MenuSupport options, Bank myBank, Support support) {
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
