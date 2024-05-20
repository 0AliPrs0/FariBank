package ir.ac.kntu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Bank {
    private List<UserAccount> userAccounts = new ArrayList<>();
    private List<Authentication> authentications = new ArrayList<>();
    private List<Support> supports = new ArrayList<>();
    private Map<UserAccount, Requests> requests = new HashMap<>();

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

    public Requests getRequest(UserAccount user){
        return requests.get(user);
    }

    public void addRequest(UserAccount user, Requests newRequest){
        requests.put(user, newRequest);
    }
}
