package ir.ac.kntu;

public class Person {
    private String fName;
    private String password;

    public Person() {
    }

    public Person(String fName, String password) {
        this.fName = fName;
        this.password = password;
    }

    public String getFName() {
        return fName;
    }

    public void setFName(String fName) {
        this.fName = fName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
