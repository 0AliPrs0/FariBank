package ir.ac.kntu.user.info;

public class RecentlyAccountForTransfer {
    private String name;
    private int accountNumber;

    public RecentlyAccountForTransfer() {
    }

    public RecentlyAccountForTransfer(String name, int accountNumber) {
        this.name = name;
        this.accountNumber = accountNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    @Override
    public String toString() {
        return  "name='" + name + '\'' +
                ", accountNumber=" + accountNumber;
    }
}
