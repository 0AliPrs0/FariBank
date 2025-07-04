package ir.ac.kntu.main.baseclass;

public class MockAccount {
    private String name;
    private String phoneNumber;
    private int accountNumber;
    private int balanceAccount;
    private int simCardValidity;
    private String cardNumber;

    public MockAccount() {
    }

    public MockAccount(String name, String phoneNumber, int accountNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.accountNumber = accountNumber;
        this.balanceAccount = 0;
        this.simCardValidity = 0;
        this.cardNumber = "58591" + phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public int getBalanceAccount() {
        return balanceAccount;
    }

    public void setBalanceAccount(int balanceAccount) {
        this.balanceAccount = balanceAccount;
    }

    public int getSimCardValidity() {
        return simCardValidity;
    }

    public void setSimCardValidity(int simCardValidity) {
        this.simCardValidity = simCardValidity;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }
}
