package ir.ac.kntu;

public class UserMenu implements MenuProperty {
    private static UserMenu instance = new UserMenu();

    public static UserMenu getInstance() {
        return instance;
    }

    public enum UserOptionsField {
        ACCOUNT_MANAGEMENT,
        CONTACTS,
        MONEY_TRANSFER,
        SUPPORT,
        SETTINGS,
        RETURN,
        UNDEFINED
    }

    public void implementMenu(Bank myBank, UserAccount myAccount) {
        UserOptionsField option;

        do {
            printTheMenu();
            option = getOption();
            handleTheUserMenu(option, myBank, myAccount);
        } while (option != UserOptionsField.RETURN);
    }

    @Override
    public void printTheMenu() {
        System.out.println();
        System.out.println(Color.YELLOW + "***********************");
        System.out.println(Color.BLUE + "1- Account management");
        System.out.println(Color.BLUE + "2- Contacts");
        System.out.println(Color.BLUE + "3- Money transfer");
        System.out.println(Color.BLUE + "4- Support");
        System.out.println(Color.BLUE + "5- Settings");
        System.out.println(Color.BLUE + "6- Return");
        System.out.println(Color.YELLOW + "***********************");
        System.out.println();
        System.out.print(Color.PURPLE + "Select your options: ");
    }

    @Override
    public UserOptionsField getOption() {
        UserOptionsField[] option = UserOptionsField.values();
        String inputStr = ScannerWrapper.getInstance().nextLine();
        int input;
        try {
            input = Integer.parseInt(inputStr);
        } catch (Exception e) {
            return UserOptionsField.UNDEFINED;
        }
        input--;
        if (input >= 0 && input < option.length) {
            return option[input];
        }
        return UserOptionsField.UNDEFINED;
    }

    public static void handleTheUserMenu(UserOptionsField options, Bank myBank, UserAccount myAccount) {
        ContactMenu contactMenu = new ContactMenu();
        AccountManagementMenu accountManagement = new AccountManagementMenu();
        MoneyTransferMenu moneyTransferMenu = new MoneyTransferMenu();
        SettingMenu settingMenu = new SettingMenu();
        SupportUserMenu supportUserMenu = new SupportUserMenu();
        switch (options) {
            case ACCOUNT_MANAGEMENT -> accountManagement.implementAccountManagement(myAccount);
            case CONTACTS -> contactMenu.implementContacts(myAccount);
            case MONEY_TRANSFER -> moneyTransferMenu.implementMoneyTransfer(myAccount, myBank);
            case SUPPORT -> supportUserMenu.implementSupportUser(myAccount, myBank);
            case SETTINGS -> settingMenu.implementSettings(myAccount);
            case RETURN -> System.out.println();
            default -> System.out.println(Color.RED + "Invalid Input!");
        }
    }
}
