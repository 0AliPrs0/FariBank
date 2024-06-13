package ir.ac.kntu.manager.menus;

import ir.ac.kntu.main.database.Bank;
import ir.ac.kntu.main.help.Color;
import ir.ac.kntu.main.enums.MenuProperty;
import ir.ac.kntu.main.help.ScannerWrapper;
import ir.ac.kntu.manager.implement.ImplementAutoTransaction;
import ir.ac.kntu.manager.implement.ImplementMainSettings;
import ir.ac.kntu.manager.info.Manager;

public class ManagerMenu implements MenuProperty {
    private static ManagerMenu instance = new ManagerMenu();

    public static ManagerMenu getInstance() {
        return instance;
    }

    public void implementManagerOption(Bank myBank, Manager manager) {
        ManagerOptionField option;

        do {
            printTheMenu();
            option = getOption();
            handleManage(option, myBank, manager);
        } while (option != ManagerOptionField.RETURN);
    }

    public void handleManage(ManagerOptionField option, Bank myBank, Manager manager) {
        ImplementMainSettings mainSettings = new ImplementMainSettings();
        ImplementAutoTransaction autoTransaction = new ImplementAutoTransaction();
        ManageManagerMenu managerMenu = new ManageManagerMenu();
        switch (option) {
            case MAIN_SETTINGS -> mainSettings.handleMainSettings(myBank);
            case USER_MANAGEMENT -> managerMenu.implementManageOption(myBank, manager);
            case AUTO_TRANSACTION -> autoTransaction.handleAutoTransaction(myBank);
            case RETURN -> System.out.println();
            default -> System.out.println(Color.RED + "Invalid Input!");
        }
    }

    @Override
    public void printTheMenu() {
        System.out.println();
        System.out.println(Color.CYAN + "Manager menu");
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

    public enum ManagerOptionField {
        MAIN_SETTINGS,
        USER_MANAGEMENT,
        AUTO_TRANSACTION,
        RETURN,
        UNDEFINED
    }

}
