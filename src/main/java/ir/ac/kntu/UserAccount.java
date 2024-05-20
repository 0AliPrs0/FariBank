package ir.ac.kntu;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class UserAccount extends User {
    public int balanceAccount;
    private int accountNumber;
    private int cardPassword;
    private String creditCard;
    private List<Contact> myContacts = new ArrayList<>();
    private boolean isActingContactKeyword;
    private List<Transfer> transfers = new LinkedList<>();
    private List<ChargeAccount> chargeAccounts = new LinkedList<>();


    public UserAccount() {
    }


    public UserAccount(String firstName, String lastName, String phoneNumber, String id, String password, int balanceAccount, int accountNumber, ArrayList<Contact> myContacts) {
        super(firstName, lastName, phoneNumber, id, password);
        this.balanceAccount = balanceAccount;
        this.accountNumber = accountNumber;
        this.myContacts = myContacts;
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

    public ArrayList<Contact> getMyContacts() {return new ArrayList<>(myContacts);
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
        return new LinkedList<>(transfers);
    }

    public LinkedList<ChargeAccount> getChargeAccounts() {
        return new LinkedList<>(chargeAccounts);
    }
}
