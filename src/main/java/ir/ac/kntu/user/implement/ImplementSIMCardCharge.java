package ir.ac.kntu.user.implement;

import ir.ac.kntu.main.baseclass.MockAccount;
import ir.ac.kntu.main.database.Bank;
import ir.ac.kntu.main.help.Color;
import ir.ac.kntu.main.help.ScannerWrapper;
import ir.ac.kntu.user.info.SIMCardCharge;
import ir.ac.kntu.user.info.UserAccount;
import ir.ac.kntu.util.Calendar;

public class ImplementSIMCardCharge {
    public void handleChargeManually(Bank myBank, UserAccount myAccount) {
        System.out.print("Enter the destination phone number: ");
        String phoneNumber = ScannerWrapper.getInstance().nextLine();
        boolean isTherePhone = checkPhoneNumber(myBank, phoneNumber);
        if (isTherePhone) {
            inputAmountOfCharge(myBank, myAccount, phoneNumber);
        } else {
            System.out.println(Color.RED + "Not found the phone number!");
        }
    }

    public boolean checkPhoneNumber(Bank myBank, String phoneNumber) {
        for (UserAccount entry : myBank.getUserAccounts()) {
            if (entry.getPhoneNumber().equals(phoneNumber)) {
                return true;
            }
        }

        for (MockAccount entry : myBank.getMockAccounts()) {
            if (entry.getPhoneNumber().equals(phoneNumber)) {
                return true;
            }
        }
        return false;
    }

    public void handleChargeContacts(Bank myBank, UserAccount myAccount) {
        for (int i = 1; i <= myAccount.getMyContacts().size(); i++) {
            System.out.println(Color.GREEN + i + "-" + myAccount.getMyContacts().get(i - 1).toString());
        }


        System.out.print(Color.YELLOW + "Enter the number of account number: ");
        String input = ScannerWrapper.getInstance().nextLine();
        int index = Integer.parseInt(input);

        if (index > myAccount.getRecentlyAccount().size()) {
            System.out.println(Color.RED + "Enter index in the rage!");
            return;
        }

        String phoneNumber = myAccount.getMyContacts().get(index - 1).getPhoneNumber();
        inputAmountOfCharge(myBank, myAccount, phoneNumber);
    }

    public void handleChargeYourAccount(Bank myBank, UserAccount myAccount) {
        inputAmountOfCharge(myBank, myAccount, myAccount.getPhoneNumber());
    }

    public void inputAmountOfCharge(Bank myBank, UserAccount myAccount, String phoneNumber) {
        System.out.print(Color.GREEN + "Enter the amount of charge: ");
        String input = ScannerWrapper.getInstance().nextLine();
        int amount = 0;
        try {
            amount = Integer.parseInt(input);
        } catch (Exception e) {
            System.out.println(Color.RED + "invalid input!");
            return;
        }

        if (amount > myAccount.getBalanceAccount()) {
            System.out.println(Color.RED + "Your balance is not enough!");
        } else {
            setCharge(myBank, myAccount, phoneNumber, amount);
        }
    }

    public void setCharge(Bank myBank, UserAccount myAccount, String phoneNumber, int amount) {
        for (UserAccount userAccount : myBank.getUserAccounts()) {
            if (userAccount.getPhoneNumber().equals(phoneNumber)) {
                userAccount.setSimCardValidity(userAccount.getSimCardValidity() + amount);
                if (!phoneNumber.equals(myAccount.getPhoneNumber())) {
                    userAccount.addChargeSIMCard(new SIMCardCharge(phoneNumber, amount, Calendar.getStringNow()));
                }
            }
        }

        for (MockAccount mockAccount : myBank.getMockAccounts()) {
            if (mockAccount.getPhoneNumber().equals(phoneNumber)) {
                mockAccount.setSimCardValidity(mockAccount.getSimCardValidity() + amount);
            }
        }

        myAccount.addChargeSIMCard(new SIMCardCharge(phoneNumber, amount, Calendar.getStringNow()));

        myAccount.setBalanceAccount(myAccount.getBalanceAccount() - amount);
    }
}
