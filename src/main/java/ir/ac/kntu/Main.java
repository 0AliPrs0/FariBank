package ir.ac.kntu;

public class Main {

    public static void main(String[] args) {
        Bank myBank = new Bank();

        MainMenu.MenuMain role;

        do {
            MainMenu.getInstance().printTheMenu();
            role = MainMenu.getInstance().getOption();
            handleTheMainMenu(role, myBank);
        } while (role != MainMenu.MenuMain.EXIT);

        ScannerWrapper.getInstance().close();
    }

    public static void handleTheMainMenu(MainMenu.MenuMain roles, Bank myBank){
        switch (roles) {
            case USER -> UserHandler.implementTheUserMenu(myBank);
            case SUPPORT -> SupportHandler.implementTheSupportMenu(myBank);
            case EXIT -> System.out.println("Exiting the program ...");
            default -> System.out.println("Invalid Input!");
        }
    }
}
