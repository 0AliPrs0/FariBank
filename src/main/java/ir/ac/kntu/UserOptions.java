package ir.ac.kntu;

public class UserOptions {

    public static void handleUserOptions(Bank myBank, UserAccount me) {
        Menus.UserOptions option;

        do {
            Menus.getInstance().printTheSecondUserMenu();
            option = Menus.getInstance().getOptionSecondUserMenu();
            handleTheUserMenu(option, myBank, me);
        } while (option != Menus.UserOptions.RETURN);

    }

    public static void handleTheUserMenu(Menus.UserOptions options, Bank myBank, UserAccount me) {
        switch (options) {
//            case ACCOUNT_MANAGEMENT -> ;
            case CONTACTS -> contacts(me);
//            case MONEY_TRANSFER -> ;
//            case SUPPORT -> ;
            case SETTINGS -> settings(me);
            case RETURN -> System.out.println();
            default -> System.out.println("Invalid Input!");
        }
    }

    public static void contacts(UserAccount me) {
        if (!me.getIsActingContactKeyword()) {
            System.out.println("The contact keyword is inactive!");
            return;
        }
        Menus.MenuContact option;
        do {
            Menus.getInstance().printTheContactMenu();
            option = Menus.getInstance().getOptionContactMenu();
            handleContacts(me, option);
        } while (option != Menus.MenuContact.RETURN);
    }

    public static void handleContacts(UserAccount me, Menus.MenuContact options) {
        switch (options) {
            case ADD_CONTACTS -> addContact(me);
            case VIEW_INFORMATION_CONTACT -> viewInformationContact(me);
            case RETURN -> System.out.println();
            default -> System.out.println("Invalid Input!");
        }
    }

    public static void addContact(UserAccount me) {
        System.out.println("Enter the first name of contact: ");
        String fName = ScannerWrapper.getInstance().next();

        System.out.println("Enter the first name of contact: ");
        String lName = ScannerWrapper.getInstance().next();

        System.out.println("Enter the first name of contact: ");
        String phoneNumber = ScannerWrapper.getInstance().next();

        boolean isTherePhoneNumber = checkPhoneNumber(me, phoneNumber);
        if (!isTherePhoneNumber) {
            Contact newContact = new Contact(fName, lName, phoneNumber);
            me.getMyContacts().add(newContact);
        } else {
            System.out.println("There is same phone number in your contacts");
        }
    }

    public static boolean checkPhoneNumber(UserAccount me, String phoneNumber) {
        for (Contact entry : me.getMyContacts()) {
            if (entry.getPhoneNumber().equals(phoneNumber)) {
                return true;
            }
        }
        return false;
    }

    public static void viewInformationContact(UserAccount me) {
        for (int i = 1; i <= me.getMyContacts().size(); i++) {
            Contact contact = me.getMyContacts().get(i - 1);
            System.out.println(i + "- " + contact.getFirstName() + " " + contact.getLName());
        }
        System.out.println();
        System.out.println("Chose the contact ( Enter the number of contact ) : ");
        int numberOfContact = ScannerWrapper.getInstance().nextInt();
        numberOfContact--;

        handleInformationContact(me, numberOfContact);
    }

    public static void handleInformationContact(UserAccount me, int numberOfContact) {
        Menus.ContactOption option;
        do {
            Menus.getInstance().printTheContactOption();
            option = Menus.getInstance().getOptionContactOption();
            handleContactsOption(me, numberOfContact, option);
        } while (option != Menus.ContactOption.RETURN);
    }

    public static void handleContactsOption(UserAccount me, int numberOfContact, Menus.ContactOption option) {
        switch (option) {
            case EDIT_INFORMATION -> editContact(me, numberOfContact);
            case VIEW_INFORMATION -> viewInformation(me, numberOfContact);
            case RETURN -> System.out.println();
            default -> System.out.println("Invalid Input!");
        }
    }

    public static void editContact(UserAccount me, int numberOfContact) {
        System.out.println("Enter the first name of contact: ");
        String fName = ScannerWrapper.getInstance().next();

        System.out.println("Enter the last name of contact: ");
        String lName = ScannerWrapper.getInstance().next();

        String phoneNumber = me.getMyContacts().get(numberOfContact).getPhoneNumber();

        Contact editContact = new Contact(fName, lName, phoneNumber);

        me.getMyContacts().set(numberOfContact, editContact);
        System.out.println("The information of contact was changed");
    }

    public static void viewInformation(UserAccount me, int numberOfContact) {
        System.out.println(me.getMyContacts().get(numberOfContact).toString());
    }

    public static void settings(UserAccount me) {
        Menus.SettingOptions option;
        do {
            Menus.getInstance().printTheSettingOption();
            option = Menus.getInstance().getOptionSettingOption();
            handleSettingsOption(me, option);
        } while (option != Menus.SettingOptions.RETURN);
    }

    public static void handleSettingsOption(UserAccount me, Menus.SettingOptions option) {
        switch (option) {
            case CHANGE_PASSWORD -> handleChangePassword(me);
            case REGISTER_CARD_PASSWORD -> handleRegisterCardPassword(me);
            case CHANGE_CARD_PASSWORD -> handleChangeCardPassword(me);
            case ACTIVATION_CONTACT_KEYWORD -> handleActivationContactKeyword(me);
            case INACTIVATION_CONTACT_KEYWORD -> handleInactivationContactKeyword(me);
            case RETURN -> System.out.println();
            default -> System.out.println("Invalid Input!");
        }
    }

    public static void handleChangePassword(UserAccount me){
        System.out.println("Enter new password: ");
        String password = ScannerWrapper.getInstance().next();

        boolean isSafePassword = UserHandler.checkPassword(password);
        if (isSafePassword) {
            me.setPassword(password);
        }
    }

    public static void handleRegisterCardPassword(UserAccount me){
        if (me.getCardPassword() == -1) {
            System.out.println("Enter your card password: ");
            int cardPassword = ScannerWrapper.getInstance().nextInt();
            me.setCardPassword(cardPassword);
        } else {
            System.out.println("Your card password is already registered!");
        }
    }

    public static void handleChangeCardPassword(UserAccount me){
        if (me.getCardPassword() != -1) {
            System.out.println("Enter new card password: ");
            int cardPassword = ScannerWrapper.getInstance().nextInt();
            me.setCardPassword(cardPassword);
        } else {
            System.out.println("Your card password is not registered!");
        }
    }

    public static void handleActivationContactKeyword(UserAccount me){
        if (!me.getIsActingContactKeyword()) {
            me.setIsActingContactKeyword(true);
            System.out.println("Contact keyword is active");
        } else {
            System.out.println("Contact keyword already is active!");
        }
    }

    public static void handleInactivationContactKeyword(UserAccount me){
        if (me.getIsActingContactKeyword()) {
            me.setIsActingContactKeyword(false);
            System.out.println("Contact keyword is not inactive");
        } else {
            System.out.println("Contact keyword already is not active!");
        }
    }


}
