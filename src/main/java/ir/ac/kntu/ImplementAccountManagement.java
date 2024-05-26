package ir.ac.kntu;

import ir.ac.kntu.util.Calendar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class ImplementAccountManagement {

    public void chargeAccount(UserAccount myAccount) {
        System.out.print(Color.YELLOW + "Enter the amount of money: ");
        String input = ScannerWrapper.getInstance().nextLine();
        int amount = Integer.parseInt(input);

        myAccount.setBalanceAccount(myAccount.getBalanceAccount() + amount);
        System.out.println(Color.YELLOW + "Your account increased by " + amount);

        String dateNow = Calendar.getStringNow();
        ChargeAccount newCharge = new ChargeAccount(amount, dateNow);
        myAccount.addChargeAccount(newCharge);

    }

    public void balance(UserAccount myAccount) {
        System.out.println(Color.BLUE + "Your balance account is: " + Color.GREEN + myAccount.getBalanceAccount());
    }

    public void timeFilterTransaction(UserAccount myAccount) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        String pattern = "year.month.day hour:minute:second(yyyy.MM.dd HH:mm:ss)";
        System.out.println(Color.YELLOW + "Enter the start date like this structure " + pattern);
        String startDate = ScannerWrapper.getInstance().nextLine();
        Date date1;
        try {
            date1 = simpleDateFormat.parse(startDate);
        } catch (Exception e) {
            System.out.println(Color.RED + "invalid input");
            return;
        }

        System.out.println(Color.YELLOW + "Enter the end date like this structure " + pattern);
        String endDate = ScannerWrapper.getInstance().nextLine();
        Date date2;
        try {
            date2 = simpleDateFormat.parse(endDate);
        } catch (Exception e) {
            System.out.println(Color.RED + "invalid input");
            return;
        }

        handleTimeFilterTransaction(myAccount, date1, date2);
    }

    public void handleTimeFilterTransaction(UserAccount myAccount, Date startDate, Date endDate) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd 'at' HH:mm:ss");
        List<ChargeAccount> newChargeAccount = new LinkedList<ChargeAccount>();
        List<Transfer> newTransfer = new LinkedList<Transfer>();

        for (int i = myAccount.getChargeAccounts().size() - 1; i >= 0; i--) {
            Date date;
            try {
                date = simpleDateFormat.parse(myAccount.getChargeAccounts().get(i).getDateOfChargeAccount());
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            if (startDate.getTime() <= date.getTime() && endDate.getTime() >= date.getTime()) {
                newChargeAccount.add(myAccount.getChargeAccounts().get(i));
            }
        }

        for (int i = myAccount.getTransfers().size() - 1; i >= 0; i--) {
            Date date;
            try {
                date = simpleDateFormat.parse(myAccount.getTransfers().get(i).getDateOfTransfer());
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            if (startDate.getTime() <= date.getTime() && endDate.getTime() >= date.getTime()) {
                newTransfer.add(myAccount.getTransfers().get(i));
            }
        }
        transaction(newChargeAccount, newTransfer);
    }

    public void transaction(List<ChargeAccount> chargeAccounts, List<Transfer> transfers) {
        int index = 1;
        System.out.println(Color.YELLOW + "List of charge account: ");
        for (int i = chargeAccounts.size() - 1; i >= 0; i--) {
            System.out.println(Color.GREEN + index + "- " + Color.BLUE + chargeAccounts.get(i).getChargeAmount());
            index++;
        }

        System.out.println(Color.RED + "-------------------------------------");
        System.out.println(Color.YELLOW + "List of transfer: ");
        for (int i = transfers.size() - 1; i >= 0; i--) {
            int amountTransfer = transfers.get(i).getAmountTransfer();
            System.out.println(Color.GREEN + index + "- " + Color.BLUE + amountTransfer);
            index++;
        }
        showTransactionDetail(chargeAccounts, transfers);
    }

    public void showTransactionDetail(List<ChargeAccount> chargeAccounts, List<Transfer> transfers) {
        if (chargeAccounts.isEmpty() && transfers.isEmpty()) {
            return;
        }
        System.out.println(Color.YELLOW + "Enter the number of transaction: ");
        String input = ScannerWrapper.getInstance().nextLine();
        int index = Integer.parseInt(input);

        if (index <= chargeAccounts.size()) {
            System.out.println(Color.BLUE + chargeAccounts.get(index - 1).toString());
        } else {
            System.out.println(Color.BLUE + transfers.get(index - chargeAccounts.size() - 1).toString());
        }
    }


}
