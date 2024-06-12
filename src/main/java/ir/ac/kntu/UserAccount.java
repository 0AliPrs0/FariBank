package ir.ac.kntu;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class UserAccount extends User {
    private String cardNumber;
    private final List<RecentlyAccountForTransfer> recentlyAccount = new ArrayList<>();
    private final List<Contact> myContacts = new ArrayList<>();
    private final List<Transfer> transfers = new LinkedList<>();
    private final List<ChargeAccount> chargeAccounts = new LinkedList<>();
    private final List<SIMCardCharge> chargeSIMCard = new LinkedList<>();
    private int balanceAccount;
    private int accountNumber;
    private int cardPassword;
    private boolean isActingContact;
    private int simCardValidity;
    private RemainingFund remainingFund;
    private List<ProfitFund> profitFunds = new ArrayList<>();
    private List<BonusFund> bonusFunds = new ArrayList<>();

    public UserAccount() {
    }


    public UserAccount(String firstName, String lastName, String phoneNumber, String nationalId, String password, int balanceAccount, int accountNumber, int simCardValidity) {
        super(firstName, lastName, phoneNumber, nationalId, password);
        this.balanceAccount = balanceAccount;
        this.accountNumber = accountNumber;
        this.cardPassword = -1;
        this.isActingContact = true;
        this.simCardValidity = simCardValidity;
        this.cardNumber = "60379" + phoneNumber;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
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

    public List<RecentlyAccountForTransfer> getRecentlyAccount() {
        return recentlyAccount;
    }

    public void addRecentlyAccount(RecentlyAccountForTransfer recentAccount) {
        recentlyAccount.add(recentAccount);
    }

    public List<Contact> getMyContacts() {
        return myContacts;
    }

    public int getCardPassword() {
        return cardPassword;
    }

    public void setCardPassword(int cardPassword) {
        this.cardPassword = cardPassword;
    }

    public boolean getIsActingContactKeyword() {
        return isActingContact;
    }

    public void setIsActingContactKeyword(boolean actingContact) {
        isActingContact = actingContact;
    }

    public List<Transfer> getTransfers() {
        return transfers;
    }

    public List<ChargeAccount> getChargeAccounts() {
        return chargeAccounts;
    }

    public void addChargeAccount(ChargeAccount charge) {
        chargeAccounts.add(charge);
    }

    public void addTransfer(Transfer transfer) {
        transfers.add(transfer);
    }

    public int getSimCardValidity() {
        return simCardValidity;
    }

    public void setSimCardValidity(int simCardValidity) {
        this.simCardValidity = simCardValidity;
    }

    public List<SIMCardCharge> getChargeSIMCard() {
        return chargeSIMCard;
    }

    public void addChargeSIMCard(SIMCardCharge chargeSIM) {
        chargeSIMCard.add(chargeSIM);
    }

    public RemainingFund getRemainingFund() {
        return remainingFund;
    }

    public void setRemainingFund(RemainingFund remainingFund) {
        this.remainingFund = remainingFund;
    }

    public List<ProfitFund> getProfitFunds() {
        return profitFunds;
    }

    public List<BonusFund> getBonusFunds() {
        return bonusFunds;
    }

    public void addProfitFund(ProfitFund profitFund){
        profitFunds.add(profitFund);
    }

    public void addBonusFund(BonusFund bonusFund){
        bonusFunds.add(bonusFund);
    }

    public void removeProfitFund(ProfitFund profitFund){
        profitFunds.remove(profitFund);
    }

    public void removeBonusFund(BonusFund bonusFund){
        bonusFunds.remove(bonusFund);
    }
}
