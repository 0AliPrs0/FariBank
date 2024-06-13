package ir.ac.kntu.user.menus;

import ir.ac.kntu.main.database.Bank;
import ir.ac.kntu.main.enums.MenuProperty;
import ir.ac.kntu.main.help.Color;
import ir.ac.kntu.main.help.ScannerWrapper;
import ir.ac.kntu.user.implement.ImplementSupportUser;
import ir.ac.kntu.user.info.UserAccount;

public class SupportUserMenu implements MenuProperty {
    private static SupportUserMenu instance = new SupportUserMenu();

    public static SupportUserMenu getInstance() {
        return instance;
    }


    public enum SupportUserField {
        REGISTER_MASSAGE,
        SHOW_MASSAGE,
        RETURN,
        UNDEFINED
    }


    public void implementSupportUser(UserAccount myAccount, Bank myBank) {
        SupportUserField option;
        do {
            printTheMenu();
            option = getOption();
            handleSupportUser(myBank, myAccount, option);
        } while (option != SupportUserField.RETURN);
    }

    public void handleSupportUser(Bank myBank, UserAccount myAccount, SupportUserField option) {
        ImplementSupportUser supportUser = new ImplementSupportUser();
        RegisterSupportUserMenu registerSupport = new RegisterSupportUserMenu();
        switch (option) {
            case REGISTER_MASSAGE -> registerSupport.implementRegisterSupportUser(myAccount, myBank);
            case SHOW_MASSAGE -> supportUser.showSupportUser(myAccount, myBank);
            case RETURN -> System.out.println();
            default -> System.out.println(Color.RED + "Invalid Input!");
        }
    }

    @Override
    public void printTheMenu() {
        System.out.println();
        System.out.println(Color.YELLOW + "***********************");
        System.out.println(Color.BLUE + "1- Register massage");
        System.out.println(Color.BLUE + "2- Show massage");
        System.out.println(Color.BLUE + "3- Return");
        System.out.println(Color.YELLOW + "***********************");
        System.out.println();
        System.out.print(Color.PURPLE + "Select (1 - 3): ");
    }

    @Override
    public SupportUserField getOption() {
        SupportUserField[] options = SupportUserField.values();
        String inputStr = ScannerWrapper.getInstance().nextLine();
        int input;
        try {
            input = Integer.parseInt(inputStr);
        } catch (Exception e) {
            return SupportUserField.UNDEFINED;
        }
        input--;
        if (input >= 0 && input < options.length) {
            return options[input];
        }
        return SupportUserField.UNDEFINED;
    }
}
