package ir.ac.kntu;

public class CapitalFundMenu implements MenuProperty{
    private static ContactInformationContactMenu instance = new ContactInformationContactMenu();

    public static ContactInformationContactMenu getInstance() {
        return instance;
    }

    public enum CapitalFundOption {
        ADD_FUND,
        REMOVE_FUND,
        FUND_MANAGEMENT,
        RETURN,
        UNDEFINED
    }


    public void implementCapitalFund(UserAccount myAccount, Bank myBank) {
        CapitalFundOption option;
        do {
            printTheMenu();
            option = getOption();
            handleContactsOption(myAccount, myBank, option);
        } while (option != CapitalFundOption.RETURN);
    }

    public void handleContactsOption(UserAccount myAccount, Bank myBank, CapitalFundOption option) {
        ImplementCapitalFund capitalFund = new ImplementCapitalFund();
        switch (option) {
            case ADD_FUND -> capitalFund.addFund(myAccount, myBank);
            case REMOVE_FUND -> capitalFund.removeFund(myAccount, myBank);
            case FUND_MANAGEMENT -> capitalFund.fundManagement(myAccount, myBank);
            case RETURN -> System.out.println();
            default -> System.out.println(Color.RED + "Invalid Input!");
        }
    }

    @Override
    public void printTheMenu() {
        System.out.println();
        System.out.println(Color.YELLOW + "***********************");
        System.out.println(Color.BLUE + "1- Add fund");
        System.out.println(Color.BLUE + "2- Remove fund");
        System.out.println(Color.BLUE + "3- Fund management");
        System.out.println(Color.BLUE + "4- Return");
        System.out.println(Color.YELLOW + "***********************");
        System.out.print(Color.PURPLE+ "Select the (1 - 4): ");
    }

    @Override
    public CapitalFundOption getOption() {
        CapitalFundOption[] options = CapitalFundOption.values();
        String inputStr = ScannerWrapper.getInstance().nextLine();
        int input;
        try {
            input = Integer.parseInt(inputStr);
        } catch (Exception e) {
            return CapitalFundOption.UNDEFINED;
        }
        input--;
        if (input >= 0 && input < options.length) {
            return options[input];
        }
        return CapitalFundOption.UNDEFINED;
    }
}
