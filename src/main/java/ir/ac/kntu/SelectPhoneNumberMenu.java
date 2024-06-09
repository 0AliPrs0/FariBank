package ir.ac.kntu;

public class SelectPhoneNumberMenu implements MenuProperty {
    private static SelectPhoneNumberMenu instance = new SelectPhoneNumberMenu();

    public static SelectPhoneNumberMenu getInstance() {
        return instance;
    }

    public static void handleTheUserMenu(SelectWay options, Bank myBank, UserAccount myAccount) {
        ImplementSIMCardCharge chargeSIM = new ImplementSIMCardCharge();
        switch (options) {
            case SELECT_MANUALLY -> chargeSIM.handleChargeManually(myBank, myAccount);
            case SELECT_FROM_CONTACTS -> chargeSIM.handleChargeContacts(myBank, myAccount);
            case CHARGE_YOUR_SIM_CARD -> chargeSIM.handleChargeYourAccount(myBank, myAccount);
            case RETURN -> System.out.println();
            default -> System.out.println(Color.RED + "Invalid Input!");
        }
    }

    public void implementSelectWay(Bank myBank, UserAccount myAccount) {
        SelectWay option;

        do {
            printTheMenu();
            option = getOption();
            handleTheUserMenu(option, myBank, myAccount);
        } while (option != SelectWay.RETURN);
    }

    @Override
    public void printTheMenu() {
        System.out.println();
        System.out.println(Color.YELLOW + "***********************");
        System.out.println(Color.BLUE + "1- Select manually");
        System.out.println(Color.BLUE + "2- Select from contacts");
        System.out.println(Color.BLUE + "3- Charge your account");
        System.out.println(Color.BLUE + "4- Return");
        System.out.println(Color.YELLOW + "***********************");
        System.out.println();
        System.out.print(Color.PURPLE + "Select (1 - 4): ");
    }

    @Override
    public SelectWay getOption() {
        SelectWay[] option = SelectWay.values();
        String inputStr = ScannerWrapper.getInstance().nextLine();
        int input;
        try {
            input = Integer.parseInt(inputStr);
        } catch (Exception e) {
            return SelectWay.UNDEFINED;
        }
        input--;
        if (input >= 0 && input < option.length) {
            return option[input];
        }
        return SelectWay.UNDEFINED;
    }

    public enum SelectWay {
        SELECT_MANUALLY,
        SELECT_FROM_CONTACTS,
        CHARGE_YOUR_SIM_CARD,
        RETURN,
        UNDEFINED
    }


}
