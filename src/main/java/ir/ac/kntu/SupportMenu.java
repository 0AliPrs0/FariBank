package ir.ac.kntu;

public class SupportMenu implements MenuProperty {
    private static SupportMenu instance = new SupportMenu();

    public static SupportMenu getInstance() {
        return instance;
    }

    public enum MenuSupportField {
        REQUESTS,
        USERS,
        RETURN,
        UNDEFINED
    }

    public void handleSupportOptions(Bank myBank, Support support) {
        MenuSupportField option;

        do {
            printTheMenu();
            option = getOption();
            handleTheSupportMenu(option, myBank, support);
        } while (option != MenuSupportField.RETURN);

    }

    public void handleTheSupportMenu(MenuSupportField options, Bank myBank, Support support) {
        ImplementRequest requestSupport = new ImplementRequest();
        UserInformationMenu userInformation = new UserInformationMenu();
        switch (options) {
            case REQUESTS -> requestSupport.requestAccordingToRequestType(myBank, support);
            case USERS -> userInformation.implementUsersInformation(myBank, support);
            case RETURN -> System.out.println();
            default -> System.out.println(Color.RED + "Invalid Input!");
        }
    }

    @Override
    public void printTheMenu() {
        System.out.println();
        System.out.println(Color.CYAN + "Support menu");
        System.out.println(Color.YELLOW + "***********************");
        System.out.println(Color.BLUE + "1- Requests");
        System.out.println(Color.BLUE + "2- Users");
        System.out.println(Color.BLUE + "3- Return");
        System.out.println(Color.YELLOW + "***********************");
        System.out.println();
        System.out.print(Color.PURPLE + "Select (1 - 3): ");
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
