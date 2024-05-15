package ir.ac.kntu;

public class User extends Person{
    private String lastName;
    private String phoneNumber;
    private String id;
    private int accountNumber;
    private String creditCard;
    public User(){

    }

    public User(String firstName, String lastName, String phoneNumber, String id, String password, int accountNumber) {
        super(firstName, password);
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.id = id;
        this.accountNumber = accountNumber;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

}
