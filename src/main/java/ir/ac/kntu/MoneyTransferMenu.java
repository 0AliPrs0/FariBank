package ir.ac.kntu;

public class MoneyTransferMenu implements MenuProperty {
    private static MoneyTransferMenu instance = new MoneyTransferMenu();

    public static MoneyTransferMenu getInstance() {
        return instance;
    }

    public void implementMoneyTransfer(UserAccount myAccount, Bank myBank) {
        ChoseAccountsForTransfer option;
        printTheMenu();
        option = getOption();
        handleTransfer(myAccount, option, myBank);
    }

    public void handleTransfer(UserAccount myAccount, ChoseAccountsForTransfer option, Bank myBank) {
        ImplementTransfer transfer = new ImplementTransfer();
        switch (option) {
            case SELECT_MANUALLY -> transfer.handleSelectManually(myAccount, myBank);
            case SELECT_FROM_RESENT_ACCOUNTS -> transfer.handleSelectFromResentAccount(myAccount, myBank);
            case SELECT_FROM_CONTACTS -> transfer.handleSelectFromContacts(myAccount, myBank);
            default -> System.out.println(Color.RED + "Invalid Input!");
        }
    }

    @Override
    public void printTheMenu() {
        System.out.println();
        System.out.println(Color.YELLOW + "***********************");
        System.out.println(Color.BLUE + "1- Select manually");
        System.out.println(Color.BLUE + "2- Select from recent accounts");
        System.out.println(Color.BLUE + "3- Select from contacts");
        System.out.println(Color.YELLOW + "***********************");
        System.out.print(Color.PURPLE + "Select (1 - 3): ");
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
        SELECT_MANUALLY,
        SELECT_FROM_RESENT_ACCOUNTS,
        SELECT_FROM_CONTACTS,
        UNDEFINED
    }

}
