package ir.ac.kntu;

public class Authentication extends User {
    private String supportOpinion;
    private boolean isAcceptRequest;
    private boolean isCheckSupport;

    public Authentication() {

    }

    public Authentication(String firstName, String lastName, String phoneNumber, String nationalId, String password, String supportOpinion, boolean isAcceptRequest, boolean isCheckSupport) {
        super(firstName, lastName, phoneNumber, nationalId, password);
        this.supportOpinion = supportOpinion;
        this.isAcceptRequest = isAcceptRequest;
        this.isCheckSupport = isCheckSupport;
    }

    public String getSupportOpinion() {
        return supportOpinion;
    }

    public void setSupportOpinion(String supportOpinion) {
        this.supportOpinion = supportOpinion;
    }

    public boolean isAcceptRequest() {
        return isAcceptRequest;
    }

    public void setAcceptRequest(boolean acceptRequest) {
        isAcceptRequest = acceptRequest;
    }

    public boolean isCheckSupport() {
        return isCheckSupport;
    }

    public void setCheckSupport(boolean checkSupport) {
        isCheckSupport = checkSupport;
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + getFirstName() + '\'' +
                ", lastName='" + getLastName() + '\'' +
                ", phoneNumber='" + getPhoneNumber() + '\'' +
                ", id='" + getNationalId() + '\'' +
                ", password='" + getPassword() + '\'' +
                '}';
    }
}
