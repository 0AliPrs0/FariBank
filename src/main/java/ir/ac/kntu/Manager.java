package ir.ac.kntu;

import java.util.List;

public class Manager {
    private String userName;
    private String password;
    private List<String> fathersManager;
    private boolean isBlocked;

    public Manager() {
    }

    public Manager(String userName, String password, List<String> fathersManager) {
        this.userName = userName;
        this.password = password;
        this.fathersManager = fathersManager;
        this.isBlocked = false;
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

    public List<String> getFathersManager() {
        return fathersManager;
    }

    public void setFathersManager(List<String> fathersManager) {
        this.fathersManager = fathersManager;
    }

    public boolean getIsBlocked() {
        return isBlocked;
    }

    public void setIsBlocked(boolean blocked) {
        isBlocked = blocked;
    }
}
