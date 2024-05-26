package ir.ac.kntu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ImplementRequest {
    public void requestAccordingToRequestType(Bank myBank) {
        RequestType requestType = findRequestType();
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

        showRequest(myBank, requests);
    }

    public RequestType findRequestType() {
        System.out.println(Color.BLUE + "1- Reports");
        System.out.println(Color.BLUE + "2- Contacts");
        System.out.println(Color.BLUE + "3- Transfer");
        System.out.println(Color.BLUE + "4- Setting");
        System.out.print(Color.YELLOW + "Enter the request type: ");
        String input = ScannerWrapper.getInstance().nextLine();

        switch (input) {
            case "1" -> {
                return RequestType.REPORTS;
            }
            case "2" -> {
                return RequestType.CONTACTS;
            }
            case "3" -> {
                return RequestType.TRANSFER;
            }
            case "4" -> {
                return RequestType.SETTINGS;
            }
            default -> System.out.println(Color.RED + "Invalid input!");
        }

        return null;
    }

    public void requestAccordingToApplicationStatus(Bank myBank) {
        ApplicationStatus applicationStatus = findApplicationStatus();
        if (applicationStatus == null) {
            System.out.println(Color.RED + "Invalid input!");
            return;
        }

        Map<String, List<Requests>> requests = new HashMap<>();
        for (Map.Entry<String, List<Requests>> requestsList : myBank.getRequest().entrySet()) {
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

    public void requestAccordingToUser(Bank myBank) {
        System.out.print(Color.YELLOW + "Enter the phoneNumber of user: ");
        String phoneNumberOfUser = ScannerWrapper.getInstance().nextLine();
        Map<String, List<Requests>> requests = new HashMap<>();

        for (Map.Entry<String, List<Requests>> entry : myBank.getRequest().entrySet()) {
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
                System.out.println(Color.GREEN + index + "- " + Color.BLUE + entry.toString());
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
        request.setApplicationStatus(ApplicationStatus.IN_PROGRESS);
        sendSupportMassage(myBank, phoneNumber, request);
    }

    public void sendSupportMassage(Bank myBank, String phoneNumber, Requests request) {
        System.out.println(Color.YELLOW + "If you want fix this problem");
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
        request.setApplicationStatus(ApplicationStatus.IN_CLOSED);

        for (Map.Entry<String, List<Requests>> requestList : myBank.getRequest().entrySet()) {
            for (Requests entry : requestList.getValue()) {
                if (requestList.getKey().equals(phoneNumber) && entry.getUserMassage().equals(userMassage)) {
                    entry.setSupportMassage(massageSupport);
                }
            }
        }
    }

}
