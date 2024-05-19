package ir.ac.kntu;

public class Contact extends Person{
    private String lastName;
    private String phoneNumber;

    public Contact() {

    }

    public Contact(String firstName, String lName, String phoneNumber) {
        super(firstName);
        this.lastName = lName;
        this.phoneNumber = phoneNumber;
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
        return "Contact { " +
                "firstName = '" + getFirstName() + "\'  " +
                "lastName = '" + lastName + "\'  " +
                ", phoneNumber = '" + phoneNumber + '\'' +
                " }";
    }
}
