package ir.ac.kntu.user.info;

public class User {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String nationalId;
    private String password;

    public User() {

    }

    public User(String firstName, String lastName, String phoneNumber, String nationalId, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.nationalId = nationalId;
        this.password = password;
    }

    public User(User user){
        this(user.firstName, user.lastName, user.phoneNumber, user.nationalId, user.password);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
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

    public String getNationalId() {
        return nationalId;
    }

    public void setNatinoalId(String nationalId) {
        this.nationalId = nationalId;
    }

}
