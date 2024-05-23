package ir.ac.kntu;

public class MainMenu implements MenuProperty
{
    private static MainMenu instance = new MainMenu();

    private MainMenu() {
    }

    public static MainMenu getInstance() {
        return instance;
    }

    public enum MenuMainField {
        USER,
        SUPPORT,
        EXIT,
        UNDEFINED
    }

    @Override
    public void implementMenu(Bank myBank){
        MenuMainField role;

        do {
            printTheMenu();
            role = getOption();
            handleTheMenu(role, myBank);
        } while (role != MenuMainField.EXIT);

        ScannerWrapper.getInstance().close();
    }

    @Override
    public void printTheMenu() {
        System.out.println();
        System.out.println("***********************");
        System.out.println("1- User");
        System.out.println("2- Support");
        System.out.println("3- Exit");
        System.out.println("***********************");
        System.out.println();
        System.out.print("Select your roles: ");
    }

    @Override
    public MenuMainField getOption() {
        MenuMainField[] roles = MenuMainField.values();
        String inputStr = ScannerWrapper.getInstance().next();
        int input;
        try{
            input = Integer.parseInt(inputStr);
        }catch (Exception e){
            return MenuMainField.UNDEFINED;
        }
        input--;
        if (input >= 0 && input < roles.length) {
            return roles[input];
        }
        return MenuMainField.UNDEFINED;
    }

    public void handleTheMenu(MenuMainField option, Bank myBank){
    UserHandler userHandler = new UserHandler();
    SupportHandler supportHandler = new SupportHandler();
        switch (option) {
            case USER -> UserHandler.implementTheUserMenu(myBank);
            case SUPPORT -> SupportHandler.implementTheSupportMenu(myBank);
            case EXIT -> System.out.println("Exiting the program ...");
            default -> System.out.println("Invalid Input!");
        }
    }

}
