package ir.ac.kntu;

public class Support extends Person{
    private String userName;
    private String password;

    public Support() {
    }

    public Support(String fName, String userName, String password) {
        super(fName);
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
