package ir.ac.kntu;

public class SettingMenu implements MenuProperty {
    private static SettingMenu instance = new SettingMenu();

    public static SettingMenu getInstance() {
        return instance;
    }

    public void implementSettings(UserAccount myAccount) {
        SettingOptions option;
        do {
            printTheMenu();
            option = getOption();
            handleSettingsOption(myAccount, option);
        } while (option != SettingOptions.RETURN);
    }

    public void handleSettingsOption(UserAccount myAccount, SettingOptions option) {
        UserOptions userOptions = new UserOptions();
        switch (option) {
            case CHANGE_PASSWORD -> userOptions.handleChangePassword(myAccount);
            case REGISTER_CARD_PASSWORD -> userOptions.handleRegisterCardPassword(myAccount);
            case CHANGE_CARD_PASSWORD -> userOptions.handleChangeCardPassword(myAccount);
            case ACTIVATION_CONTACT_KEYWORD -> userOptions.handleActivationContactKeyword(myAccount);
            case INACTIVATION_CONTACT_KEYWORD -> userOptions.handleInactivationContactKeyword(myAccount);
            case SHOW_ACCOUNT_NUMBER -> System.out.println(myAccount.getAccountNumber());
            case RETURN -> System.out.println();
            default -> System.out.println(Color.RED + "Invalid Input!");
        }
    }

    @Override
    public void printTheMenu() {
        System.out.println();
        System.out.println(Color.YELLOW + "***********************");
        System.out.println(Color.BLUE + "1- Change password");
        System.out.println(Color.BLUE + "2- Register card password");
        System.out.println(Color.BLUE + "3- change card password");
        System.out.println(Color.BLUE + "4- Activation contact keyword");
        System.out.println(Color.BLUE + "5- Inactivation contact keyword");
        System.out.println(Color.BLUE + "6- Show account number");
        System.out.println(Color.BLUE + "7- Return");
        System.out.println(Color.YELLOW + "***********************");
        System.out.println();
        System.out.print(Color.PURPLE + "Select the option: ");
    }

    @Override
    public SettingOptions getOption() {
        SettingOptions[] options = SettingOptions.values();
        String inputStr = ScannerWrapper.getInstance().nextLine();
        int input;
        try {
            input = Integer.parseInt(inputStr);
        } catch (Exception e) {
            return SettingOptions.UNDEFINED;
        }
        input--;
        if (input >= 0 && input < options.length) {
            return options[input];
        }
        return SettingOptions.UNDEFINED;
    }

    public enum SettingOptions {
        CHANGE_PASSWORD,
        REGISTER_CARD_PASSWORD,
        CHANGE_CARD_PASSWORD,
        ACTIVATION_CONTACT_KEYWORD,
        INACTIVATION_CONTACT_KEYWORD,
        SHOW_ACCOUNT_NUMBER,
        RETURN,
        UNDEFINED
    }
}
