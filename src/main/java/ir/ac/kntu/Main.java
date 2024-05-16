package ir.ac.kntu;

public class Main {

    public static void main(String[] args) {
        Bank myBank = new Bank();

        MainMenu.RoleMenu role;

        do {
            MainMenu.getInstance().printTheMenu();
            role = MainMenu.getInstance().getOption();
            handleTheMainMenu(role, myBank);
        } while (role != MainMenu.RoleMenu.EXIT);

        ScannerWrapper.getInstance().close();
    }

    public static void handleTheMainMenu(MainMenu.RoleMenu roles, Bank myBank){
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
