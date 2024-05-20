package ir.ac.kntu;

import java.util.ArrayList;
import java.util.List;

public class Bank {
    private List<UserAccount> userAccounts = new ArrayList<>();
    private List<Authentication> authentications = new ArrayList<>();
    private List<Support> supports = new ArrayList<>();

    public Bank() {
    }

    public ArrayList<UserAccount> getUserAccounts() {
        return new ArrayList<>(userAccounts);
    }

    public ArrayList<Authentication> getAuthentications() {
        return new ArrayList<>(authentications);
    }


    public ArrayList<Support> getSupports() {
        return new ArrayList<>(supports);
    }
}
