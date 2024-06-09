package ir.ac.kntu;

public class AccountManagementMenu implements MenuProperty {

    public enum AccountManagementOption {
        CHARGED_ACCOUNT,
        BALANCE,
        TRANSACTION,
        TIME_FILTER_TRANSACTION,
        SIM_CARD_VALIDITY,
        RETURN,
        UNDEFINED
    }

    public void implementAccountManagement(UserAccount myAccount) {
        AccountManagementOption option;
        do {
            printTheMenu();
            option = getOption();
            handleAccountManagement(myAccount, option);
        } while (option != AccountManagementOption.RETURN);
    }

    public void handleAccountManagement(UserAccount myAccount, AccountManagementOption option) {
        ImplementAccountManagement manageAccount = new ImplementAccountManagement();
        switch (option) {
            case CHARGED_ACCOUNT -> manageAccount.chargeAccount(myAccount);
            case BALANCE -> manageAccount.balance(myAccount);
            case TRANSACTION -> manageAccount.transaction(myAccount.getChargeAccounts(), myAccount.getTransfers());
            case TIME_FILTER_TRANSACTION -> manageAccount.timeFilterTransaction(myAccount);
            case SIM_CARD_VALIDITY -> manageAccount.showSIMCardValidity(myAccount);
            case RETURN -> System.out.println();
            default -> System.out.println(Color.RED + "Invalid Input!");
        }
    }


    @Override
    public void printTheMenu() {
        System.out.println();
        System.out.println(Color.CYAN + "Account management menu");
        System.out.println(Color.YELLOW + "***********************");
        System.out.println(Color.BLUE + "1- Charge account");
        System.out.println(Color.BLUE + "2- Balance");
        System.out.println(Color.BLUE + "3- Transaction");
        System.out.println(Color.BLUE + "4- Time filter transaction");
        System.out.println(Color.BLUE + "5- SIM card validity");
        System.out.println(Color.BLUE + "6- Return");
        System.out.println(Color.YELLOW + "***********************");
        System.out.print(Color.PURPLE + "Select (1 - 6): ");
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
