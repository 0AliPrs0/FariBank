package ir.ac.kntu.manager.implement;

import ir.ac.kntu.main.database.Bank;
import ir.ac.kntu.main.help.Color;
import ir.ac.kntu.main.baseclass.MockAccount;
import ir.ac.kntu.main.help.ScannerWrapper;
import ir.ac.kntu.user.implement.ImplementTransfer;
import ir.ac.kntu.user.info.BonusFund;
import ir.ac.kntu.user.info.Transfer;
import ir.ac.kntu.user.info.UserAccount;

import java.util.Date;

public class ImplementAutoTransaction {
    public void handleAutoTransaction(Bank myBank) {
        System.out.println(Color.BLUE + "1- Making Transfers");
        System.out.println(Color.BLUE + "2- Interest deposit");
        System.out.print(Color.PURPLE + "Enter the option: ");
        String input = ScannerWrapper.getInstance().nextLine();

        switch (input) {
            case "1" -> handleTransfers(myBank);
            case "2" -> handleInterest(myBank);
            default -> System.out.println(Color.RED + "Invalid input!");
        }
    }

    public void handleTransfers(Bank myBank) {
        int index = 1;
        for (Transfer transfer : myBank.getTransfers()) {
            UserAccount myAccount = findUserAccount(myBank, transfer);
            MockAccount mockAccount = findMockAccount(myBank, transfer);

            ImplementTransfer makeTransfer = new ImplementTransfer();
            int money = transfer.getAmountTransfer();
            int wadge = makeTransfer.computingWadge(myBank.getInterests().getInterBankBridge(), money);

            myAccount.setBalanceAccount(myAccount.getBalanceAccount() - makeTransfer.remainingFundHandler(money + wadge, myAccount));
            mockAccount.setBalanceAccount(mockAccount.getBalanceAccount() + money);

            makeTransfer.setTransactionForCardByCardAndInterestBridgeBAnk(money, myAccount, mockAccount);
            System.out.println(Color.PURPLE + index + "- " + transfer.toString());
            index++;
            myBank.removeTransfer(transfer);
        }
    }

    public UserAccount findUserAccount(Bank myBank, Transfer transfer) {
        UserAccount myAccount = new UserAccount();
        for (UserAccount entry : myBank.getUserAccounts()) {
            if (entry.getAccountNumber() == transfer.getBeginningAccountNumber()) {
                myAccount = entry;
            }
        }
        return myAccount;
    }

    public MockAccount findMockAccount(Bank myBank, Transfer transfer) {
        MockAccount mockAccount = new MockAccount();
        for (MockAccount entry : myBank.getMockAccounts()) {
            if (entry.getAccountNumber() == transfer.getDestinationAccountNumber()) {
                mockAccount = entry;
            }
        }
        return mockAccount;
    }

    public void handleInterest(Bank myBank) {
        int index = 0;
        for (UserAccount user : myBank.getUserAccounts()) {
            for (BonusFund bonus : user.getBonusFunds()) {
                Date currentDate = new Date();
                long elapsedTime = (long) (bonus.getSumOfDeposit() - bonus.getNumberOfDeposit() + 1) * myBank.getDepositPeriod() * 60 * 1000;
                if (bonus.getDataOfDeposit().getTime() + elapsedTime < currentDate.getTime() && bonus.getNumberOfDeposit() > 0) {
                    makeInterest(myBank, user, bonus);
                    System.out.println(Color.PURPLE + index + "- name: " + user.getFirstName() + " " + user.getLastName() + bonus.toString());
                }
            }
        }
    }

    public void makeInterest(Bank myBank, UserAccount user, BonusFund bonus) {
        for (BonusFund entry : user.getBonusFunds()) {
            if (entry.getFundName().equals(bonus.getFundName())) {
                entry.setNumberOfDeposit(entry.getNumberOfDeposit() - 1);

                ImplementTransfer transfer = new ImplementTransfer();
                int interest = transfer.computingWadge(myBank.getInterests().getFundInterestRate(), bonus.getFixedFundBalance());
                entry.setFundBalance(entry.getFundBalance() + interest);
            }
        }

    }
}
