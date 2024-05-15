package ir.ac.kntu;

public class User extends Person{
    private String lastName;
    private String phoneNumber;
    private String id;
    private String password;
    private int accountNumber;
    private String creditCard;
    public User(){

    }

    public User(String firstName, String lastName, String phoneNumber, String id, String password, int accountNumber) {
        super(firstName);
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.id = id;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
