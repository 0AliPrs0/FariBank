package ir.ac.kntu;

public class UserLoginMenu implements MenuProperty{
    private static UserLoginMenu instance = new UserLoginMenu();

    public UserLoginMenu() {
    }

    public static UserLoginMenu getInstance() {
        return instance;
    }

    public enum UserLoginField {
        LOG_IN,
        SIGN_UP,
        RETURN,
        UNDEFINED
    }

    public void implementMenu(Bank myBank) {
        UserLoginField option;
        do {
            printTheMenu();
            option = getOption();
            handleTheUserMenu(option, myBank);
        } while (option != UserLoginField.RETURN);
    }

    @Override
    public void printTheMenu() {
        System.out.println();
        System.out.println(Color.YELLOW + "***********************");
        System.out.println(Color.BLUE + "1- Log_in");
        System.out.println(Color.BLUE + "2- Sign_up");
        System.out.println(Color.BLUE + "3- Return");
        System.out.println(Color.YELLOW + "***********************");
        System.out.println();
        System.out.print(Color.PURPLE + "Select the option: ");
    }

    public UserLoginField getOption() {
        UserLoginField[] options = UserLoginField.values();
        String inputStr = ScannerWrapper.getInstance().nextLine();
        int input;
        try{
            input = Integer.parseInt(inputStr);
        }catch (Exception e){
            return UserLoginField.UNDEFINED;
        }
        input--;
        if (input >= 0 && input < options.length) {
            return options[input];
        }
        return UserLoginField.UNDEFINED;
    }

    public static void handleTheUserMenu(UserLoginField option, Bank myBank) {
        UserHandler userHandler = new UserHandler();
        switch (option) {
            case LOG_IN -> userHandler.handleLogin(myBank);
            case SIGN_UP -> userHandler.handleSignUp(myBank);
            case RETURN -> System.out.println();
            default -> System.out.println(Color.RED + "Invalid input");
        }
    }

}
