package ir.ac.kntu;

public class mockAccount {
    private String name;
    private String phoneNumber;
    private int accountNumber;
    private int balanceAccount;
    private int SIMCardValidity;

    public mockAccount() {
    }

    public mockAccount(String name, String phoneNumber, int accountNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.accountNumber = accountNumber;
        this.balanceAccount = 0;
        this.SIMCardValidity = 0;
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

    public int getSIMCardValidity() {
        return SIMCardValidity;
    }

    public void setSIMCardValidity(int SIMCardValidity) {
        this.SIMCardValidity = SIMCardValidity;
    }
}
