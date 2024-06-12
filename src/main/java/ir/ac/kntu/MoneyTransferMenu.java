package ir.ac.kntu;

public class MoneyTransferMenu implements MenuProperty {
    private static MoneyTransferMenu instance = new MoneyTransferMenu();

    public static MoneyTransferMenu getInstance() {
        return instance;
    }

    public void implementMoneyTransfer(UserAccount myAccount, Bank myBank) {
        ChoseAccountsForTransfer option;

        do {
            printTheMenu();
            option = getOption();
            handleTransfer(myAccount, option, myBank);
        } while (option != ChoseAccountsForTransfer.RETURN);
    }

    public void handleTransfer(UserAccount myAccount, ChoseAccountsForTransfer option, Bank myBank) {
        ImplementTransfer transfer = new ImplementTransfer();
        switch (option) {
            case SELECT_MANUALLY_BY_ACCOUNT_NUMBER -> transfer.handleSelectManuallyByAccountNumber(myAccount, myBank);
            case SELECT_MANUALLY_BY_CARD_NUMBER -> transfer.handleSelectManuallyByCardNumber(myAccount, myBank);
            case SELECT_FROM_RESENT_ACCOUNTS -> transfer.handleSelectFromResentAccount(myAccount, myBank);
            case SELECT_FROM_CONTACTS -> transfer.handleSelectFromContacts(myAccount, myBank);
            case RETURN -> System.out.println();
            default -> System.out.println(Color.RED + "Invalid Input!");
        }
    }

    @Override
    public void printTheMenu() {
        System.out.println();
        System.out.println(Color.YELLOW + "***********************");
        System.out.println(Color.BLUE + "1- Select manually by account number");
        System.out.println(Color.BLUE + "2- Select manually by card number");
        System.out.println(Color.BLUE + "3- Select from recent accounts");
        System.out.println(Color.BLUE + "4- Select from contacts");
        System.out.println(Color.YELLOW + "***********************");
        System.out.print(Color.PURPLE + "Select (1 - 4): ");
    }

    @Override
    public ChoseAccountsForTransfer getOption() {
        ChoseAccountsForTransfer[] options = ChoseAccountsForTransfer.values();
        String inputStr = ScannerWrapper.getInstance().nextLine();
        int input;
        try {
            input = Integer.parseInt(inputStr);
        } catch (Exception e) {
            return ChoseAccountsForTransfer.UNDEFINED;
        }
        input--;
        if (input >= 0 && input < options.length) {
            return options[input];
        }
        return ChoseAccountsForTransfer.UNDEFINED;
    }

    public enum ChoseAccountsForTransfer {
        SELECT_MANUALLY_BY_ACCOUNT_NUMBER,
        SELECT_MANUALLY_BY_CARD_NUMBER,
        SELECT_FROM_RESENT_ACCOUNTS,
        SELECT_FROM_CONTACTS,
        RETURN,
        UNDEFINED
    }


}
