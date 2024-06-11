package ir.ac.kntu;

import java.util.Date;

public class ImplementCapitalFund {
    public void addFund(UserAccount myAccount, Bank myBank) {
        System.out.println(Color.BLUE + "1- Profit fund");
        System.out.println(Color.BLUE + "2- Bonus fund");
        System.out.print(Color.CYAN + "Enter the type of fund : ");
        String input = ScannerWrapper.getInstance().nextLine();

        switch (input) {
            case "1" -> addProfitFund(myAccount);
            case "2" -> addBonusFund(myAccount, myBank);
            default -> System.out.println("Invalid input!");
        }
    }

    public void addProfitFund(UserAccount myAccount) {
        System.out.print(Color.CYAN + "Enter the name of fund: ");
        String fundName = ScannerWrapper.getInstance().nextLine();
        BonusFund bonusFund = searchBonusFund(myAccount, fundName);
        if (bonusFund == null) {
            System.out.println(Color.RED + "This fund already exist!");
            return;
        }
        myAccount.addProfitFund(new ProfitFund(fundName));
        System.out.println(Color.GREEN + "The fund added");
    }

    public void addBonusFund(UserAccount myAccount, Bank myBank) {
        System.out.print(Color.CYAN + "Enter the name of fund: ");
        String fundName = ScannerWrapper.getInstance().nextLine();
        ProfitFund profitFund = searchProfitFund(myAccount, fundName);
        if (profitFund == null) {
            System.out.println(Color.RED + "This fund already exist!");
            return;
        }
        System.out.print(Color.CYAN + "The duration of the deposit is " + myBank.getDepositPeriod() + " minutes. How long (How minute) do you want to deposit in this fund? ");
        String input = ScannerWrapper.getInstance().nextLine();

        int numberOfDeposit = 0;
        try {
            numberOfDeposit = Integer.parseInt(input) / myBank.getDepositPeriod();
        } catch (Exception e) {
            System.out.println(Color.RED + "Invalid input");
        }
        System.out.print(Color.CYAN + "Enter the amount of money for deposit: ");
        String inputStr = ScannerWrapper.getInstance().nextLine();

        int amountMoney = 0;
        try {
            amountMoney = Integer.parseInt(inputStr);
        } catch (Exception e) {
            System.out.println(Color.RED + "Invalid input");
        }

        if (amountMoney <= myAccount.getBalanceAccount()) {
            Date currentDate = new Date();
            myAccount.addBonusFund(new BonusFund(amountMoney, fundName, currentDate, numberOfDeposit));
        } else {
            System.out.println(Color.RED + "Your balance is not enough!");
        }
    }

    public void removeFund(UserAccount myAccount) {
        System.out.print(Color.CYAN + "Enter the name of fund for removing: ");
        String fundName = ScannerWrapper.getInstance().nextLine();

        BonusFund bonusFund = searchBonusFund(myAccount, fundName);
        ProfitFund profitFund = searchProfitFund(myAccount, fundName);

        if (bonusFund != null) {
            if (bonusFund.getNumberOfDeposit() <= 0) {
                myAccount.removeBonusFund(bonusFund);
            } else {
                System.out.println(Color.RED + "You can not remove this fund. because the deposit time has not yet expired");
            }
        } else if (profitFund != null) {
            myAccount.removeProfitFund(profitFund);
        } else {
            System.out.println(Color.RED + "There is not fund with this name!");
        }
    }

    public BonusFund searchBonusFund(UserAccount myAccount, String fundName) {
        for (BonusFund bonusFund : myAccount.getBonusFunds()) {
            if (bonusFund.getFundName().equals(fundName)) {
                return bonusFund;
            }
        }
        return null;
    }

    public ProfitFund searchProfitFund(UserAccount myAccount, String fundName) {
        for (ProfitFund profitFund : myAccount.getProfitFunds()) {
            if (profitFund.getFundName().equals(fundName)) {
                return profitFund;
            }
        }
        return null;
    }

    public void fundManagement(UserAccount myAccount) {
        int index = 1;
        System.out.println(Color.YELLOW + index + "- " + myAccount.getRemainingFund().toString());
        index++;

        for (ProfitFund entry : myAccount.getProfitFunds()) {
            System.out.println(Color.YELLOW + index + "- " + entry.toString());
            index++;
        }

        for (BonusFund entry : myAccount.getBonusFunds()) {
            System.out.println(Color.YELLOW + index + "- " + entry.toString());
            index++;
        }

        System.out.print(Color.CYAN + "Enter the number of fund: ");
        String input = ScannerWrapper.getInstance().nextLine();
        int indexOfFund = 0;

        try {
            indexOfFund = Integer.parseInt(input);
        } catch (Exception e) {
            System.out.println(Color.RED + "Invalid input!");
            return;
        }

        if (indexOfFund > myAccount.getBonusFunds().size() + myAccount.getProfitFunds().size() + 1) {
            System.out.println(Color.RED + "Invalid input!");
            return;
        }
        manageFund(indexOfFund, myAccount);
    }

    public void manageFund(int indexOfFund, UserAccount myAccount) {
        int indexOfOperation = inputOperation();
        if (indexOfOperation < 1 || indexOfOperation > 2) {
            return;
        }

        int amountOfMoney = inputAmountMoney();
        if (amountOfMoney < 0) {
            return;
        }

        CapitalFund capitalFund = findFund(myAccount, indexOfFund);
        if (isNotTimeTrue(myAccount, indexOfFund)) {
            return;
        }

        switch (indexOfFund) {
            case 1 -> {
                if (amountOfMoney > myAccount.getBalanceAccount()) {
                    System.out.println(Color.RED + "Your balance is not enough!");
                    return;
                }
                transfer(myAccount, indexOfFund, amountOfMoney);
            }
            case 2 -> {
                if (amountOfMoney > capitalFund.getFundBalance()) {
                    System.out.println(Color.RED + "The fund balance is not enough!");
                    return;
                }
                transfer(myAccount, indexOfFund, -amountOfMoney);
            }
            default -> System.out.println();
        }
    }

    public boolean isNotTimeTrue(UserAccount myAccount, int indexOfFund) {
        CapitalFund capitalFund = findFund(myAccount, indexOfFund);
        if (capitalFund instanceof BonusFund other) {
            if (other.getNumberOfDeposit() > 0) {
                System.out.println(Color.RED + "You can not transfer by this fund. because the deposit time has not yet expired");
                return true;
            }
        }
        return false;
    }

    public CapitalFund findFund(UserAccount myAccount, int indexOfFund) {
        if (indexOfFund == 1) {
            return myAccount.getRemainingFund();
        } else if (indexOfFund > 1 && indexOfFund < myAccount.getProfitFunds().size() + 1) {
            return myAccount.getProfitFunds().get(indexOfFund - 2);
        } else {
            return myAccount.getBonusFunds().get(indexOfFund - myAccount.getProfitFunds().size() - 2);
        }
    }

    public int inputOperation() {
        System.out.println(Color.BLUE + "1- Transfer to fund");
        System.out.println(Color.BLUE + "2- Transfer to account");
        System.out.print(Color.CYAN + "Enter the number of operation: ");
        String input = ScannerWrapper.getInstance().nextLine();
        int index = -1;
        try {
            index = Integer.parseInt(input);
        } catch (Exception e) {
            System.out.println(Color.RED + "Invalid input!");
        }
        if (index < 1 || index > 2) {
            System.out.println(Color.RED + "Invalid input!");
        }

        return index;
    }

    public int inputAmountMoney() {
        System.out.print(Color.CYAN + "Enter the amount of money: ");
        String input = ScannerWrapper.getInstance().nextLine();
        int index = -1;
        try {
            index = Integer.parseInt(input);
        } catch (Exception e) {
            System.out.println(Color.RED + "Invalid input!");
        }
        return index;

    }

    public void transfer(UserAccount myAccount, int indexOfFund, int amountMoney) {
        myAccount.setBalanceAccount(myAccount.getBalanceAccount() - amountMoney);

        if (indexOfFund == 1) {
            int balanceOfFund = myAccount.getRemainingFund().getFundBalance();
            myAccount.getRemainingFund().setFundBalance(balanceOfFund + amountMoney);

        } else if (indexOfFund > 1 && indexOfFund < myAccount.getProfitFunds().size() + 1) {
            int balanceOfFund = myAccount.getProfitFunds().get(indexOfFund - 2).getFundBalance();
            myAccount.getProfitFunds().get(indexOfFund - 2).setFundBalance(balanceOfFund + amountMoney);

        } else if (indexOfFund > myAccount.getBonusFunds().size() + myAccount.getProfitFunds().size() + 1) {
            int balanceOfFund = myAccount.getBonusFunds().get(indexOfFund - myAccount.getProfitFunds().size() - 2).getFundBalance();
            myAccount.getBonusFunds().get(indexOfFund - myAccount.getProfitFunds().size() - 2).setFundBalance(balanceOfFund + amountMoney);

        }
    }
}
