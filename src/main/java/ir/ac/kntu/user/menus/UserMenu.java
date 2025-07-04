package ir.ac.kntu.user.menus;

import ir.ac.kntu.main.database.Bank;
import ir.ac.kntu.main.enums.MenuProperty;
import ir.ac.kntu.main.help.Color;
import ir.ac.kntu.main.help.ScannerWrapper;
import ir.ac.kntu.user.info.UserAccount;

public class UserMenu implements MenuProperty {
    private static final UserMenu instance = new UserMenu();

    public static UserMenu getInstance() {
        return instance;
    }

    public enum UserOptionsField {
        ACCOUNT_MANAGEMENT,
        CONTACTS,
        MONEY_TRANSFER,
        SUPPORT,
        SETTINGS,
        CAPITAL_FUND,
        SIM_CARD_CHARGE,
        RETURN,
        UNDEFINED
    }

    public void implementMenu(Bank myBank, UserAccount myAccount) {
        UserOptionsField option;

        do {
            System.out.println();
            System.out.println(Color.PURPLE + "Hello " + myAccount.getFirstName() + " " + myAccount.getLastName());
            printTheMenu();
            option = getOption();
            handleTheUserMenu(option, myBank, myAccount);
        } while (option != UserOptionsField.RETURN);
    }

    @Override
    public void printTheMenu() {
        System.out.println();
        System.out.println(Color.CYAN + "User menu");
        System.out.println(Color.YELLOW + "***********************");
        System.out.println(Color.BLUE + "1- Account management");
        System.out.println(Color.BLUE + "2- Contacts");
        System.out.println(Color.BLUE + "3- Money transfer");
        System.out.println(Color.BLUE + "4- Support");
        System.out.println(Color.BLUE + "5- Settings");
        System.out.println(Color.BLUE + "6- Capital fund");
        System.out.println(Color.BLUE + "7- SIM card charge");
        System.out.println(Color.BLUE + "8- Return");
        System.out.println(Color.YELLOW + "***********************");
        System.out.println();
        System.out.print(Color.PURPLE + "Select (1 - 8): ");
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
        SelectPhoneNumberMenu select = new SelectPhoneNumberMenu();
        CapitalFundMenu capitalFund = new CapitalFundMenu();
        switch (options) {
            case ACCOUNT_MANAGEMENT -> accountManagement.implementAccountManagement(myAccount);
            case CONTACTS -> contactMenu.implementContacts(myAccount);
            case MONEY_TRANSFER -> moneyTransferMenu.implementMoneyTransfer(myAccount, myBank);
            case SUPPORT -> supportUserMenu.implementSupportUser(myAccount, myBank);
            case SETTINGS -> settingMenu.implementSettings(myAccount);
            case CAPITAL_FUND -> capitalFund.implementCapitalFund(myAccount, myBank);
            case SIM_CARD_CHARGE -> select.implementSelectWay(myBank, myAccount);
            case RETURN -> System.out.println();
            default -> System.out.println(Color.RED + "Invalid Input!");
        }
    }
}
