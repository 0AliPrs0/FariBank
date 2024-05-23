package ir.ac.kntu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Bank {
    private List<UserAccount> userAccounts = new ArrayList<>();
    private List<Support> supports = new ArrayList<>();
    private List<Authentication> authentications = new ArrayList<>();
    private Map<String, Requests> requests = new HashMap<>();

    public Bank() {
    }

    public ArrayList<UserAccount> getUserAccounts() {
        return (ArrayList<UserAccount>) userAccounts;
    }

    public ArrayList<Authentication> getAuthentications() {
        return (ArrayList<Authentication>) authentications;
    }

    public ArrayList<Support> getSupports() {
        return (ArrayList<Support>) supports;
    }

    public Map<String, Requests> getRequest() {
        return requests;
    }

    public void addRequest(String phoneNumber, Requests newRequest) {
        requests.put(phoneNumber, newRequest);
    }
}
