package ir.ac.kntu;

import java.util.ArrayList;

public class UserAccount extends User{
    private int accountNumber;
    private String creditCard;
    private ArrayList<Contact> myContacts = new ArrayList<>();

    public UserAccount() {
    }


    public UserAccount(String firstName, String lastName, String phoneNumber, String id, String password, int accountNumber, ArrayList<Contact> myContacts) {
        super(firstName, lastName, phoneNumber, id, password);
        this.accountNumber = accountNumber;
        this.myContacts = myContacts;
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
}
