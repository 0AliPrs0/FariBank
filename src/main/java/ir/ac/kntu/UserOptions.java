package ir.ac.kntu;

public class UserOptions {

    public static void handleUserOptions(Bank myBank, UserAccount user) {
        SecondUserMenu.UserOptions option;

        do {
            SecondUserMenu.getInstance().printTheMenu();
            option = SecondUserMenu.getInstance().getOption();
            handleTheUserMenu(option, myBank, user);
        } while (option != SecondUserMenu.UserOptions.RETURN);

    }

    public static void handleTheUserMenu(SecondUserMenu.UserOptions options, Bank myBank, UserAccount user) {
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
