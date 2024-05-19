package ir.ac.kntu;

public class Main {

    public static void main(String[] args) {
        Bank myBank = new Bank();

        Menus.MenuMain role;

        do {
            Menus.getInstance().printTheMainMenu();
            role = Menus.getInstance().getOptionMainMenu();
            handleTheMainMenu(role, myBank);
        } while (role != Menus.MenuMain.EXIT);

        ScannerWrapper.getInstance().close();
    }

    public static void handleTheMainMenu(Menus.MenuMain roles, Bank myBank){
        switch (roles) {
            case USER -> UserHandler.implementTheUserMenu(myBank);
            case SUPPORT -> SupportHandler.implementTheSupportMenu(myBank);
            case EXIT -> System.out.println("Exiting the program ...");
            default -> System.out.println("Invalid Input!");
        }
    }
}
