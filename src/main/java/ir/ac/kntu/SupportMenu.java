package ir.ac.kntu;

public class SupportMenu implements MenuProperty {
    private static SupportMenu instance = new SupportMenu();

    public SupportMenu() {
    }

    public static SupportMenu getInstance() {
        return instance;
    }

    public enum MenuSupportField {
        AUTHENTICATION,
        REQUESTS,
        USERS,
        RETURN,
        UNDEFINED
    }

    public void handleSupportOptions(Bank myBank) {
        MenuSupportField option;

        do {
            printTheMenu();
            option = getOption();
            handleTheSupportMenu(option, myBank);
        } while (option != MenuSupportField.RETURN);

    }

    public void handleTheSupportMenu(MenuSupportField options, Bank myBank) {
        SupportOptions supportOptions = new SupportOptions();
        switch (options) {
            case AUTHENTICATION -> supportOptions.authentication(myBank);
            case REQUESTS -> supportOptions.request(myBank);
            case USERS -> supportOptions.usersInformation(myBank);
            case RETURN -> System.out.println();
            default -> System.out.println("Invalid Input!");
        }
    }

    @Override
    public void printTheMenu() {
        System.out.println();
        System.out.println("***********************");
        System.out.println("1- Authentication");
        System.out.println("2- Requests");
        System.out.println("3- Users");
        System.out.println("4- Return");
        System.out.println("***********************");
        System.out.println();
        System.out.print("Select the option: ");
    }

    @Override
    public MenuSupportField getOption() {
        MenuSupportField[] options = MenuSupportField.values();
        String inputStr = ScannerWrapper.getInstance().next();
        int input;
        try {
            input = Integer.parseInt(inputStr);
        } catch (Exception e) {
            return MenuSupportField.RETURN;
        }
        input--;
        if (input >= 0 && input < options.length) {
            return options[input];
        }
        return MenuSupportField.UNDEFINED;
    }

}
