package ir.ac.kntu.user.implement;

import ir.ac.kntu.main.database.Bank;
import ir.ac.kntu.main.enums.ApplicationStatus;
import ir.ac.kntu.main.enums.RequestType;
import ir.ac.kntu.main.help.Color;
import ir.ac.kntu.main.help.ScannerWrapper;
import ir.ac.kntu.user.info.Requests;
import ir.ac.kntu.user.info.User;
import ir.ac.kntu.user.info.UserAccount;

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
        User user = new User(myAccount.getFirstName(), myAccount.getLastName(), myAccount.getPhoneNumber(), myAccount.getNationalId(), myAccount.getPassword());
        requests.add(new Requests(user, requestType, ApplicationStatus.IN_PROGRESS, massageUser, " "));
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
