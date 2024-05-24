package ir.ac.kntu;

public class SettingMenu implements MenuProperty{
    private static SettingMenu instance = new SettingMenu();

    public SettingMenu() {
    }

    public static SettingMenu getInstance() {
        return instance;
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

    public void implementSettings(UserAccount me) {
        SettingOptions option;
        do {
            printTheMenu();
            option = getOption();
            handleSettingsOption(me, option);
        } while (option != SettingOptions.RETURN);
    }

    public void handleSettingsOption(UserAccount me, SettingOptions option) {
        UserOptions userOptions = new UserOptions();
        switch (option) {
            case CHANGE_PASSWORD -> userOptions.handleChangePassword(me);
            case REGISTER_CARD_PASSWORD -> userOptions.handleRegisterCardPassword(me);
            case CHANGE_CARD_PASSWORD -> userOptions.handleChangeCardPassword(me);
            case ACTIVATION_CONTACT_KEYWORD -> userOptions.handleActivationContactKeyword(me);
            case INACTIVATION_CONTACT_KEYWORD -> userOptions.handleInactivationContactKeyword(me);
            case SHOW_ACCOUNT_NUMBER -> System.out.println(me.getAccountNumber());
            case RETURN -> System.out.println();
            default -> System.out.println("Invalid Input!");
        }
    }
    @Override
    public void printTheMenu() {
        System.out.println();
        System.out.println("***********************");
        System.out.println("1- Change password");
        System.out.println("2- Register card password");
        System.out.println("3- change card password");
        System.out.println("4- Activation contact keyword");
        System.out.println("5- Inactivation contact keyword");
        System.out.println("6- Show account number");
        System.out.println("7- Return");
        System.out.println("***********************");
        System.out.println();
        System.out.print("Select the option: ");
    }

    @Override
    public SettingOptions getOption() {
        SettingOptions[] options = SettingOptions.values();
        String inputStr = ScannerWrapper.getInstance().next();
        int input;
        try{
            input = Integer.parseInt(inputStr);
        }catch (Exception e){
            return SettingOptions.UNDEFINED;
        }
        input--;
        if (input >= 0 && input < options.length) {
            return options[input];
        }
        return SettingOptions.UNDEFINED;
    }
}
