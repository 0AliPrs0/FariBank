package ir.ac.kntu.user.menus;

import ir.ac.kntu.main.database.Bank;
import ir.ac.kntu.main.enums.MenuProperty;
import ir.ac.kntu.main.enums.RequestType;
import ir.ac.kntu.main.help.Color;
import ir.ac.kntu.main.help.ScannerWrapper;
import ir.ac.kntu.user.implement.ImplementSupportUser;
import ir.ac.kntu.user.info.UserAccount;

public class RegisterSupportUserMenu implements MenuProperty {

    private static RegisterSupportUserMenu instance = new RegisterSupportUserMenu();

    public static RegisterSupportUserMenu getInstance() {
        return instance;
    }

    public enum RegisterSupportUserField {
        REPORT,
        CONTACTS,
        TRANSFER,
        SETTING,
        FUND,
        CHARGE,
        CARD,
        RETURN,
        UNDEFINED
    }

    public void implementRegisterSupportUser(UserAccount myAccount, Bank myBank) {
        RegisterSupportUserField option;
        do {
            printTheMenu();
            option = getOption();
            handleRegisterSupportUser(myBank, myAccount, option);
        } while (option != RegisterSupportUserField.RETURN);
    }

    public void handleRegisterSupportUser(Bank myBank, UserAccount myAccount, RegisterSupportUserField option) {
        ImplementSupportUser supportUser = new ImplementSupportUser();
        switch (option) {
            case REPORT -> supportUser.handleSupportUser(myBank, myAccount, RequestType.REPORTS);
            case CONTACTS -> supportUser.handleSupportUser(myBank, myAccount, RequestType.CONTACTS);
            case TRANSFER -> supportUser.handleSupportUser(myBank, myAccount, RequestType.TRANSFER);
            case SETTING -> supportUser.handleSupportUser(myBank, myAccount, RequestType.SETTINGS);
            case FUND -> supportUser.handleSupportUser(myBank, myAccount, RequestType.FUND);
            case CHARGE -> supportUser.handleSupportUser(myBank, myAccount, RequestType.CHARGE);
            case CARD -> supportUser.handleSupportUser(myBank, myAccount, RequestType.CARD);
            case RETURN -> System.out.println();
            default -> System.out.println(Color.RED + "Invalid Input!");
        }
    }

    @Override
    public void printTheMenu() {
        System.out.println();
        System.out.println(Color.YELLOW + "***********************");
        System.out.println(Color.BLUE + "1- Report");
        System.out.println(Color.BLUE + "2- Contacts");
        System.out.println(Color.BLUE + "3- Transfer");
        System.out.println(Color.BLUE + "4- Setting");
        System.out.println(Color.BLUE + "5- Funds");
        System.out.println(Color.BLUE + "6- Charge");
        System.out.println(Color.BLUE + "7- Card");
        System.out.println(Color.BLUE + "8- Return");
        System.out.println(Color.YELLOW + "***********************");
        System.out.print(Color.PURPLE + "Select (1 - 8): ");
    }

    @Override
    public RegisterSupportUserField getOption() {
        RegisterSupportUserField[] options = RegisterSupportUserField.values();
        String inputStr = ScannerWrapper.getInstance().nextLine();
        int input;
        try {
            input = Integer.parseInt(inputStr);
        } catch (Exception e) {
            return RegisterSupportUserField.UNDEFINED;
        }
        input--;
        if (input >= 0 && input < options.length) {
            return options[input];
        }
        return RegisterSupportUserField.UNDEFINED;
    }
}
