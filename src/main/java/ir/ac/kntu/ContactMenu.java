package ir.ac.kntu;

public class ContactMenu implements MenuProperty {
    private static ContactMenu instance = new ContactMenu();

    public static ContactMenu getInstance() {
        return instance;
    }

    public enum MenuContactField {
        ADD_CONTACTS,
        VIEW_INFORMATION_CONTACT,
        RETURN,
        UNDEFINED
    }

    public void implementContacts(UserAccount myAccount) {
        if (!myAccount.getIsActingContactKeyword()) {
            System.out.println("The contact keyword is inactive!");
            return;
        }
        MenuContactField option;
        do {
            printTheMenu();
            option = getOption();
            handleContacts(myAccount, option);
        } while (option != MenuContactField.RETURN);
    }

    public void handleContacts(UserAccount myAccount, MenuContactField options) {
        UserOptions userOptions = new UserOptions();
        switch (options) {
            case ADD_CONTACTS -> userOptions.addContact(myAccount);
            case VIEW_INFORMATION_CONTACT -> userOptions.viewInformationContact(myAccount);
            case RETURN -> System.out.println();
            default -> System.out.println(Color.RED + "Invalid Input!");
        }
    }

    @Override
    public void printTheMenu() {
        System.out.println();
        System.out.println(Color.YELLOW + "***********************");
        System.out.println(Color.BLUE + "1- Add contact");
        System.out.println(Color.BLUE + "2- view information contact");
        System.out.println(Color.BLUE + "3- Return");
        System.out.println(Color.YELLOW + "***********************");
        System.out.println();
        System.out.print(Color.PURPLE + "Select the option: ");
    }

    @Override
    public MenuContactField getOption() {
        MenuContactField[] options = MenuContactField.values();
        String inputStr = ScannerWrapper.getInstance().nextLine();
        int input;
        try {
            input = Integer.parseInt(inputStr);
        } catch (Exception e) {
            return MenuContactField.UNDEFINED;
        }
        input--;
        if (input >= 0 && input < options.length) {
            return options[input];
        }
        return MenuContactField.UNDEFINED;
    }


}
