package ir.ac.kntu.main.database;

import ir.ac.kntu.main.baseclass.InterestRatesAndFees;
import ir.ac.kntu.main.baseclass.MockAccount;
import ir.ac.kntu.manager.info.Manager;
import ir.ac.kntu.support.info.Support;
import ir.ac.kntu.user.info.Requests;
import ir.ac.kntu.user.info.Transfer;
import ir.ac.kntu.user.info.UserAccount;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Bank {
    private List<UserAccount> userAccounts = new ArrayList<>();
    private List<Support> supports = new ArrayList<>();
    private List<Manager> managers = new ArrayList<>();
    private InterestRatesAndFees interests = new InterestRatesAndFees();
    private List<MockAccount> mockAccounts = new ArrayList<>();
    private Map<String, List<Requests>> requests = new HashMap<>();
    private int depositPeriod;
    private List<Transfer> transfers = new ArrayList<>();

    public List<UserAccount> getUserAccounts() {
        return userAccounts;
    }

    public List<Support> getSupports() {
        return supports;
    }

    public void addSupport(Support support){
        supports.add(support);
    }

    public List<Manager> getManagers() {
        return managers;
    }

    public void addManager(Manager manager){
        managers.add(manager);
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

    public List<MockAccount> getMockAccounts() {
        return mockAccounts;
    }

    public void addMockAccounts(MockAccount mockAccount){
        mockAccounts.add(mockAccount);
    }

    public void addRequest(String phoneNumber, List<Requests> newRequest) {
        requests.put(phoneNumber, newRequest);
    }

    public int getDepositPeriod() {
        return depositPeriod;
    }

    public void setDepositPeriod(int depositPeriod) {
        this.depositPeriod = depositPeriod;
    }

    public List<Transfer> getTransfers() {
        return transfers;
    }

    public void addTransfer(Transfer transfer){
        transfers.add(transfer);
    }

    public void removeTransfer(Transfer transfer){
        transfers.remove(transfer);
    }
}
