package ir.ac.kntu;

import java.util.ArrayList;

public class UserAccount extends User {
    private int accountNumber;
    private int cardPassword;
    private String creditCard;
    private ArrayList<Contact> myContacts = new ArrayList<>();
    private boolean isActingContactKeyword;

    public UserAccount() {
    }


    public UserAccount(String firstName, String lastName, String phoneNumber, String id, String password, int accountNumber, ArrayList<Contact> myContacts) {
        super(firstName, lastName, phoneNumber, id, password);
        this.accountNumber = accountNumber;
        this.myContacts = myContacts;
        this.isActingContactKeyword = true;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public ArrayList<Contact> getMyContacts() {
        return myContacts;
    }

    public void setMyContacts(ArrayList<Contact> myContacts) {
        this.myContacts = myContacts;
    }

    public int getCardPassword() {
        return cardPassword;
    }

    public void setCardPassword(int cardPassword) {
        this.cardPassword = cardPassword;
    }

    public boolean isActingContactKeyword() {
        return isActingContactKeyword;
    }

    public void setActingContactKeyword(boolean actingContactKeyword) {
        isActingContactKeyword = actingContactKeyword;
    }
}
