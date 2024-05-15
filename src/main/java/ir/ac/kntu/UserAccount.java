package ir.ac.kntu;

public class UserAccount {
    private int accountNumber;
    private String creditCard;

    public UserAccount() {
    }

    public UserAccount(int accountNumber, String creditCard) {
        this.accountNumber = accountNumber;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }
}
