package ir.ac.kntu;

public class SupportUserMenu implements MenuProperty {
    private static SupportUserMenu instance = new SupportUserMenu();

    public SupportUserMenu() {
    }

    public static SupportUserMenu getInstance() {
        return instance;
    }


    public enum SupportUserField {
        REGISTER_MASSAGE,
        SHOW_MASSAGE,
        RETURN,
        UNDEFINED
    }


    public void implementSupportUser(UserAccount me, Bank myBank) {
        SupportUserField option;
        do {
            printTheMenu();
            option = getOption();
            handleSupportUser(myBank, me, option);
        } while (option != SupportUserField.RETURN);
    }

    public void handleSupportUser(Bank myBank, UserAccount me, SupportUserField option) {
        UserOptions userOptions = new UserOptions();
        RegisterSupportUserMenu registerSupportUserMenu = new RegisterSupportUserMenu();
        switch (option) {
            case SHOW_MASSAGE -> userOptions.showSupportUser(me, myBank);
            case REGISTER_MASSAGE -> registerSupportUserMenu.implementRegisterSupportUser(me, myBank);
            case RETURN -> System.out.println();
            default -> System.out.println("Invalid Input!");
        }
    }

    @Override
    public void printTheMenu() {
        System.out.println();
        System.out.println("***********************");
        System.out.println("1- Register massage");
        System.out.println("2- Show massage");
        System.out.println("3- Return");
        System.out.println("***********************");
        System.out.println();
        System.out.print("Select the option: ");
    }

    @Override
    public SupportUserField getOption() {
        SupportUserField[] options = SupportUserField.values();
        String inputStr = ScannerWrapper.getInstance().next();
        int input;
        try {
            input = Integer.parseInt(inputStr);
        } catch (Exception e) {
            return SupportUserField.UNDEFINED;
        }
        input--;
        if (input >= 0 && input < options.length) {
            return options[input];
        }
        return SupportUserField.UNDEFINED;
    }
}
