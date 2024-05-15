package ir.ac.kntu;

public class UserAccount extends User{
    private int accountNumber;
    private String creditCard;

    public UserAccount() {
    }


    public UserAccount(String firstName, String lastName, String phoneNumber, String id, String password, int accountNumber) {
        super(firstName, lastName, phoneNumber, id, password);
        this.accountNumber = accountNumber;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }
}
