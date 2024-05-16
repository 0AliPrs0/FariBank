package ir.ac.kntu;

public class SupportMenu implements MenuProperty{
    public enum MenuSupport{
        AUTHENTICATION,
        REQUESTS,
        USERS,
        RETURN,
        UNDEFINED
    }

    private static SupportMenu instance = new SupportMenu();

    private SupportMenu() {
    }

    public static SupportMenu getInstance() {
        return instance;
    }
    @Override
    public void printTheMenu() {
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

    @Override
    public MenuSupport getOption() {
        MenuSupport[] options = MenuSupport.values();
        int input = ScannerWrapper.getInstance().nextInt();
        input--;
        if (input >= 0 && input < options.length) {
            return options[input];
        }
        return MenuSupport.UNDEFINED;
    }
}
