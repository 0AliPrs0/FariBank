package ir.ac.kntu;

public class Person {
    private String firstName;

    public Person() {
    }

    public Person(String fName) {
        this.firstName = fName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String fName) {
        this.firstName = fName;
    }
}
