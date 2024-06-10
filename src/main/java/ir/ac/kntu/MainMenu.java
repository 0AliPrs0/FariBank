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
        MANAGER,
        EXIT,
        UNDEFINED
    }

    public void implementMenu(Bank myBank) {
        addFewMockAccount(myBank);
        myBank.setInterests(new InterestRatesAndFees("2%", "300", "2%", "2000", "0"));
        myBank.setDepositPeriod(2);

        MenuMainField option;
        do {
            printTheMenu();
            option = getOption();
            handleTheMenu(option, myBank);
        } while (option != MenuMainField.EXIT);

        ScannerWrapper.getInstance().close();
    }

    public void addFewMockAccount(Bank myBank){
        MockAccount mockAccount1 = new MockAccount("nima", "09032969853", 1234567);
        myBank.addMockAccounts(mockAccount1);

        MockAccount mockAccount2 = new MockAccount("mahdi", "09147458951", 7654321);
        myBank.addMockAccounts(mockAccount2);
    }

    @Override
    public void printTheMenu() {
        System.out.println();
        System.out.println(Color.CYAN + "Main menu");
        System.out.println(Color.YELLOW + "***********************");
        System.out.println(Color.BLUE + "1- User");
        System.out.println(Color.BLUE + "2- Support");
        System.out.println(Color.BLUE + "3- Manager");
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
        SupportHandler supportHandler = new SupportHandler();
        ManagerHandler managerHandler = new ManagerHandler();
        switch (option) {
            case USER -> userLoginMenu.implementMenu(myBank);
            case SUPPORT -> supportHandler.implementTheSupportMenu(myBank);
            case MANAGER -> managerHandler.implementTheManagerMenu(myBank);
            case EXIT -> System.out.println(Color.RED + "Exiting the program ...");
            default -> System.out.println(Color.RED + "Invalid Input!");
        }
    }

}
