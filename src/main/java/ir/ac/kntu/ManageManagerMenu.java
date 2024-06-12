package ir.ac.kntu;

public class ManageManagerMenu implements MenuProperty {
    private static ManageManagerMenu instance = new ManageManagerMenu();

    public static ManageManagerMenu getInstance() {
        return instance;
    }

    public enum ManageOption {
        SHOW_USERS,
        ADD_USERS,
        BLOCKING_USERS,
        EDIT_USERS,
        RETURN,
        UNDEFINED
    }
    public void implementManageOption(Bank myBank) {
        ManageOption option;

        do {
        printTheMenu();
        option = getOption();
        handleManage(option, myBank);
        } while (option != ManageOption.RETURN);
    }

    public void handleManage(ManageOption option, Bank myBank) {
        ImplementUserManagement userManage = new ImplementUserManagement();
        switch (option) {
//            case SHOW_USERS -> userManage.handleMainSettings(myBank);
            case ADD_USERS -> userManage.handleAddUsers(myBank);
            case BLOCKING_USERS -> userManage.handleBlockingUsers(myBank);
            case EDIT_USERS -> userManage.handleEditUsers(myBank);
            case RETURN -> System.out.println();
            default -> System.out.println(Color.RED + "Invalid Input!");
        }
    }

    @Override
    public void printTheMenu() {
        System.out.println();
        System.out.println(Color.YELLOW + "***********************");
        System.out.println(Color.BLUE + "1- Show users");
        System.out.println(Color.BLUE + "2- add users");
        System.out.println(Color.BLUE + "3- Blocking users");
        System.out.println(Color.BLUE + "4- Edit users");
        System.out.println(Color.BLUE + "5- Return");
        System.out.println(Color.YELLOW + "***********************");
        System.out.print(Color.PURPLE + "Select (1 - 5): ");
    }

    @Override
    public ManageOption getOption() {
        ManageOption[] options = ManageOption.values();
        String inputStr = ScannerWrapper.getInstance().nextLine();
        int input;
        try {
            input = Integer.parseInt(inputStr);
        } catch (Exception e) {
            return ManageOption.UNDEFINED;
        }
        input--;
        if (input >= 0 && input < options.length) {
            return options[input];
        }
        return ManageOption.UNDEFINED;
    }


}
