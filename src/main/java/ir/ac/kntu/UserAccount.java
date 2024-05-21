package ir.ac.kntu;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class UserAccount extends User {
    public int balanceAccount;
    private int accountNumber;
    private int cardPassword;
    private String creditCard;
    private List<RecentlyAccountForTransfer> recentlyAccountNumberForTransfer = new ArrayList<>();
    private final List<Contact> myContacts = new ArrayList<>();
    private boolean isActingContactKeyword;
    private final List<Transfer> transfers = new LinkedList<>();
    private final List<ChargeAccount> chargeAccounts = new LinkedList<>();


    public UserAccount() {
    }


    public UserAccount(String firstName, String lastName, String phoneNumber, String id, String password, int balanceAccount, int accountNumber) {
        super(firstName, lastName, phoneNumber, id, password);
        this.balanceAccount = balanceAccount;
        this.accountNumber = accountNumber;
        this.cardPassword = -1;
        this.isActingContactKeyword = true;
    }

    public int getBalanceAccount() {
        return balanceAccount;
    }

    public void setBalanceAccount(int balanceAccount) {
        this.balanceAccount = balanceAccount;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public List<RecentlyAccountForTransfer> getRecentlyAccountNumberForTransfer() {
        return recentlyAccountNumberForTransfer;
    }

    public ArrayList<Contact> getMyContacts() {return (ArrayList<Contact>) myContacts;
    }

    public int getCardPassword() {
        return cardPassword;
    }

    public void setCardPassword(int cardPassword) {
        this.cardPassword = cardPassword;
    }

    public boolean getIsActingContactKeyword() {
        return isActingContactKeyword;
    }

    public void setIsActingContactKeyword(boolean actingContactKeyword) {
        isActingContactKeyword = actingContactKeyword;
    }

    public LinkedList<Transfer> getTransfers() {
        return (LinkedList<Transfer>) transfers;
    }

    public LinkedList<ChargeAccount> getChargeAccounts() {
        return (LinkedList<ChargeAccount>) chargeAccounts;
    }

    public void addChargeAccount(ChargeAccount charge){
        chargeAccounts.add(charge);
    }

    public void addTransfer(Transfer transfer){
        transfers.add(transfer);
    }
}
