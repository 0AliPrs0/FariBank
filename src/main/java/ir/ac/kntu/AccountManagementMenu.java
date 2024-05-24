package ir.ac.kntu;

public class AccountManagementMenu implements MenuProperty {

    public enum AccountManagementOption {
        CHARGED_ACCOUNT,
        BALANCE,
        TRANSACTION,
        TIME_FILTER_TRANSACTION,
        RETURN,
        UNDEFINED
    }

    public void implementAccountManagement(UserAccount me) {
        AccountManagementOption option;
        do {
            printTheMenu();
            option = getOption();
            handleAccountManagement(me, option);
        } while (option != AccountManagementOption.RETURN);
    }

    public void handleAccountManagement(UserAccount me, AccountManagementOption option) {
        UserOptions userOptions = new UserOptions();
        switch (option) {
            case CHARGED_ACCOUNT -> userOptions.chargeAccount(me);
            case BALANCE -> userOptions.balance(me);
            case TRANSACTION -> userOptions.transaction(me.getChargeAccounts(), me.getTransfers());
            case TIME_FILTER_TRANSACTION -> userOptions.timeFilterTransaction(me);
            case RETURN -> System.out.println();
            default -> System.out.println(Color.RED + "Invalid Input!");
        }
    }


    @Override
    public void printTheMenu() {
        System.out.println();
        System.out.println(Color.YELLOW + "***********************");
        System.out.println(Color.BLUE + "1- Charge account");
        System.out.println(Color.BLUE + "2- Balance");
        System.out.println(Color.BLUE + "3- Transaction");
        System.out.println(Color.BLUE + "4- Time filter transaction");
        System.out.println(Color.BLUE + "5- Return");
        System.out.println(Color.YELLOW + "***********************");
        System.out.println();
        System.out.print(Color.PURPLE + "Select the option: ");
    }

    @Override
    public AccountManagementOption getOption() {
        AccountManagementOption[] options = AccountManagementOption.values();
        String inputStr = ScannerWrapper.getInstance().nextLine();
        int input;
        try {
            input = Integer.parseInt(inputStr);
        } catch (Exception e) {
            return AccountManagementOption.UNDEFINED;
        }
        input--;
        if (input >= 0 && input < options.length) {
            return options[input];
        }
        return AccountManagementOption.UNDEFINED;
    }
}
