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
        List<ChargeAccount> newChargeAccount = new LinkedList<>();
        List<Transfer> newTransfer = new LinkedList<>();
        List<SIMCardCharge> simCardCharges = new LinkedList<>();

        addChargeAccounts(myAccount, startDate, endDate, newChargeAccount);
        addTransfers(myAccount, startDate, endDate, newTransfer);
        addSIMCardCharges(myAccount, startDate, endDate, simCardCharges);
        transaction(newChargeAccount, newTransfer, simCardCharges);
    }

    public void addChargeAccounts(UserAccount myAccount, Date startDate, Date endDate, List<ChargeAccount> newChargeAccount){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd 'at' HH:mm:ss");
        Date date;

        for (int i = myAccount.getChargeAccounts().size() - 1; i >= 0; i--) {
            try {
                date = simpleDateFormat.parse(myAccount.getChargeAccounts().get(i).getDateOfChargeAccount());
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            if (startDate.getTime() <= date.getTime() && endDate.getTime() >= date.getTime()) {
                newChargeAccount.add(myAccount.getChargeAccounts().get(i));
            }
        }
    }

    public void addTransfers(UserAccount myAccount, Date startDate, Date endDate, List<Transfer> newTransfer){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd 'at' HH:mm:ss");
        Date date;

        for (int i = myAccount.getTransfers().size() - 1; i >= 0; i--) {
            try {
                date = simpleDateFormat.parse(myAccount.getTransfers().get(i).getDateOfTransfer());
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            if (startDate.getTime() <= date.getTime() && endDate.getTime() >= date.getTime()) {
                newTransfer.add(myAccount.getTransfers().get(i));
            }
        }
    }

    public void addSIMCardCharges(UserAccount myAccount, Date startDate, Date endDate, List<SIMCardCharge> simCardCharges){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd 'at' HH:mm:ss");
        Date date;

        for (int i = myAccount.getChargeSIMCard().size() - 1; i >= 0; i--) {
            try {
                date = simpleDateFormat.parse(myAccount.getChargeSIMCard().get(i).getDateOfCharge());
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            if (startDate.getTime() <= date.getTime() && endDate.getTime() >= date.getTime()) {
                simCardCharges.add(myAccount.getChargeSIMCard().get(i));
            }
        }
    }

    public void transaction(List<ChargeAccount> chargeAccounts, List<Transfer> transfers, List<SIMCardCharge> simCardCharges) {
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

        System.out.println(Color.RED + "-------------------------------------");
        System.out.println(Color.YELLOW + "List of SIM card charges: ");
        for (int i = simCardCharges.size() - 1; i >= 0; i--) {
            int amountTransfer = simCardCharges.get(i).getChargeAmount();
            System.out.println(Color.GREEN + index + "- " + Color.BLUE + amountTransfer);
            index++;
        }
        showTransactionDetail(chargeAccounts, transfers, simCardCharges);
    }

    public void showTransactionDetail(List<ChargeAccount> chargeAccounts, List<Transfer> transfers, List<SIMCardCharge> simCardCharges) {
        if (chargeAccounts.isEmpty() && transfers.isEmpty()) {
            return;
        }
        System.out.println(Color.YELLOW + "Enter the number of transaction: ");
        String input = ScannerWrapper.getInstance().nextLine();
        int index = Integer.parseInt(input);

        if (index > chargeAccounts.size() + transfers.size() || index <= 0) {
            System.out.println(Color.RED + "Enter index in the rage!");
            return;
        }
        if (index <= chargeAccounts.size()) {
            System.out.println(Color.BLUE + chargeAccounts.get(index - 1).toString());
        } else if(index <= chargeAccounts.size() + transfers.size()) {
            System.out.println(Color.BLUE + transfers.get(index - chargeAccounts.size() - 1).toString());
        } else {
            System.out.println(Color.BLUE + simCardCharges.get(index - chargeAccounts.size() - transfers.size() - 1).toString());
        }
    }

    public void showSIMCardValidity(UserAccount userAccount){
        System.out.println(Color.GREEN + "Your SIM card validity is : " + Color.BLUE + userAccount.getSimCardValidity());
    }


}
