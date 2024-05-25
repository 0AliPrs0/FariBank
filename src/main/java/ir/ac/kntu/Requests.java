package ir.ac.kntu;

public class Requests extends User {
    private RequestType requestType;
    private ApplicationStatus applicationStatus;
    private String userMassage;
    private String supportMassage;

    public Requests() {
    }

    public Requests(RequestType requestType, ApplicationStatus applicationStatus, String userMassage, String supportMassage) {
        super();
        this.requestType = requestType;
        this.applicationStatus = applicationStatus;
        this.userMassage = userMassage;
        this.supportMassage = supportMassage;
    }

    public RequestType getRequestType() {
        return requestType;
    }

    public void setRequestType(RequestType requestType) {
        this.requestType = requestType;
    }

    public ApplicationStatus getApplicationStatus() {
        return applicationStatus;
    }

    public void setApplicationStatus(ApplicationStatus applicationStatus) {
        this.applicationStatus = applicationStatus;
    }

    public String getUserMassage() {
        return userMassage;
    }

    public void setUserMassage(String userMassage) {
        this.userMassage = userMassage;
    }

    public String getSupportMassage() {
        return supportMassage;
    }

    public void setSupportMassage(String supportMassage) {
        this.supportMassage = supportMassage;
    }

    @Override
    public String toString() {
        return "requestType=" + requestType +
                ", applicationStatus=" + applicationStatus +
                ", userMassage='" + userMassage + "' ";
    }
}
