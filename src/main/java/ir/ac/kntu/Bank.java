package ir.ac.kntu;

import java.util.ArrayList;

public class Bank {
    private ArrayList<UserAccount> userAccounts = new ArrayList<>();
    private ArrayList<Authentication> authentications = new ArrayList<>();

    public Bank() {
    }

    public Bank(ArrayList<UserAccount> userAccounts, ArrayList<Authentication> authentications) {
        this.userAccounts = userAccounts;
        this.authentications = authentications;
    }

    public ArrayList<UserAccount> getUserAccounts() {
        return userAccounts;
    }

    public void setUserAccounts(ArrayList<UserAccount> userAccounts) {
        this.userAccounts = userAccounts;
    }

    public ArrayList<Authentication> getAuthentications() {
        return authentications;
    }

    public void setAuthentications(ArrayList<Authentication> authentications) {
        this.authentications = authentications;
    }
}
