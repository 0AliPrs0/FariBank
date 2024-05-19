package ir.ac.kntu;

public class UserOptions {

    public static void handleUserOptions(Bank myBank, UserAccount me) {
        Menus.UserOptions option;

        do {
            Menus.getInstance().printTheSecondUserMenu();
            option = Menus.getInstance().getOptionSecondUserMenu();
            handleTheUserMenu(option, myBank, me);
        } while (option != Menus.UserOptions.RETURN);

    }

    public static void handleTheUserMenu(Menus.UserOptions options, Bank myBank, UserAccount me) {
        switch (options) {
//            case ACCOUNT_MANAGEMENT -> ;
            case CONTACTS -> contacts();
//            case MONEY_TRANSFER -> ;
//            case SUPPORT -> ;
//            case SETTINGS -> ;
//            case RETURN -> ;
            default -> System.out.println("Invalid Input!");
        }
    }
}
