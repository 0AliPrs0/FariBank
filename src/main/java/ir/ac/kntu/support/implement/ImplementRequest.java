package ir.ac.kntu.support.implement;

import ir.ac.kntu.main.database.Bank;
import ir.ac.kntu.main.enums.ApplicationStatus;
import ir.ac.kntu.main.enums.RequestType;
import ir.ac.kntu.main.help.Color;
import ir.ac.kntu.main.help.ScannerWrapper;
import ir.ac.kntu.support.info.Support;
import ir.ac.kntu.support.menus.RequestSupportMenu;
import ir.ac.kntu.user.info.Requests;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ImplementRequest {
    public void requestAccordingToRequestType(Bank myBank, Support support) {
        RequestType requestType = findRequestType(support);
        if (requestType == null) {
            System.out.println(Color.RED + "Invalid input!");
            return;
        }

        Map<String, List<Requests>> requests = new HashMap<>();
        for (Map.Entry<String, List<Requests>> requestsList : myBank.getRequest().entrySet()) {
            List<Requests> list = new ArrayList<>();

            for (Requests entry : requestsList.getValue()) {
                if (entry.getRequestType().equals(requestType)) {
                    list.add(entry);
                }
            }
            requests.put(requestsList.getKey(), list);
        }

        RequestSupportMenu supportMenu = new RequestSupportMenu();
        supportMenu.implementRequestSupport(myBank, requests);
    }

    public RequestType findRequestType(Support support) {
        int input = printMenu(support);
        if (input > 8 || input < 1) {
            System.out.println(Color.RED + "Invalid input!");
            return null;
        }

        if (support.getIsLockField()[input]) {
            System.out.println(Color.RED + "This field inactive for you!!");
            return null;
        }

        return findType(input);
    }

    public RequestType findType(int input){
        switch (input) {
            case 1 -> {
                return RequestType.REPORTS;
            }
            case 2 -> {
                return RequestType.CONTACTS;
            }
            case 3 -> {
                return RequestType.TRANSFER;
            }
            case 4 -> {
                return RequestType.SETTINGS;
            }
            case 5 -> {
                return RequestType.AUTHENTICATION;
            }
            case 6 -> {
                return RequestType.FUND;
            }
            case 7 -> {
                return RequestType.CHARGE;
            }
            case 8 -> {
                return RequestType.CARD;
            }
            default -> System.out.println(Color.RED + "Invalid input!");
        }
        return null;
    }

    public int printMenu(Support support) {
        System.out.println(color(1, support.getIsLockField()) + "1- Reports");
        System.out.println(color(2, support.getIsLockField()) + "2- Contacts");
        System.out.println(color(3, support.getIsLockField()) + "3- Transfer");
        System.out.println(color(4, support.getIsLockField()) + "4- Setting");
        System.out.println(color(5, support.getIsLockField()) + "5- Authentication");
        System.out.println(color(6, support.getIsLockField()) + "6- Fund");
        System.out.println(color(7, support.getIsLockField()) + "7- Charge");
        System.out.println(color(8, support.getIsLockField()) + "8- Card");
        System.out.print(Color.YELLOW + "Enter the request type: ");
        String input = ScannerWrapper.getInstance().nextLine();
        int index = 0;
        try {
            index = Integer.parseInt(input);
        } catch (Exception e) {
            System.out.println(Color.RED + "Invalid input!");
        }

        return index;
    }

    public String color(int number, boolean[] isLock) {
        if (isLock[number]) {
            return Color.RED;
        }
        return Color.GREEN;
    }

    public void requestAccordingToApplicationStatus(Bank myBank, Map<String, List<Requests>> request) {
        ApplicationStatus applicationStatus = findApplicationStatus();
        if (applicationStatus == null) {
            System.out.println(Color.RED + "Invalid input!");
            return;
        }

        Map<String, List<Requests>> requests = new HashMap<>();
        for (Map.Entry<String, List<Requests>> requestsList : request.entrySet()) {
            List<Requests> list = new ArrayList<>();

            for (Requests entry : requestsList.getValue()) {
                if (entry.getApplicationStatus().equals(applicationStatus)) {
                    list.add(entry);
                }
            }
            requests.put(requestsList.getKey(), list);
        }

        showRequest(myBank, requests);
    }

    public ApplicationStatus findApplicationStatus() {
        System.out.println(Color.BLUE + "1- Registered");
        System.out.println(Color.BLUE + "2- In progress");
        System.out.println(Color.BLUE + "3- In closed");
        System.out.print(Color.YELLOW + "Enter the application status: ");
        String applicationStatus = ScannerWrapper.getInstance().nextLine();

        switch (applicationStatus) {
            case "1" -> {
                return ApplicationStatus.REGISTERED;
            }
            case "2" -> {
                return ApplicationStatus.IN_PROGRESS;
            }
            case "3" -> {
                return ApplicationStatus.IN_CLOSED;
            }
            default -> System.out.println(Color.RED + "Invalid input!");
        }

        return null;
    }

    public void requestAccordingToUser(Bank myBank, Map<String, List<Requests>> request) {
        System.out.print(Color.YELLOW + "Enter the phoneNumber of user: ");
        String phoneNumberOfUser = ScannerWrapper.getInstance().nextLine();
        Map<String, List<Requests>> requests = new HashMap<>();

        for (Map.Entry<String, List<Requests>> entry : request.entrySet()) {
            if (entry.getKey().equals(phoneNumberOfUser)) {
                requests.put(entry.getKey(), entry.getValue());

            }
        }
        showRequest(myBank, requests);
    }

    public void showRequest(Bank myBank, Map<String, List<Requests>> requests) {
        if (requests.isEmpty()) {
            System.out.println(Color.RED + "The list is empty!");
            return;
        }
        List<String> phoneNumbers = new ArrayList<>();
        Map<Integer, Requests> requestsMap = new HashMap<>();
        int index = 1;
        for (Map.Entry<String, List<Requests>> requestList : requests.entrySet()) {
            for (Requests entry : requestList.getValue()) {
                phoneNumbers.add(requestList.getKey());
                requestsMap.put(index, entry);
                System.out.println(Color.GREEN + index + "- " + "requestType=" + entry.getRequestType() + ", applicationStatus=" + entry.getApplicationStatus());
                index++;
            }
        }
        inputRequest(myBank, requestsMap, phoneNumbers, index);
    }

    public void inputRequest(Bank myBank, Map<Integer, Requests> requestsMap, List<String> phoneNumbers, int index) {
        System.out.print(Color.YELLOW + "Chose number of request: ");
        String input = ScannerWrapper.getInstance().nextLine();
        int number;
        try {
            number = Integer.parseInt(input);
        } catch (Exception e) {
            return;
        }
        if (number >= index) {
            return;
        }

        String phoneNumber = phoneNumbers.get(number - 1);
        Requests request = requestsMap.get(number);
        if (request.getRequestType().equals(RequestType.AUTHENTICATION)) {
            setAuthentication(myBank, request);
            return;
        }
        if (request.getApplicationStatus().equals(ApplicationStatus.IN_PROGRESS)) {
            request.setApplicationStatus(ApplicationStatus.IN_CLOSED);
            sendSupportMassage(myBank, phoneNumber, request);
        } else {
            System.out.println(Color.BLUE + request.toString() + "supportMassage: " + request.getSupportMassage());
        }
    }

    public void setAuthentication(Bank myBank, Requests requests) {
        System.out.println(Color.GREEN + "firstName='" + requests.getFirstName() + '\'' +
                ", lastName='" + requests.getLastName() + '\'' +
                ", phoneNumber='" + requests.getPhoneNumber() + '\'' +
                ", nationalId='" + requests.getNationalId() + '\'' +
                ", password='" + requests.getPassword());
        System.out.println(Color.YELLOW + "Do you confirm this user ?");
        System.out.println(Color.BLUE + "1- Yes");
        System.out.println(Color.BLUE + "2- No");
        String input = ScannerWrapper.getInstance().nextLine();
        int number;
        try {
            number = Integer.parseInt(input);
        } catch (Exception e) {
            return;
        }

        if (number == 1) {
            setApplicationStatus(myBank, requests, ApplicationStatus.REGISTERED, "");
        } else if (number == 2) {
            System.out.println(Color.YELLOW + "Enter the reason for rejecting this user's authentication");
            String supportOpinion = ScannerWrapper.getInstance().nextLine();
            setApplicationStatus(myBank, requests, ApplicationStatus.IN_CLOSED, supportOpinion);
        }
    }

    public void setApplicationStatus(Bank myBank, Requests requests, ApplicationStatus applicationStatus, String supportMassage) {
        for (int i = 0; i < myBank.getRequest().get(requests.getPhoneNumber()).size(); i++) {
            if (myBank.getRequest().get(requests.getPhoneNumber()).get(i).getRequestType().equals(RequestType.AUTHENTICATION)) {
                myBank.getRequest().get(requests.getPhoneNumber()).get(i).setApplicationStatus(applicationStatus);
                myBank.getRequest().get(requests.getPhoneNumber()).get(i).setSupportMassage(supportMassage);
            }
        }
    }

    public void sendSupportMassage(Bank myBank, String phoneNumber, Requests request) {
        System.out.println(Color.YELLOW + "If you want fix this problem?");
        System.out.println(Color.BLUE + "1- Yes");
        System.out.println(Color.BLUE + "2- No");
        String input = ScannerWrapper.getInstance().nextLine();
        int number;
        try {
            number = Integer.parseInt(input);
        } catch (Exception e) {
            return;
        }
        if (number != 1) {
            return;
        }

        System.out.print(Color.YELLOW + "Enter your massage to user for this problem: ");
        String massageSupport = ScannerWrapper.getInstance().nextLine();
        String userMassage = request.getUserMassage();
        request.setApplicationStatus(ApplicationStatus.REGISTERED);

        for (Map.Entry<String, List<Requests>> requestList : myBank.getRequest().entrySet()) {
            for (Requests entry : requestList.getValue()) {
                if (requestList.getKey().equals(phoneNumber) && entry.getUserMassage().equals(userMassage)) {
                    entry.setSupportMassage(massageSupport);
                }
            }
        }
    }

}
