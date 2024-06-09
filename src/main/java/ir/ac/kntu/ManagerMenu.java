package ir.ac.kntu;

public class ManagerMenu implements MenuProperty{
    private static ManagerMenu instance = new ManagerMenu();

    public static ManagerMenu getInstance() {
        return instance;
    }

    public enum ManagerOptionField {
        MAIN_SETTINGS,
        USER_MANAGEMENT,
        AUTO_TRANSACTION,
        RETURN,
        UNDEFINED
    }

    public void implementManagerOption(Bank myBank) {
        ManagerOptionField option;
        printTheMenu();
        option = getOption();
        handleManage(option, myBank);
    }

    public void handleManage(ManagerOptionField option, Bank myBank) {
        ImplementMainSettings mainSettings = new ImplementMainSettings();
        switch (option) {
            case MAIN_SETTINGS -> mainSettings.handleMainSettings(myBank);
//            case USER_MANAGEMENT -> transfer.handleSelectFromResentAccount(myBank);
//            case AUTO_TRANSACTION -> transfer.handleSelectFromContacts(myBank);
            case RETURN -> System.out.println();
            default -> System.out.println(Color.RED + "Invalid Input!");
        }
    }

    @Override
    public void printTheMenu() {
        System.out.println();
        System.out.println(Color.YELLOW + "***********************");
        System.out.println(Color.BLUE + "1- Main settings");
        System.out.println(Color.BLUE + "2- User management");
        System.out.println(Color.BLUE + "3- Auto transaction");
        System.out.println(Color.BLUE + "4- Return");
        System.out.println(Color.YELLOW + "***********************");
        System.out.print(Color.PURPLE + "Select (1 - 4): ");
    }

    @Override
    public ManagerOptionField getOption() {
        ManagerOptionField[] options = ManagerOptionField.values();
        String inputStr = ScannerWrapper.getInstance().nextLine();
        int input;
        try {
            input = Integer.parseInt(inputStr);
        } catch (Exception e) {
            return ManagerOptionField.UNDEFINED;
        }
        input--;
        if (input >= 0 && input < options.length) {
            return options[input];
        }
        return ManagerOptionField.UNDEFINED;
    }

}
