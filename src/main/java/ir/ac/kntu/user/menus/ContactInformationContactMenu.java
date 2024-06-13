package ir.ac.kntu.user.menus;

import ir.ac.kntu.main.help.Color;
import ir.ac.kntu.user.implement.ImplementContactUser;
import ir.ac.kntu.main.enums.MenuProperty;
import ir.ac.kntu.main.help.ScannerWrapper;
import ir.ac.kntu.user.info.UserAccount;

public class ContactInformationContactMenu implements MenuProperty {
    private static ContactInformationContactMenu instance = new ContactInformationContactMenu();

    public static ContactInformationContactMenu getInstance() {
        return instance;
    }

    public enum ContactOption {
        EDIT_INFORMATION,
        VIEW_INFORMATION,
        RETURN,
        UNDEFINED
    }


    public void implementInformationContact(UserAccount myAccount, int numberOfContact) {
        ContactOption option;
        do {
            printTheMenu();
            option = getOption();
            handleContactsOption(myAccount, numberOfContact, option);
        } while (option != ContactOption.RETURN);
    }

    public void handleContactsOption(UserAccount myAccount, int numberOfContact, ContactOption option) {
        ImplementContactUser userContacts = new ImplementContactUser();
        switch (option) {
            case EDIT_INFORMATION -> userContacts.editContact(myAccount, numberOfContact);
            case VIEW_INFORMATION -> userContacts.viewInformation(myAccount, numberOfContact);
            case RETURN -> System.out.println();
            default -> System.out.println(Color.RED + "Invalid Input!");
        }
    }

    @Override
    public void printTheMenu() {
        System.out.println();
        System.out.println(Color.YELLOW + "***********************");
        System.out.println(Color.BLUE + "1- Edit information");
        System.out.println(Color.BLUE + "2- view information");
        System.out.println(Color.BLUE + "3- Return");
        System.out.println(Color.YELLOW + "***********************");
        System.out.print(Color.PURPLE+ "Select the option: ");
    }

    @Override
    public ContactOption getOption() {
        ContactOption[] options = ContactOption.values();
        String inputStr = ScannerWrapper.getInstance().nextLine();
        int input;
        try {
            input = Integer.parseInt(inputStr);
        } catch (Exception e) {
            return ContactOption.UNDEFINED;
        }
        input--;
        if (input >= 0 && input < options.length) {
            return options[input];
        }
        return ContactOption.UNDEFINED;
    }
}
