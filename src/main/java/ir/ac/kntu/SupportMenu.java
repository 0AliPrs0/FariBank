package ir.ac.kntu;

public class SupportMenu implements MenuProperty {
    private static SupportMenu instance = new SupportMenu();

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
        RequestSupportMenu requestSupport = new RequestSupportMenu();
        UserInformationMenu userInformation = new UserInformationMenu();
        switch (options) {
            case AUTHENTICATION -> supportOptions.authentication(myBank);
            case REQUESTS -> requestSupport.implementRequestSupport(myBank);
            case USERS -> userInformation.implementUsersInformation(myBank);
            case RETURN -> System.out.println();
            default -> System.out.println(Color.RED + "Invalid Input!");
        }
    }

    @Override
    public void printTheMenu() {
        System.out.println();
        System.out.println(Color.YELLOW + "***********************");
        System.out.println(Color.BLUE + "1- Authentication");
        System.out.println(Color.BLUE + "2- Requests");
        System.out.println(Color.BLUE + "3- Users");
        System.out.println(Color.BLUE + "4- Return");
        System.out.println(Color.YELLOW + "***********************");
        System.out.println();
        System.out.print(Color.PURPLE + "Select the option: ");
    }

    @Override
    public MenuSupportField getOption() {
        MenuSupportField[] options = MenuSupportField.values();
        String inputStr = ScannerWrapper.getInstance().nextLine();
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
