package ir.ac.kntu;

public class RegisterSupportUserMenu implements MenuProperty {

    private static RegisterSupportUserMenu instance = new RegisterSupportUserMenu();

    public RegisterSupportUserMenu() {
    }

    public static RegisterSupportUserMenu getInstance() {
        return instance;
    }

    public enum RegisterSupportUserField {
        REPORT,
        CONTACTS,
        TRANSFER,
        SETTING,
        RETURN,
        UNDEFINED
    }

    public void implementRegisterSupportUser(UserAccount me, Bank myBank) {
        RegisterSupportUserField option;
        do {
            printTheMenu();
            option = getOption();
            handleRegisterSupportUser(myBank, me, option);
        } while (option != RegisterSupportUserField.RETURN);
    }

    public void handleRegisterSupportUser(Bank myBank, UserAccount me, RegisterSupportUserField option) {
        UserOptions userOptions = new UserOptions();
        switch (option) {
            case REPORT -> userOptions.handleReportOfSupportUser(myBank, me);
            case CONTACTS -> userOptions.handleContactOfSupportUser(myBank, me);
            case TRANSFER -> userOptions.handleTransferOfSupportUser(myBank, me);
            case SETTING -> userOptions.handleSettingOfSupportUser(myBank, me);
            case RETURN -> System.out.println();
            default -> System.out.println(Color.RED + "Invalid Input!");
        }
    }

    @Override
    public void printTheMenu() {
        System.out.println();
        System.out.println(Color.YELLOW + "***********************");
        System.out.println(Color.BLUE + "1- Report");
        System.out.println(Color.BLUE + "2- Contacts");
        System.out.println(Color.BLUE + "3- Transfer");
        System.out.println(Color.BLUE + "4- Setting");
        System.out.println(Color.BLUE + "5- Return");
        System.out.println(Color.YELLOW + "***********************");
        System.out.println();
        System.out.print(Color.PURPLE + "Select the option: ");
    }

    @Override
    public RegisterSupportUserField getOption() {
        RegisterSupportUserField[] options = RegisterSupportUserField.values();
        String inputStr = ScannerWrapper.getInstance().nextLine();
        int input;
        try {
            input = Integer.parseInt(inputStr);
        } catch (Exception e) {
            return RegisterSupportUserField.UNDEFINED;
        }
        input--;
        if (input >= 0 && input < options.length) {
            return options[input];
        }
        return RegisterSupportUserField.UNDEFINED;
    }
}
