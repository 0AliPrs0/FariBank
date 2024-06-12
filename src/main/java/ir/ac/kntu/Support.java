package ir.ac.kntu;

public class Support {
    private String name;
    private String userName;
    private String password;
    private boolean isBlocked;

    public Support() {
    }

    public Support(String name, String userName, String password) {
        this.name = name;
        this.userName = userName;
        this.password = password;
        this.isBlocked = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public boolean getIsBlocked() {
        return isBlocked;
    }

    public void setIsBlocked(boolean blocked) {
        isBlocked = blocked;
    }
}
