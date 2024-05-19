package ir.ac.kntu;

public class Menus {
    private static Menus instance = new Menus();

    private Menus() {
    }

    public static Menus getInstance() {
        return instance;
    }

    public enum MenuMain {
        USER,
        SUPPORT,
        EXIT,
        UNDEFINED
    }

    public void printTheMainMenu() {
        System.out.println();
        System.out.println("***********************");
        System.out.println("1- User");
        System.out.println("2- Support");
        System.out.println("3- Exit");
        System.out.println("***********************");
        System.out.println();
        System.out.print("Select your roles: ");
    }

    public Menus.MenuMain getOptionMainMenu() {
        MenuMain[] roles = MenuMain.values();
        int input = ScannerWrapper.getInstance().nextInt();
        input--;
        if (input >= 0 && input < roles.length) {
            return roles[input];
        }
        return MenuMain.UNDEFINED;
    }

    public enum UserMenu {
        LOG_IN,
        SIGN_UP,
        RETURN,
        UNDEFINED
    }

    public void printTheFirstUserMenu() {
        System.out.println();
        System.out.println("***********************");
        System.out.println("1- Log_in");
        System.out.println("2- Sign_up");
        System.out.println("3- Return");
        System.out.println("***********************");
        System.out.println();
        System.out.print("Select the option: ");
    }

    public Menus.UserMenu getOptionFirstUserMenu() {
        UserMenu[] options = UserMenu.values();
        int input = ScannerWrapper.getInstance().nextInt();
        input--;
        if (input >= 0 && input < options.length) {
            return options[input];
        }
        return UserMenu.UNDEFINED;
    }

    public enum UserOptions {
        ACCOUNT_MANAGEMENT,
        CONTACTS,
        MONEY_TRANSFER,
        SUPPORT,
        SETTINGS,
        RETURN,
        UNDEFINED
    }

    public void printTheSecondUserMenu() {
        System.out.println();
        System.out.println("***********************");
        System.out.println("1- Account management");
        System.out.println("2- Contacts");
        System.out.println("3- Money transfer");
        System.out.println("4- Support");
        System.out.println("5- Settings");
        System.out.println("6- return");
        System.out.println("***********************");
        System.out.println();
        System.out.print("Select your roles: ");
    }

    public Menus.UserOptions getOptionSecondUserMenu() {
        UserOptions[] roles = UserOptions.values();
        int input = ScannerWrapper.getInstance().nextInt();
        input--;
        if (input >= 0 && input < roles.length) {
            return roles[input];
        }
        return UserOptions.UNDEFINED;
    }

    public enum MenuSupport {
        AUTHENTICATION,
        REQUESTS,
        USERS,
        RETURN,
        UNDEFINED
    }

    public void printTheSupportMenu() {
        System.out.println();
        System.out.println("***********************");
        System.out.println("1- Authentication");
        System.out.println("2- Requests");
        System.out.println("3- Users");
        System.out.println("4- return");
        System.out.println("***********************");
        System.out.println();
        System.out.print("Select the option: ");
    }

    public Menus.MenuSupport getOptionSupportMenu() {
        MenuSupport[] options = MenuSupport.values();
        int input = ScannerWrapper.getInstance().nextInt();
        input--;
        if (input >= 0 && input < options.length) {
            return options[input];
        }
        return MenuSupport.UNDEFINED;
    }

    public enum MenuContact {
        ADD_CONTACTS,
        VIEW_INFORMATION_CONTACT,
        RETURN,
        UNDEFINED
    }

    public void printTheContactMenu() {
        System.out.println();
        System.out.println("***********************");
        System.out.println("1- Add contact");
        System.out.println("2- view information contact");
        System.out.println("3- return");
        System.out.println("***********************");
        System.out.println();
        System.out.print("Select the option: ");
    }

    public Menus.MenuContact getOptionContactMenu() {
        MenuContact[] options = MenuContact.values();
        int input = ScannerWrapper.getInstance().nextInt();
        input--;
        if (input >= 0 && input < options.length) {
            return options[input];
        }
        return MenuContact.UNDEFINED;
    }

    public enum ContactOption {
        EDIT_INFORMATION,
        VIEW_INFORMATION,
        RETURN,
        UNDEFINED
    }

    public void printTheContactOption() {
        System.out.println();
        System.out.println("***********************");
        System.out.println("1- Edit information");
        System.out.println("2- view information");
        System.out.println("3- return");
        System.out.println("***********************");
        System.out.println();
        System.out.print("Select the option: ");
    }

    public Menus.ContactOption getOptionContactOption() {
        ContactOption[] options = ContactOption.values();
        int input = ScannerWrapper.getInstance().nextInt();
        input--;
        if (input >= 0 && input < options.length) {
            return options[input];
        }
        return ContactOption.UNDEFINED;
    }

    public enum SettingOptions {
        CHANGE_PASSWORD,
        REGISTER_CARD_PASSWORD,
        CHANGE_CARD_PASSWORD,
        ACTIVATION_CONTACT_KEYWORD,
        INACTIVATION_CONTACT_KEYWORD,
        RETURN,
        UNDEFINED
    }

    public void printTheSettingOption() {
        System.out.println();
        System.out.println("***********************");
        System.out.println("1- Change password");
        System.out.println("2- Register card password");
        System.out.println("3- change card password");
        System.out.println("4- Activation contact keyword");
        System.out.println("5- Inactivation contact keyword");
        System.out.println("6- return");
        System.out.println("***********************");
        System.out.println();
        System.out.print("Select the option: ");
    }

    public Menus.SettingOptions getOptionSettingOption() {
        SettingOptions[] options = SettingOptions.values();
        int input = ScannerWrapper.getInstance().nextInt();
        input--;
        if (input >= 0 && input < options.length) {
            return options[input];
        }
        return SettingOptions.UNDEFINED;
    }
}
