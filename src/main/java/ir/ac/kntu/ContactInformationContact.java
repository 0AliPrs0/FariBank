package ir.ac.kntu;

public class ContactInformationContact implements MenuProperty {
    private static ContactInformationContact instance = new ContactInformationContact();

    public ContactInformationContact() {
    }

    public static ContactInformationContact getInstance() {
        return instance;
    }

    public enum ContactOption {
        EDIT_INFORMATION,
        VIEW_INFORMATION,
        RETURN,
        UNDEFINED
    }

    public void implementInformationContact(UserAccount me, int numberOfContact) {
        ContactOption option;
        do {
            printTheMenu();
            option = getOption();
            handleContactsOption(me, numberOfContact, option);
        } while (option != ContactOption.RETURN);
    }

    public void handleContactsOption(UserAccount me, int numberOfContact, ContactOption option) {
        UserOptions userOptions = new UserOptions();
        switch (option) {
            case EDIT_INFORMATION -> userOptions.editContact(me, numberOfContact);
            case VIEW_INFORMATION -> userOptions.viewInformation(me, numberOfContact);
            case RETURN -> System.out.println();
            default -> System.out.println("Invalid Input!");
        }
    }

    @Override
    public void printTheMenu() {
        System.out.println();
        System.out.println("***********************");
        System.out.println("1- Edit information");
        System.out.println("2- view information");
        System.out.println("3- Return");
        System.out.println("***********************");
        System.out.println();
        System.out.print("Select the option: ");
    }

    @Override
    public ContactOption getOption() {
        ContactOption[] options = ContactOption.values();
        String inputStr = ScannerWrapper.getInstance().next();
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
