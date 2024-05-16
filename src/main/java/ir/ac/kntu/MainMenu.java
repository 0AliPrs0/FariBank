package ir.ac.kntu;

public class MainMenu implements MenuProperty{
    public enum RoleMenu {
        USER,
        SUPPORT,
        EXIT,
        UNDEFINED
    }

    private static MainMenu instance = new MainMenu();

    private MainMenu() {
    }

    public static MainMenu getInstance() {
        return instance;
    }

    @Override
    public void printTheMenu() {
        System.out.println();
        System.out.println("***********************");
        System.out.println("1- User");
        System.out.println("2- Support");
        System.out.println("3- Exit");
        System.out.println("***********************");
        System.out.println();
        System.out.print("Select your roles: ");
    }

    @Override
    public RoleMenu getOption() {
        RoleMenu[] roles = RoleMenu.values();
        int input = ScannerWrapper.getInstance().nextInt();
        input--;
        if (input >= 0 && input < roles.length) {
            return roles[input];
        }
        return RoleMenu.UNDEFINED;
    }

}
