package ir.ac.kntu;

public class FirstUserMenu implements MenuProperty{
    public enum UserMenu {
        LOG_IN,
        SIGN_UP,
        RETURN,
        UNDEFINED
    }

    private static FirstUserMenu instance = new FirstUserMenu();

    private FirstUserMenu() {
    }

    public static FirstUserMenu getInstance() {
        return instance;
    }


    @Override
    public void printTheMenu() {
        System.out.println();
        System.out.println("***********************");
        System.out.println("1- Log_in");
        System.out.println("2- Sign_up");
        System.out.println("3- Return");
        System.out.println("***********************");
        System.out.println();
        System.out.print("Select the option: ");
    }

    @Override
    public UserMenu getOption() {
        UserMenu[] options = UserMenu.values();
        int input = ScannerWrapper.getInstance().nextInt();
        input--;
        if (input >= 0 && input < options.length) {
            return options[input];
        }
        return UserMenu.UNDEFINED;
    }

}
