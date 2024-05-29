package ir.ac.kntu;

public class RegisterSupportUserMenu implements MenuProperty {

    private static RegisterSupportUserMenu instance = new RegisterSupportUserMenu();

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

    public void implementRegisterSupportUser(UserAccount myAccount, Bank myBank) {
        RegisterSupportUserField option;
        do {
            printTheMenu();
            option = getOption();
            handleRegisterSupportUser(myBank, myAccount, option);
        } while (option != RegisterSupportUserField.RETURN);
    }

    public void handleRegisterSupportUser(Bank myBank, UserAccount myAccount, RegisterSupportUserField option) {
        ImplementSupportUser supportUser = new ImplementSupportUser();
        switch (option) {
            case REPORT -> supportUser.handleSupportUser(myBank, myAccount, RequestType.REPORTS);
            case CONTACTS -> supportUser.handleSupportUser(myBank, myAccount, RequestType.CONTACTS);
            case TRANSFER -> supportUser.handleSupportUser(myBank, myAccount, RequestType.TRANSFER);
            case SETTING -> supportUser.handleSupportUser(myBank, myAccount, RequestType.SETTINGS);
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
        System.out.print(Color.PURPLE + "Select (1 - 5): ");
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
