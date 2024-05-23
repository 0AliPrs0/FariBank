package ir.ac.kntu;

public class ContactMenu implements MenuProperty{
    private static ContactMenu instance = new ContactMenu();

    public ContactMenu() {
    }

    public static ContactMenu getInstance() {
        return instance;
    }

    public enum MenuContactField {
        ADD_CONTACTS,
        VIEW_INFORMATION_CONTACT,
        RETURN,
        UNDEFINED
    }

    public void implementContacts(UserAccount me) {
        if (!me.getIsActingContactKeyword()) {
            System.out.println("The contact keyword is inactive!");
            return;
        }
        MenuContactField option;
        do {
            printTheMenu();
            option = getOption();
            handleContacts(me, option);
        } while (option != MenuContactField.RETURN);
    }

    public void handleContacts(UserAccount me, MenuContactField options) {
        UserOptions userOptions = new UserOptions();
        switch (options) {
            case ADD_CONTACTS -> userOptions.addContact(me);
            case VIEW_INFORMATION_CONTACT -> userOptions.viewInformationContact(me);
            case RETURN -> System.out.println();
            default -> System.out.println("Invalid Input!");
        }
    }

    @Override
    public void printTheMenu() {
        System.out.println();
        System.out.println("***********************");
        System.out.println("1- Add contact");
        System.out.println("2- view information contact");
        System.out.println("3- Return");
        System.out.println("***********************");
        System.out.println();
        System.out.print("Select the option: ");
    }

    @Override
    public MenuContactField getOption() {
        MenuContactField[] options = MenuContactField.values();
        String inputStr = ScannerWrapper.getInstance().next();
        int input;
        try{
            input = Integer.parseInt(inputStr);
        }catch (Exception e){
            return MenuContactField.UNDEFINED;
        }        input--;
        if (input >= 0 && input < options.length) {
            return options[input];
        }
        return MenuContactField.UNDEFINED;
    }


}
