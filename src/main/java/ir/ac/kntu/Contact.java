package ir.ac.kntu;

public class Contact {
    private String firstName;
    private String lastName;
    private String phoneNumber;

    public Contact() {

    }

    public Contact(String firstName, String lName, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lName;
        this.phoneNumber = phoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLName() {
        return lastName;
    }

    public void setLName(String lName) {
        this.lastName = lName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return  "firstName = '" + firstName + "\'  " +
                "lastName = '" + lastName + "\'  " +
                ", phoneNumber = '" + phoneNumber + '\'';
    }
}
