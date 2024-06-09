package ir.ac.kntu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Bank {
    private List<UserAccount> userAccounts = new ArrayList<>();
    private List<Support> supports = new ArrayList<>();
    private List<Manager> managers = new ArrayList<>();
    private InterestRatesAndFees interests = new InterestRatesAndFees();
    private Map<String, List<Requests>> requests = new HashMap<>();

    public List<UserAccount> getUserAccounts() {
        return userAccounts;
    }

    public List<Support> getSupports() {
        return supports;
    }

    public List<Manager> getManagers() {
        return managers;
    }

    public InterestRatesAndFees getInterests() {
        return interests;
    }

    public void setInterests(InterestRatesAndFees interests) {
        this.interests = interests;
    }

    public Map<String, List<Requests>> getRequest() {
        return requests;
    }

    public void addRequest(String phoneNumber, List<Requests> newRequest) {
        requests.put(phoneNumber, newRequest);
    }
}
