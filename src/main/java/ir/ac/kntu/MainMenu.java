package ir.ac.kntu;

public class MainMenu implements MenuProperty {
    private static MainMenu instance = new MainMenu();

    private MainMenu() {
    }

    public static MainMenu getInstance() {
        return instance;
    }

    public enum MenuMainField {
        USER,
        SUPPORT,
        MANAGE,
        EXIT,
        UNDEFINED
    }

    public void implementMenu(Bank myBank) {
        MenuMainField option;

        do {
            printTheMenu();
            option = getOption();
            handleTheMenu(option, myBank);
        } while (option != MenuMainField.EXIT);

        ScannerWrapper.getInstance().close();
    }

    @Override
    public void printTheMenu() {
        System.out.println();
        System.out.println(Color.CYAN + "Main menu");
        System.out.println(Color.YELLOW + "***********************");
        System.out.println(Color.BLUE + "1- User");
        System.out.println(Color.BLUE + "2- Support");
        System.out.println(Color.BLUE + "3- Manage");
        System.out.println(Color.BLUE + "4- Exit");
        System.out.println(Color.YELLOW + "***********************");
        System.out.print(Color.PURPLE + "Select your options: ");
    }

    @Override
    public MenuMainField getOption() {
        MenuMainField[] options = MenuMainField.values();
        String inputStr = ScannerWrapper.getInstance().nextLine();
        int input;
        try {
            input = Integer.parseInt(inputStr);
        } catch (Exception e) {
            return MenuMainField.UNDEFINED;
        }
        input--;
        if (input >= 0 && input < options.length) {
            return options[input];
        }
        return MenuMainField.UNDEFINED;
    }

    public void handleTheMenu(MenuMainField option, Bank myBank) {
        UserLoginMenu userLoginMenu = new UserLoginMenu();
        switch (option) {
            case USER -> userLoginMenu.implementMenu(myBank);
            case SUPPORT -> SupportHandler.implementTheSupportMenu(myBank);
//            case MANAGE ->
            case EXIT -> System.out.println(Color.RED + "Exiting the program ...");
            default -> System.out.println(Color.RED + "Invalid Input!");
        }
    }

}
