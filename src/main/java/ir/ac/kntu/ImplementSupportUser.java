package ir.ac.kntu;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ImplementSupportUser {
    public void showSupportUser(UserAccount myAccount, Bank myBank) {
        List<Requests> myRequest = new ArrayList<>();
        for (Map.Entry<String, List<Requests>> entry : myBank.getRequest().entrySet()) {
            if (entry.getKey().equals(myAccount.getPhoneNumber())) {
                myRequest = entry.getValue();
            }
        }
        printMassages(myRequest);
    }

    public void printMassages(List<Requests> requests) {
        if (requests.isEmpty()) {
            System.out.println(Color.RED + "The list is empty!");
            return;
        }

        for (int i = 1; i <= requests.size(); i++) {
            System.out.println(Color.GREEN + i + "- " + Color.BLUE + requests.get(i - 1).toString() + " Support massage: " + requests.get(i - 1).getSupportMassage());
        }
    }


    public String inputTheMassage() {
        System.out.print(Color.YELLOW + "Enter your massage: ");
        return ScannerWrapper.getInstance().nextLine();
    }

    public void handleSupportUser(Bank myBank, UserAccount myAccount, RequestType requestType) {
        String massageUser = inputTheMassage();
        List<Requests> requests = userRequest(myBank.getRequest(), myAccount.getPhoneNumber());
        requests.add(new Requests(requestType, ApplicationStatus.REGISTERED, massageUser, " "));
        myBank.addRequest(myAccount.getPhoneNumber(), requests);
    }

    public List<Requests> userRequest(Map<String, List<Requests>> requests, String phoneNumber) {
        List<Requests> requestsList = new ArrayList<>();
        for (Map.Entry<String, List<Requests>> entry : requests.entrySet()) {
            if (entry.getKey().equals(phoneNumber)) {
                requestsList = entry.getValue();
            }
        }
        return requestsList;
    }

}
