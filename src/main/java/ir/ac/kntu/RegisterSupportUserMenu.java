package ir.ac.kntu;

public class RegisterSupportUserMenu implements MenuProperty{

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

    public void registerSupportUser(UserAccount me, Bank myBank) {
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
            case SETTING -> userOptions.handleActivationContactKeyword(me);
            case RETURN -> System.out.println();
            default -> System.out.println("Invalid Input!");
        }
    }

    @Override
    public void printTheMenu() {
        System.out.println();
        System.out.println("***********************");
        System.out.println("1- Report");
        System.out.println("2- Contacts");
        System.out.println("3- Transfer");
        System.out.println("4- Setting");
        System.out.println("5- Return");
        System.out.println("***********************");
        System.out.println();
        System.out.print("Select the option: ");
    }

    @Override
    public RegisterSupportUserField getOption() {
        RegisterSupportUserField[] options = RegisterSupportUserField.values();
        String inputStr = ScannerWrapper.getInstance().next();
        int input;
        try{
            input = Integer.parseInt(inputStr);
        }catch (Exception e){
            return RegisterSupportUserField.UNDEFINED;
        }
        input--;
        if (input >= 0 && input < options.length) {
            return options[input];
        }
        return RegisterSupportUserField.UNDEFINED;
    }
}
